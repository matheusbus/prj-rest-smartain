package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceIntervention;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceInterventionService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mpp/serviceIntervention")
public class ServiceInterventionController {

    @Autowired
    private ServiceInterventionService serviceInterventionService;

    @GetMapping
    public ResponseEntity<List<ServiceIntervention>> findAll() {
        List<ServiceIntervention> serviceInterventions = serviceInterventionService.findAll();

        if(serviceInterventions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(serviceInterventions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceIntervention> findById(@PathVariable Integer id) {
        Optional<ServiceIntervention> serviceInterventions = serviceInterventionService.findById(id);

        if(serviceInterventions.isEmpty()) {
            throw new NotFoundException("Service Intervention id not found - " + id);
        }

        return ResponseEntity.ok(serviceInterventions.get());
    }

}
