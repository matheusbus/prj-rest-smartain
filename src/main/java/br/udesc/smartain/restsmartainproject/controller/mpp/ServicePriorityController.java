package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriority;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mpp/servicePriority")
public class ServicePriorityController {

    @Autowired
    private ServicePriorityService servicePriorityService;

    @GetMapping
    public ResponseEntity<List<ServicePriority>> findAll() {
        List<ServicePriority> servicePriorities = servicePriorityService.findAll();

        if(servicePriorities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(servicePriorities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicePriority> findById(@PathVariable Integer id) {
        Optional<ServicePriority> servicePriority = servicePriorityService.findById(id);

        if(servicePriority.isEmpty()) {
            throw new NotFoundException("Service Priority id not found - " + id);
        }

        return ResponseEntity.ok(servicePriority.get());
    }

}
