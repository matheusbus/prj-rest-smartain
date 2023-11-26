package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent.Supplier;
import br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent.SupplierRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent.SupplierService;
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
@RequestMapping("/api/mhu/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> findAll() {
        List<Supplier> suppliers = supplierService.findAll();

        if(suppliers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findById(@PathVariable Integer id) {
        Optional<Supplier> supplier = supplierService.findById(id);

        if(supplier.isEmpty()) {
            throw new NotFoundException("Supplier id not found - " + id);
        }

        return ResponseEntity.ok(supplier.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Supplier>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Supplier> suppliers = supplierService.findAllByStatus(status);
        if(suppliers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody SupplierRequest request) {
        Supplier newSupplier = new Supplier();
        newSupplier.setSocialReason(request.getSocialReason());
        newSupplier.setCnpj(request.getCnpj());
        newSupplier.setEmail(request.getEmail());
        newSupplier.setPhone(request.getPhone());
        newSupplier.setStatus(RegisterState.valueOf(request.getStatus().getValue()));


        Supplier savedSupplier = supplierService.save(newSupplier);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSupplier.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@Valid @RequestBody Supplier newSupplier, @PathVariable Integer id) {
        Optional<Supplier> supplierToUpdate = supplierService.findById(id);

        if(supplierToUpdate.isEmpty()) {
            throw new NotFoundException("Supplier id not found - " + id);
        }

        supplierToUpdate = supplierToUpdate.map((supplierUpdated) -> {
            supplierUpdated.setSocialReason(newSupplier.getSocialReason());
            supplierUpdated.setCnpj(newSupplier.getCnpj());
            supplierUpdated.setEmail(newSupplier.getEmail());
            supplierUpdated.setPhone(newSupplier.getPhone());
            supplierUpdated.setStatus(newSupplier.getStatus());
            return supplierUpdated;
        });

        return ResponseEntity.ok(supplierService.save(supplierToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Supplier> supplier = supplierService.findById(id);

        if(supplier.isEmpty()) {
            throw new NotFoundException("Supplier id not found - " + id);
        }
        return ResponseEntity.ok(supplierService.inactiveSupplier(supplier.get()));
    }

}
