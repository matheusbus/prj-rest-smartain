package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent.ModelRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent.ModelService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent.Model;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.ManufacturerService;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import br.udesc.smartain.restsmartainproject.domain.types.DomainModelType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/model")
public class ModelController {
    
    @Autowired
    private ModelService modelService;

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<Model>> findAll() {
        List<Model> models = modelService.findAll();

        if(models.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(models);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> findById(@PathVariable Integer id) {
        Optional<Model> model = modelService.findById(id);

        if(model.isEmpty()) {
            throw new NotFoundException("Model id not found - " + id);
        }

        return ResponseEntity.ok(model.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Model>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Model> models = modelService.findAllByStatus(status);
        if(models.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(models);
    }

    @PostMapping
    public ResponseEntity<Model> createModel(@Valid @RequestBody ModelRequest request) {
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);;

        Model newModel = new Model();
        newModel.setDimensions(request.getDimensions());
        newModel.setModel(request.getModel());
        newModel.setModel(request.getModel());
        newModel.setManufacturer(manufacturer);
        newModel.setStatus(RegisterState.valueOf(request.getStatus().getValue()));

        Model savedModel = modelService.save(newModel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedModel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@Valid @RequestBody ModelRequest request, @PathVariable Integer id) {
        Optional<Model> MachineModelToUpdate = modelService.findById(id);
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);

        if(MachineModelToUpdate.isEmpty()) {
            throw new NotFoundException("Model id not found - " + id);
        }

        MachineModelToUpdate = MachineModelToUpdate.map((MachineModelUpdated) -> {
            MachineModelUpdated.setModel(request.getModel());
            MachineModelUpdated.setManufacturer(manufacturer);
            MachineModelUpdated.setDimensions(request.getDimensions());
            MachineModelUpdated.setDomainType(DomainModelType.valueOf(request.getModelTypeId()));
            MachineModelUpdated.setStatus(request.getStatus());
            return MachineModelUpdated;
        });

        return ResponseEntity.ok(modelService.save(MachineModelToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Model> model = modelService.findById(id);

        if(model.isEmpty()) {
            throw new NotFoundException("Model id not found - " + id);
        }
        return ResponseEntity.ok(modelService.inactiveModel(model.get()));
    }
    
}
