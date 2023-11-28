package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserService;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlan;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlanRequest;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlanService;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlanStatus;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrder;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderRequest;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mpp/maintenancePlan")
public class MaintenancePlanController {

    @Autowired
    private MaintenancePlanService maintenancePlanService;

    @Autowired
    private UserService userService;

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @Autowired
    private ServiceOrderService serviceOrderService;

    @GetMapping
    public ResponseEntity<List<MaintenancePlan>> findAll() {
        List<MaintenancePlan> maintenancePlans = maintenancePlanService.findAll();

        if(maintenancePlans.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(maintenancePlans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenancePlan> findById(@PathVariable Integer id) {
        Optional<MaintenancePlan> maintenancePlan = maintenancePlanService.findById(id);

        if(maintenancePlan.isEmpty()) {
            throw new NotFoundException("Maintenance Plan id not found - " + id);
        }

        return ResponseEntity.ok(maintenancePlan.get());
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<ServiceOrder>> getOrdersByPlan(@PathVariable Integer id) {
        Optional<MaintenancePlan> maintenancePlan = maintenancePlanService.findById(id);

        if(maintenancePlan.isEmpty()) {
            throw new NotFoundException("Maintenance Plan id not found - " + id);
        }

        return ResponseEntity.ok(maintenancePlan.get().getOrders());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<MaintenancePlan>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2 && status != 3 && status != 4) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<MaintenancePlan> maintenancePlans = maintenancePlanService.findAllByStatus(status);
        if(maintenancePlans.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maintenancePlans);
    }

    @PostMapping
    public ResponseEntity<MaintenancePlan> createMaintenancePlan(@Valid @RequestBody MaintenancePlanRequest request) {
        MaintenancePlan maintenancePlan = new MaintenancePlan();

        User user = userService.findById(request.getUserId()).get();
        ManufacturingUnit unit = manufacturingUnitService.findById(request.getUnitId()).get();

        maintenancePlan.setCreatedDate(request.getCreatedDate());
        maintenancePlan.setUser(user);
        maintenancePlan.setUnit(unit);
        maintenancePlan.setStatus(MaintenancePlanStatus.NOT_STARTED);

        MaintenancePlan savedMaintenancePlan = maintenancePlanService.save(maintenancePlan);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMaintenancePlan.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/addOrder")
    public ResponseEntity<MaintenancePlan> addOrderToPlan(@PathVariable Integer planId, @Valid @RequestBody ServiceOrderRequest request) {
        Optional<MaintenancePlan> maintenancePlan = maintenancePlanService.findById(planId);
        if(maintenancePlan.isEmpty()) {
            throw new NotFoundException("Maintenance Plan id not found - " + planId);
        }

        Optional<ServiceOrder> serviceOrder = serviceOrderService.findById(request.getId());
        if(serviceOrder.isEmpty()) {
            throw new NotFoundException("Service Order id not found - " + request.getId());
        }

        maintenancePlan.get().addServiceOrder(serviceOrder.get());
        MaintenancePlan savedMaintenancePlan = maintenancePlanService.save(maintenancePlan.get());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/orders")
                .buildAndExpand(savedMaintenancePlan.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenancePlan> updateMaintenancePlan(@PathVariable Integer id, @Valid @RequestBody MaintenancePlanRequest request) {
        Optional<MaintenancePlan> maintenancePlanToUpdate = maintenancePlanService.findById(id);

        if(maintenancePlanToUpdate.isEmpty()) {
            throw new NotFoundException("Maintenance Plan id not found - " + id);
        }

        User user = userService.findById(request.getUserId()).get();
        ManufacturingUnit unit = manufacturingUnitService.findById(request.getUnitId()).get();

        maintenancePlanToUpdate = maintenancePlanToUpdate.map((maintenancePlanUpdated) -> {
            maintenancePlanUpdated.setUser(user);
            maintenancePlanUpdated.setCreatedDate(request.getCreatedDate());
            maintenancePlanUpdated.setStatus(MaintenancePlanStatus.valueOf(request.getStatus()));
            maintenancePlanUpdated.setUnit(unit);
            return maintenancePlanUpdated;
        });

        return ResponseEntity.ok(maintenancePlanService.save(maintenancePlanToUpdate.get()));
    }

}
