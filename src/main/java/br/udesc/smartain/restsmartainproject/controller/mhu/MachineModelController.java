package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModelRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModelService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent.ModelType;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent.ModelTypeService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.ManufacturerService;
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
@RequestMapping("/api/mhu/machineModel")
public class MachineModelController {
    
    @Autowired
    private MachineModelService machineModelService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ModelTypeService modelTypeService;

    @GetMapping
    public ResponseEntity<List<MachineModel>> findAll() {
        List<MachineModel> MachineModels = machineModelService.findAll();

        if(MachineModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(MachineModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineModel> findById(@PathVariable Integer id) {
        Optional<MachineModel> MachineModel = machineModelService.findById(id);

        if(MachineModel.isEmpty()) {
            throw new NotFoundException("MachineModel id not found - " + id);
        }

        return ResponseEntity.ok(MachineModel.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<MachineModel>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<MachineModel> MachineModels = machineModelService.findAllByStatus(status);
        if(MachineModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MachineModels);
    }

    @PostMapping
    public ResponseEntity<MachineModel> createMachineModel(@Valid @RequestBody MachineModelRequest request) {
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);;
        ModelType machineModelType = modelTypeService.findById(request.getModelTypeId().intValue()).orElse(null);


        MachineModel newMachineModel = new MachineModel();
        newMachineModel.setDimensions(request.getDimensions());
        newMachineModel.setMachineModelType(machineModelType);
        newMachineModel.setModel(request.getModel());
        newMachineModel.setManufacturer(manufacturer);
        newMachineModel.setStatus(RegisterState.valueOf(request.getStatus().getValue()));

        MachineModel savedMachineModel = machineModelService.save(newMachineModel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMachineModel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MachineModel> updateMachineModel(@Valid @RequestBody MachineModelRequest request, @PathVariable Integer id) {
        Optional<MachineModel> MachineModelToUpdate = machineModelService.findById(id);
        Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId().intValue()).orElse(null);;
        ModelType machineModelType = modelTypeService.findById(request.getModelTypeId().intValue()).orElse(null);

        if(MachineModelToUpdate.isEmpty()) {
            throw new NotFoundException("MachineModel id not found - " + id);
        }

        MachineModelToUpdate = MachineModelToUpdate.map((MachineModelUpdated) -> {
            MachineModelUpdated.setModel(request.getModel());
            MachineModelUpdated.setManufacturer(manufacturer);
            MachineModelUpdated.setDimensions(request.getDimensions());
            MachineModelUpdated.setMachineModelType(machineModelType);
            MachineModelUpdated.setStatus(request.getStatus());
            return MachineModelUpdated;
        });

        return ResponseEntity.ok(machineModelService.save(MachineModelToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<MachineModel> MachineModel = machineModelService.findById(id);

        if(MachineModel.isEmpty()) {
            throw new NotFoundException("MachineModel id not found - " + id);
        }
        return ResponseEntity.ok(machineModelService.inactiveMachineModel(MachineModel.get()));
    }
    
}
