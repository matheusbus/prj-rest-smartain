package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationType;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCause;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCauseService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriority;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrder;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderRequest;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderStatus;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriorityService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitation;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationService;
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
@RequestMapping("/api/mpp/serviceOrder")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

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

    @Autowired
    private ServiceCauseService serviceCauseService;

    @Autowired
    private OrderGenerationTypeService orderGenerationTypeService;

    @GetMapping
    public ResponseEntity<List<ServiceOrder>> findAll() {
        List<ServiceOrder> ServiceOrders = serviceOrderService.findAll();

        if(ServiceOrders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ServiceOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrder> findById(@PathVariable Integer id) {
        Optional<ServiceOrder> ServiceOrder = serviceOrderService.findById(id);

        if(ServiceOrder.isEmpty()) {
            throw new NotFoundException("Service Order id not found - " + id);
        }

        return ResponseEntity.ok(ServiceOrder.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<ServiceOrder>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2 && status != 3 && status != 4 && status != 5) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<ServiceOrder> ServiceOrders = serviceOrderService.findAllByStatus(status);
        if(ServiceOrders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ServiceOrders);
    }

    @PostMapping
    public ResponseEntity<ServiceOrder> createServiceOrder(@Valid @RequestBody ServiceOrderRequest request) {
        ServiceOrder newServiceOrder = new ServiceOrder();

        Machine machine = machineService.findById(request.getMachineId()).orElse(null);
        ManufacturingUnit unit = manufacturingUnitService.findById(machine.getUnit().getId()).orElse(null);
        ServicePriority priority = servicePriorityService.findById(request.getPriorityId()).orElse(null);
        User user = userService.findById(request.getUserId()).orElse(null);
        MaintenanceType maintenanceType = maintenanceTypeService.findById(request.getMaintenanceTypeId()).orElse(null);
        ServiceCause cause = serviceCauseService.findById((request.getServiceCauseId() != null) ? request.getServiceCauseId() : 0).orElse(null);
        Optional<ServiceSolicitation> solicitation = serviceSolicitationService.findById(request.getSolicitationId() != null ? request.getSolicitationId() : 0);
        OrderGenerationType orderGenerationType = orderGenerationTypeService.findById(request.getGenerationTypeId()).orElse(null);

        newServiceOrder.setUnit(unit);
        newServiceOrder.setMachine(machine);
        newServiceOrder.setStatus(ServiceOrderStatus.NOT_STARTED);
        newServiceOrder.setServiceCause(cause);
        newServiceOrder.setEstimatedDuration(request.getEstimatedDuration());
        newServiceOrder.setServiceIntervention(null);
        newServiceOrder.setOpeningDate(LocalDateTime.now());
        newServiceOrder.setPriority(priority);
        newServiceOrder.setMaintenanceType(maintenanceType);
        newServiceOrder.setServiceSolicitation(solicitation.orElse(null));
        newServiceOrder.setOpeningUser(user);
        newServiceOrder.setGenerationType(orderGenerationType);
        //newServiceOrder.setMaintenancePlan();

        ServiceOrder savedServiceOrder = serviceOrderService.save(newServiceOrder);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedServiceOrder.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrder> updateServiceOrder(@Valid @RequestBody ServiceOrderRequest request, @PathVariable Integer id) {
        Optional<ServiceOrder> serviceOrderToUpdate = serviceOrderService.findById(id);

        if(serviceOrderToUpdate.isEmpty()) {
            throw new NotFoundException("Service Order id not found - " + id);
        }


        Machine machine = machineService.findById(request.getMachineId()).orElse(null);
        ManufacturingUnit unit = manufacturingUnitService.findById(machine.getUnit().getId()).orElse(null);
        ServicePriority priority = servicePriorityService.findById(request.getPriorityId()).orElse(null);
        MaintenanceType maintenanceType = maintenanceTypeService.findById(request.getMaintenanceTypeId()).orElse(null);
        ServiceCause cause = serviceCauseService.findById((request.getServiceCauseId() != null) ? request.getServiceCauseId() : 0).orElse(null);
        Optional<ServiceSolicitation> solicitation = serviceSolicitationService.findById(request.getSolicitationId() != null ? request.getSolicitationId() : 0);
        OrderGenerationType orderGenerationType = orderGenerationTypeService.findById(request.getGenerationTypeId()).orElse(null);

        serviceOrderToUpdate = serviceOrderToUpdate.map((serviceOrderUpdated) -> {
            serviceOrderUpdated.setUnit(unit);
            serviceOrderUpdated.setMachine(machine);
            serviceOrderUpdated.setStatus(ServiceOrderStatus.valueOf(request.getStatus()));
            serviceOrderUpdated.setServiceCause(cause);
            serviceOrderUpdated.setEstimatedDuration(request.getEstimatedDuration());
            serviceOrderUpdated.setServiceIntervention(null);
            serviceOrderUpdated.setOpeningDate(serviceOrderUpdated.getOpeningDate());
            serviceOrderUpdated.setPriority(priority);
            serviceOrderUpdated.setMaintenanceType(maintenanceType);
            serviceOrderUpdated.setServiceSolicitation(solicitation.orElse(null));
            serviceOrderUpdated.setOpeningUser(serviceOrderUpdated.getOpeningUser());
            serviceOrderUpdated.setGenerationType(orderGenerationType);
            serviceOrderUpdated.setMaintenancePlan(serviceOrderUpdated.getMaintenancePlan());
            return serviceOrderUpdated;
        });

        return ResponseEntity.ok(serviceOrderService.save(serviceOrderToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<ServiceOrder> ServiceOrder = serviceOrderService.findById(id);

        if(ServiceOrder.isEmpty()) {
            throw new NotFoundException("Service Order id not found - " + id);
        }
        return ResponseEntity.ok(serviceOrderService.suspendServiceSolicitation(ServiceOrder.get()));
    }




}
