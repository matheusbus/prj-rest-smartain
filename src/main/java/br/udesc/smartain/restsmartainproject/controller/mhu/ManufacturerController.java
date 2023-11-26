package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.ManufacturerRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.ManufacturerService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent.Supplier;
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
@RequestMapping("/api/mhu/manufacturer")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<Manufacturer>> findAll() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();

        if(manufacturers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(manufacturers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Integer id) {
        Optional<Manufacturer> manufacturer = manufacturerService.findById(id);

        if(manufacturer.isEmpty()) {
            throw new NotFoundException("Manufacturer id not found - " + id);
        }

        return ResponseEntity.ok(manufacturer.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Manufacturer>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Manufacturer> Manufacturers = manufacturerService.findAllByStatus(status);
        if(Manufacturers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Manufacturers);
    }

    @PostMapping
    public ResponseEntity<Manufacturer> createManufacturer(@Valid @RequestBody ManufacturerRequest request) {
        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setSocialReason(request.getSocialReason());
        newManufacturer.setCnpj(request.getCnpj());
        newManufacturer.setEmail(request.getEmail());
        newManufacturer.setPhone(request.getPhone());
        newManufacturer.setStatus(RegisterState.valueOf(request.getStatus().getValue()));


        Manufacturer savedManufacturer = manufacturerService.save(newManufacturer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedManufacturer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@Valid @RequestBody Manufacturer newManufacturer, @PathVariable Integer id) {
        Optional<Manufacturer> manufacturerToUpdate = manufacturerService.findById(id);

        if(manufacturerToUpdate.isEmpty()) {
            throw new NotFoundException("Manufacturer id not found - " + id);
        }

        manufacturerToUpdate = manufacturerToUpdate.map((manufacturerUpdated) -> {
            manufacturerUpdated.setSocialReason(newManufacturer.getSocialReason());
            manufacturerUpdated.setCnpj(newManufacturer.getCnpj());
            manufacturerUpdated.setEmail(newManufacturer.getEmail());
            manufacturerUpdated.setPhone(newManufacturer.getPhone());
            manufacturerUpdated.setStatus(newManufacturer.getStatus());
            return manufacturerUpdated;
        });

        return ResponseEntity.ok(manufacturerService.save(manufacturerToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Manufacturer> manufacturer = manufacturerService.findById(id);

        if(manufacturer.isEmpty()) {
            throw new NotFoundException("Manufacturer id not found - " + id);
        }
        return ResponseEntity.ok(manufacturerService.inactiveManufacturer(manufacturer.get()));
    }

}
