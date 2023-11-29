package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent.Component;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent.Equipment;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModelService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCell;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCellService;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.SectorService;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private ProductionCellService productionCellService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @Autowired
    private MachineModelService machineModelService;
    
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

    @GetMapping("/{id}/components")
    public ResponseEntity<List<Component>> getComponentsByMachineId(@PathVariable Integer id) {
        Optional<Machine> Machine = machineService.findById(id);

        if(Machine.isEmpty()) {
            throw new NotFoundException("Machine id not found - " + id);
        }

        return ResponseEntity.ok(Machine.get().getComponents());
    }

    @GetMapping("/{id}/equipments")
    public ResponseEntity<List<Equipment>> getEquipmentsByMachineId(@PathVariable Integer id) {
        Optional<Machine> Machine = machineService.findById(id);

        if(Machine.isEmpty()) {
            throw new NotFoundException("Machine id not found - " + id);
        }

        return ResponseEntity.ok(Machine.get().getEquipments());
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
    public ResponseEntity<Machine> createMachine(@Valid @RequestBody MachineRequest request) {
        ProductionCell cell = productionCellService.findById(request.getProductionCellId()).orElse(null);
        Sector sector = sectorService.findById(cell.getSector().getId()).orElse(null);
        ManufacturingUnit unit = manufacturingUnitService.findById(sector.getUnit().getId()).orElse(null);
        MachineModel model = machineModelService.findById(request.getMachineModelId()).orElse(null);

        Machine newMachine = new Machine();

        newMachine.setMachineModel(model);
        newMachine.setUnit(unit);
        newMachine.setSector(sector);
        newMachine.setProductionCell(cell);
        newMachine.setTag(request.getTag());
        newMachine.setTechnicalData(request.getTechnicalData());
        newMachine.setStatus(RegisterState.valueOf(request.getStatus()));
        newMachine.setCreatedDate(LocalDateTime.now());
        newMachine.setAcquisitionDate(request.getAcquisitionDate());
        newMachine.setWarrantyExpDate(request.getWarrantyExpDate());

        if (newMachine.getWarrantyExpDate().isAfter(LocalDate.now())) {
            newMachine.setWarranty((short) 1);
        }
        else {
            newMachine.setWarranty((short) 0);
        }

        Machine savedMachine = machineService.save(newMachine);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMachine.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(@Valid @RequestBody MachineRequest request, @PathVariable Integer id) {
        Optional<Machine> MachineToUpdate = machineService.findById(id);
        ProductionCell cell = productionCellService.findById(request.getProductionCellId()).orElse(null);
        Sector sector = sectorService.findById(cell.getSector().getId()).orElse(null);
        ManufacturingUnit unit = manufacturingUnitService.findById(sector.getUnit().getId()).orElse(null);
        MachineModel model = machineModelService.findById(request.getMachineModelId()).orElse(null);

        if(MachineToUpdate.isEmpty()) {
            throw new NotFoundException("Machine id not found - " + id);
        }

        MachineToUpdate = MachineToUpdate.map((MachineUpdated) -> {
            MachineUpdated.setUnit(unit);
            MachineUpdated.setSector(sector);
            MachineUpdated.setProductionCell(cell);
            MachineUpdated.setMachineModel(model);
            MachineUpdated.setCreatedDate(MachineUpdated.getCreatedDate());
            MachineUpdated.setAcquisitionDate(request.getAcquisitionDate());
            MachineUpdated.setTag(request.getTag());
            MachineUpdated.setTechnicalData(request.getTechnicalData());
            MachineUpdated.setStatus(RegisterState.valueOf(request.getStatus()));
            MachineUpdated.setWarrantyExpDate(request.getWarrantyExpDate());

            if (MachineUpdated.getWarrantyExpDate().isAfter(LocalDate.now())) {
                MachineUpdated.setWarranty((short) 1);
            }
            else {
                MachineUpdated.setWarranty((short) 0);
            }
            return MachineUpdated;
        });

        return ResponseEntity.ok(machineService.save(MachineToUpdate.get()));
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
