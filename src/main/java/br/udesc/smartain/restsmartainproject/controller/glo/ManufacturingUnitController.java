package br.udesc.smartain.restsmartainproject.controller.glo;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
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
    public ResponseEntity<ManufacturingUnit> createManUnit(@Valid @RequestBody ManufacturingUnit unit) {
        ManufacturingUnit newUnit = manufacturingUnitService.save(unit);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUnit.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturingUnit> updateById(@Valid @RequestBody ManufacturingUnit newUnit, @PathVariable Integer id) {
        Optional<ManufacturingUnit> unitToUpdate = manufacturingUnitService.findById(id);

        if(unitToUpdate.isEmpty()) {
            throw new NotFoundException("Unit id not found - " + id);
        }

        unitToUpdate = unitToUpdate.map((unitUpdated) -> {
            unitUpdated.setCity(newUnit.getCity());
            unitUpdated.setCustomer(newUnit.getCustomer());
            unitUpdated.setAddress(newUnit.getAddress());
            unitUpdated.setStatus(newUnit.getStatus());

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
