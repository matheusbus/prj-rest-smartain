package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;
    
    @GetMapping
    public ResponseEntity<List<Machine>> findAll() {
        List<Machine> Machines = machineService.findAll();

        if(Machines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(Machines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> findById(@PathVariable Integer id) {
        Optional<Machine> Machine = machineService.findById(id);

        if(Machine.isEmpty()) {
            throw new NotFoundException("Machine id not found - " + id);
        }

        return ResponseEntity.ok(Machine.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Machine>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Machine> Machines = machineService.findAllByStatus(status);
        if(Machines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Machines);
    }

    @PostMapping
    public ResponseEntity<Machine> createMachine(@Valid @RequestBody Machine Machine) {
        Machine newMachine = machineService.save(Machine);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMachine.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(@Valid @RequestBody Machine newMachine, @PathVariable Integer id) {
        Optional<Machine> MachineToUpdate = machineService.findById(id);

        if(MachineToUpdate.isEmpty()) {
            throw new NotFoundException("Machine id not found - " + id);
        }

        MachineToUpdate = MachineToUpdate.map((MachineUpdated) -> {
            MachineUpdated.setUnit(newMachine.getUnit());
            MachineUpdated.setSector(newMachine.getSector());
            MachineUpdated.setProductionCell(newMachine.getProductionCell());
            MachineUpdated.setMachineModel(newMachine.getMachineModel());
            MachineUpdated.setCreatedDate(newMachine.getCreatedDate());
            MachineUpdated.setAcquisitionDate(newMachine.getAcquisitionDate());
            MachineUpdated.setTag(newMachine.getTag());
            MachineUpdated.setTechnicalData(newMachine.getTechnicalData());
            MachineUpdated.setWarranty(newMachine.getWarranty());
            MachineUpdated.setStatus(newMachine.getStatus());
            MachineUpdated.setWarrantyExpDate(newMachine.getWarrantyExpDate());
            return MachineUpdated;
        });

        return ResponseEntity.ok(MachineToUpdate.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Machine> Machine = machineService.findById(id);

        if(Machine.isEmpty()) {
            throw new NotFoundException("Machine id not found - " + id);
        }
        return ResponseEntity.ok(machineService.inactiveMachine(Machine.get()));
    }

}
