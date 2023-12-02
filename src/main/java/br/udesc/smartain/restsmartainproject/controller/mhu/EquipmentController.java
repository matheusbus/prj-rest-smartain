package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.Brand;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.BrandService;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent.Equipment;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent.EquipmentRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent.EquipmentService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent.Model;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent.ModelService;
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
@RequestMapping("/api/mhu/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Equipment>> findAll() {
        List<Equipment> equipment = equipmentService.findAll();

        if(equipment.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable Integer id) {
        Optional<Equipment> equipment = equipmentService.findById(id);

        if(equipment.isEmpty()) {
            throw new NotFoundException("Equipment id not found - " + id);
        }

        return ResponseEntity.ok(equipment.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Equipment>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Equipment> equipments = equipmentService.findAllByStatus(status);
        if(equipments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipments);
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@Valid @RequestBody EquipmentRequest request) {
        Equipment newEquipment = new Equipment();
        Machine machine = machineService.findById(request.getMachineId()).get();
        Brand brand = brandService.findById(request.getBrandId()).get();
        Model model = modelService.findById(request.getEquipmentModelId()).get();

        newEquipment.setName(request.getName());
        newEquipment.setMachine(machine);
        newEquipment.setModel(model);
        newEquipment.setStatus(RegisterState.valueOf(request.getStatus()));
        newEquipment.setTechnicalData(request.getTechnicalData());
        newEquipment.setBrand(brand);

        Equipment savedEquipment = equipmentService.save(newEquipment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEquipment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@Valid @RequestBody EquipmentRequest request, @PathVariable Integer id) {
        Optional<Equipment> equipmentToUpdate = equipmentService.findById(id);
        Machine machine = machineService.findById(request.getMachineId()).get();
        Brand brand = brandService.findById(request.getBrandId()).get();
        Model model = modelService.findById(request.getEquipmentModelId()).get();

        if(equipmentToUpdate.isEmpty()) {
            throw new NotFoundException("Equipment id not found - " + id);
        }

        equipmentToUpdate = equipmentToUpdate.map((equipmentUpdated) -> {
            equipmentUpdated.setName(request.getName());
            equipmentUpdated.setMachine(machine);
            equipmentUpdated.setStatus(RegisterState.valueOf(request.getStatus()));
            equipmentUpdated.setTechnicalData(request.getTechnicalData());
            equipmentUpdated.setModel(model);
            equipmentUpdated.setBrand(brand);
            return equipmentUpdated;
        });

        return ResponseEntity.ok(equipmentService.save(equipmentToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Equipment> equipment = equipmentService.findById(id);

        if(equipment.isEmpty()) {
            throw new NotFoundException("Equipment id not found - " + id);
        }
        return ResponseEntity.ok(equipmentService.inactiveEquipment(equipment.get()));
    }

}
