package br.udesc.smartain.restsmartainproject.controller.glo;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.City;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.CityService;
import br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent.Customer;
import br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent.CustomerService;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitRequest;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent.UnitType;
import br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent.UnitTypeService;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/glo/manufacturingUnit")
public class ManufacturingUnitController {

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UnitTypeService unitTypeService;

    @GetMapping
    public ResponseEntity<List<ManufacturingUnit>> findAll() {
        Optional<List<ManufacturingUnit>> units = manufacturingUnitService.findAll();

        if(units.get().isEmpty()) {
            throw new NotFoundException("No one Manufacturing Unit registered yet.");
        }

        return ResponseEntity.ok(units.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<ManufacturingUnit>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<ManufacturingUnit> units = manufacturingUnitService.findAllByStatus(status).get();
        if(units.isEmpty()) {
            throw new NotFoundException("No one unit with status " + RegisterState.valueOf(status).toString());
        }
        return ResponseEntity.ok(units);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturingUnit> findById(@PathVariable Integer id) {
        Optional<ManufacturingUnit> unit = manufacturingUnitService.findById(id);

        if(unit.isEmpty()) {
            throw new NotFoundException("Unit id not found - " + id);
        }
        return ResponseEntity.ok(unit.get());
    }

    @PostMapping
    public ResponseEntity<ManufacturingUnit> createManUnit(@Valid @RequestBody ManufacturingUnitRequest request) {
        Customer customer = customerService.findById(request.getCustomerId().intValue()).orElse(null);;
        City city = cityService.findTeste(request.getCityId().intValue()).orElse(null);
        UnitType type = unitTypeService.findById(request.getTypeId().intValue()).orElse(null);

        ManufacturingUnit newUnit = new ManufacturingUnit();
        newUnit.setCustomer(customer);
        newUnit.setCity(city);
        newUnit.setAddress(request.getAddress());
        newUnit.setTag(request.getTag());
        newUnit.setStatus(RegisterState.valueOf(request.getStatus().getValue()));
        newUnit.setType(type);

        ManufacturingUnit savedUnit = manufacturingUnitService.save(newUnit);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUnit.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturingUnit> updateById(@Valid @RequestBody ManufacturingUnitRequest request, @PathVariable Integer id) {
        Optional<ManufacturingUnit> unitToUpdate = manufacturingUnitService.findById(request.getId());
        Customer customer = customerService.findById(request.getCustomerId().intValue()).orElse(null);;
        City city = cityService.findTeste(request.getCityId().intValue()).orElse(null);
        UnitType type = unitTypeService.findById(request.getTypeId().intValue()).orElse(null);

        if(unitToUpdate.isEmpty()) {
            throw new NotFoundException("Unit id not found - " + id);
        }

        unitToUpdate = unitToUpdate.map((unitUpdated) -> {
            unitUpdated.setTag(request.getTag());
            unitUpdated.setCity(city);
            unitUpdated.setCustomer(customer);
            unitUpdated.setAddress(request.getAddress());
            unitUpdated.setStatus(RegisterState.valueOf(request.getStatus().getValue()));
            unitUpdated.setType(type);

            return unitUpdated;
        });
        return ResponseEntity.ok(manufacturingUnitService.save(unitToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<ManufacturingUnit> unitToDelete = manufacturingUnitService.findById(id);

        if(unitToDelete.isEmpty()) {
            throw new NotFoundException("Unit id not found - " + id);
        }
        return ResponseEntity.ok(manufacturingUnitService.inactivateUnit(unitToDelete.get()));
    }

}
