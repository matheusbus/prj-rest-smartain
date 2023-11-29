package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent.ComponentModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent.ComponentModelRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent.ComponentModelService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.ManufacturerService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent.ModelType;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent.ModelTypeService;
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
@RequestMapping("/api/mhu/componentModel")
public class ComponentModelController {

    @Autowired
    private ComponentModelService componentModelService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ModelTypeService modelTypeService;

    @GetMapping
    public ResponseEntity<List<ComponentModel>> findAll() {
        List<ComponentModel> componentModels = componentModelService.findAll();

        if(componentModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(componentModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentModel> findById(@PathVariable Integer id) {
        Optional<ComponentModel> componentModel = componentModelService.findById(id);

        if(componentModel.isEmpty()) {
            throw new NotFoundException("ComponentModel id not found - " + id);
        }

        return ResponseEntity.ok(componentModel.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<ComponentModel>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<ComponentModel> componentModels = componentModelService.findAllByStatus(status);
        if(componentModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(componentModels);
    }

    @PostMapping
    public ResponseEntity<ComponentModel> createComponentModel(@Valid @RequestBody ComponentModelRequest request) {
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);;
        ModelType componentModelType = modelTypeService.findById(request.getModelTypeId().intValue()).orElse(null);


        ComponentModel newComponentModel = new ComponentModel();
        newComponentModel.setDimensions(request.getDimensions());
        newComponentModel.setModelType(componentModelType);
        newComponentModel.setModel(request.getModel());
        newComponentModel.setManufacturer(manufacturer);
        newComponentModel.setStatus(RegisterState.valueOf(request.getStatus().getValue()));

        ComponentModel savedComponentModel = componentModelService.save(newComponentModel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedComponentModel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentModel> updateComponentModel(@Valid @RequestBody ComponentModelRequest request, @PathVariable Integer id) {
        Optional<ComponentModel> componentModelToUpdate = componentModelService.findById(id);
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);;
        ModelType componentModelType = modelTypeService.findById(request.getModelTypeId().intValue()).orElse(null);

        if(componentModelToUpdate.isEmpty()) {
            throw new NotFoundException("ComponentModel id not found - " + id);
        }

        componentModelToUpdate = componentModelToUpdate.map((ComponentModelUpdated) -> {
            ComponentModelUpdated.setModel(request.getModel());
            ComponentModelUpdated.setManufacturer(manufacturer);
            ComponentModelUpdated.setDimensions(request.getDimensions());
            ComponentModelUpdated.setModelType(componentModelType);
            ComponentModelUpdated.setStatus(request.getStatus());
            return ComponentModelUpdated;
        });

        return ResponseEntity.ok(componentModelService.save(componentModelToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<ComponentModel> componentModel = componentModelService.findById(id);

        if(componentModel.isEmpty()) {
            throw new NotFoundException("ComponentModel id not found - " + id);
        }
        return ResponseEntity.ok(componentModelService.inactiveComponentModel(componentModel.get()));
    }

}
