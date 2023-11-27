package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.Professional;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.ProfessionalService;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriority;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriorityService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitation;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationRequest;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationStatus;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mpp/serviceSolicitation")
public class ServiceSolicitationController {

    @Autowired
    private ServiceSolicitationService serviceSolicitationService;

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private ServicePriorityService servicePriorityService;

    @Autowired
    private ServiceSymptomService serviceSymptomService;

    @Autowired
    private UserService userService;

    @Autowired
    private MaintenanceTypeService maintenanceTypeService;

    @GetMapping
    public ResponseEntity<List<ServiceSolicitation>> findAll() {
        List<ServiceSolicitation> serviceSolicitations = serviceSolicitationService.findAll();

        if(serviceSolicitations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(serviceSolicitations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceSolicitation> findById(@PathVariable Integer id) {
        Optional<ServiceSolicitation> serviceSolicitation = serviceSolicitationService.findById(id);

        if(serviceSolicitation.isEmpty()) {
            throw new NotFoundException("Service Solicitation id not found - " + id);
        }

        return ResponseEntity.ok(serviceSolicitation.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<ServiceSolicitation>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2 && status != 3) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<ServiceSolicitation> serviceSolicitations = serviceSolicitationService.findAllByStatus(status);
        if(serviceSolicitations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(serviceSolicitations);
    }

    @PostMapping
    public ResponseEntity<ServiceSolicitation> createServiceSolicitation(@Valid @RequestBody ServiceSolicitationRequest request) {
        ServiceSolicitation newServiceSolicitation = new ServiceSolicitation();

        Machine machine = machineService.findById(request.getMachineId()).get();
        ManufacturingUnit unit = manufacturingUnitService.findById(machine.getUnit().getId()).orElseThrow();
        ServicePriority priority = servicePriorityService.findById(request.getPriorityId()).get();
        ServiceSymptom symptom = serviceSymptomService.findById(request.getSymptomId()).get();
        User user = userService.findById(request.getResponsibleUserId()).get();
        MaintenanceType maintenanceType = maintenanceTypeService.findById(request.getMaintenanceTypeId()).get();

        newServiceSolicitation.setUnit(unit);
        newServiceSolicitation.setMachine(machine);
        newServiceSolicitation.setDescription(request.getDescription());
        newServiceSolicitation.setOpeningDate(LocalDateTime.now());
        newServiceSolicitation.setStatus(ServiceSolicitationStatus.valueOf(request.getStatus()));
        newServiceSolicitation.setPriority(priority);
        newServiceSolicitation.setSymptom(symptom);
        newServiceSolicitation.setResponsibleUser(user);
        newServiceSolicitation.setMaintenanceType(maintenanceType);

        ServiceSolicitation savedServiceSolicitation = serviceSolicitationService.save(newServiceSolicitation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedServiceSolicitation.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceSolicitation> updateSupplier(@Valid @RequestBody ServiceSolicitationRequest request, @PathVariable Integer id) {
        Optional<ServiceSolicitation> serviceSolicitationToUpdate = serviceSolicitationService.findById(id);

        if(serviceSolicitationToUpdate.isEmpty()) {
            throw new NotFoundException("Service Solicitation id not found - " + id);
        }

        Machine machine = machineService.findById(request.getMachineId()).get();
        ManufacturingUnit unit = manufacturingUnitService.findById(machine.getUnit().getId()).orElseThrow();
        ServicePriority priority = servicePriorityService.findById(request.getPriorityId()).get();
        ServiceSymptom symptom = serviceSymptomService.findById(request.getSymptomId()).get();
        MaintenanceType maintenanceType = maintenanceTypeService.findById(request.getMaintenanceTypeId()).get();

        serviceSolicitationToUpdate = serviceSolicitationToUpdate.map((serviceSolicitationUpdated) -> {
            serviceSolicitationUpdated.setUnit(unit);
            serviceSolicitationUpdated.setMachine(machine);
            serviceSolicitationUpdated.setDescription(request.getDescription());
            serviceSolicitationUpdated.setOpeningDate(serviceSolicitationUpdated.getOpeningDate());
            serviceSolicitationUpdated.setStatus(ServiceSolicitationStatus.valueOf(request.getStatus()));
            serviceSolicitationUpdated.setPriority(priority);
            serviceSolicitationUpdated.setSymptom(symptom);
            serviceSolicitationUpdated.setResponsibleUser(serviceSolicitationUpdated.getResponsibleProfessional());
            serviceSolicitationUpdated.setMaintenanceType(maintenanceType);
            return serviceSolicitationUpdated;
        });

        return ResponseEntity.ok(serviceSolicitationService.save(serviceSolicitationToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<ServiceSolicitation> serviceSolicitation = serviceSolicitationService.findById(id);

        if(serviceSolicitation.isEmpty()) {
            throw new NotFoundException("Service Solicitation id not found - " + id);
        }
        return ResponseEntity.ok(serviceSolicitationService.cancelServiceSolicitation(serviceSolicitation.get()));
    }

}
