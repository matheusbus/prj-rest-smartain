package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentModelComponent.EquipmentModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentModelComponent.EquipmentModelRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentModelComponent.EquipmentModelService;
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
@RequestMapping("/api/mhu/equipmentModel")
public class EquipmentModelController {

    @Autowired
    private EquipmentModelService equipmentModelService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ModelTypeService modelTypeService;

    @GetMapping
    public ResponseEntity<List<EquipmentModel>> findAll() {
        List<EquipmentModel> equipmentModels = equipmentModelService.findAll();

        if(equipmentModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(equipmentModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentModel> findById(@PathVariable Integer id) {
        Optional<EquipmentModel> equipmentModel = equipmentModelService.findById(id);

        if(equipmentModel.isEmpty()) {
            throw new NotFoundException("EquipmentModel id not found - " + id);
        }

        return ResponseEntity.ok(equipmentModel.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<EquipmentModel>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<EquipmentModel> equipmentModels = equipmentModelService.findAllByStatus(status);
        if(equipmentModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipmentModels);
    }

    @PostMapping
    public ResponseEntity<EquipmentModel> createEquipmentModel(@Valid @RequestBody EquipmentModelRequest request) {
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);;
        ModelType componentModelType = modelTypeService.findById(request.getModelTypeId().intValue()).orElse(null);


        EquipmentModel newEquipmentModel = new EquipmentModel();
        newEquipmentModel.setDimensions(request.getDimensions());
        newEquipmentModel.setModelType(componentModelType);
        newEquipmentModel.setModel(request.getModel());
        newEquipmentModel.setManufacturer(manufacturer);
        newEquipmentModel.setStatus(RegisterState.valueOf(request.getStatus().getValue()));

        EquipmentModel savedEquipmentModel = equipmentModelService.save(newEquipmentModel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEquipmentModel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentModel> updateEquipmentModel(@Valid @RequestBody EquipmentModelRequest request, @PathVariable Integer id) {
        Optional<EquipmentModel> equipmentModelToUpdate = equipmentModelService.findById(id);
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);;
        ModelType componentModelType = modelTypeService.findById(request.getModelTypeId().intValue()).orElse(null);

        if(equipmentModelToUpdate.isEmpty()) {
            throw new NotFoundException("EquipmentModel id not found - " + id);
        }

        equipmentModelToUpdate = equipmentModelToUpdate.map((equipmentModelUpdated) -> {
            equipmentModelUpdated.setModel(request.getModel());
            equipmentModelUpdated.setManufacturer(manufacturer);
            equipmentModelUpdated.setDimensions(request.getDimensions());
            equipmentModelUpdated.setModelType(componentModelType);
            equipmentModelUpdated.setStatus(request.getStatus());
            return equipmentModelUpdated;
        });

        return ResponseEntity.ok(equipmentModelService.save(equipmentModelToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<EquipmentModel> equipmentModel = equipmentModelService.findById(id);

        if(equipmentModel.isEmpty()) {
            throw new NotFoundException("EquipmentModel id not found - " + id);
        }
        return ResponseEntity.ok(equipmentModelService.inactiveEquipmentModel(equipmentModel.get()));
    }


}
