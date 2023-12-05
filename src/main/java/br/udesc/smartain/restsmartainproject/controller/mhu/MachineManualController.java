package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineManualComponent.MachineManual;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineManualComponent.MachineManualRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineManualComponent.MachineManualService;
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
@RequestMapping("/api/mhu/machineManual")
public class MachineManualController {

    @Autowired
    private MachineManualService machineManualService;

    @Autowired
    private MachineService machineService;

    @GetMapping
    public ResponseEntity<List<MachineManual>> findAll() {
        List<MachineManual> machineManuals = machineManualService.findAll();

        if(machineManuals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(machineManuals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineManual> findById(@PathVariable Integer id) {
        Optional<MachineManual> machineManual = machineManualService.findById(id);

        if(machineManual.isEmpty()) {
            throw new NotFoundException("Machine Manual id not found - " + id);
        }

        return ResponseEntity.ok(machineManual.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<MachineManual>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<MachineManual> machineManuals = machineManualService.findAllByStatus(status);
        if(machineManuals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(machineManuals);
    }

    @PostMapping
    public ResponseEntity<MachineManual> createMachineManual(@Valid @RequestBody MachineManualRequest request) {
        Optional<Machine> machine = machineService.findById(request.getMachineId());

        if(machine.isEmpty()) {
            throw new NotFoundException("Machine id " + request.getMachineId() + " not found.");
        }

        MachineManual newMachineManual = new MachineManual();

        newMachineManual.setMachine(machine.get());
        newMachineManual.setTitle(request.getTitle());
        newMachineManual.setDescription(request.getDescription());
        newMachineManual.setStatus(RegisterState.valueOf(request.getStatus()));

        MachineManual savedMachineManual = machineManualService.save(newMachineManual);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMachineManual.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MachineManual> updateMachineManual(@Valid @RequestBody MachineManualRequest request, @PathVariable Integer id) {
        Optional<MachineManual> machineManualToUpdate = machineManualService.findById(request.getId());
        Optional<Machine> machine = machineService.findById(request.getMachineId());

        if(machineManualToUpdate.isEmpty()) {
            throw new NotFoundException("Machine Manual id not found - " + id);
        }

        if(machine.isEmpty()) {
            throw new NotFoundException("Machine id not found - " + id);
        }

        machineManualToUpdate = machineManualToUpdate.map((machineManualUpdated) -> {
            machineManualUpdated.setMachine(machine.get());
            machineManualUpdated.setTitle(request.getTitle());
            machineManualUpdated.setDescription(request.getDescription());
            machineManualUpdated.setStatus(RegisterState.valueOf(request.getStatus()));
            return machineManualUpdated;
        });

        return ResponseEntity.ok(machineManualService.save(machineManualToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<MachineManual> machineManual = machineManualService.findById(id);

        if(machineManual.isEmpty()) {
            throw new NotFoundException("Machine Manual id not found - " + id);
        }
        return ResponseEntity.ok(machineManualService.inactiveMachineManual(machineManual.get()));
    }


}
