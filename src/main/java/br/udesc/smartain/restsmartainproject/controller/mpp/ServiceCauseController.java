package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCause;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mpp/serviceCause")
public class ServiceCauseController {

    @Autowired
    private ServiceCauseService serviceCauseService;

    @GetMapping
    public ResponseEntity<List<ServiceCause>> findAll() {
        List<ServiceCause> serviceCauses = serviceCauseService.findAll();

        if(serviceCauses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(serviceCauses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCause> findById(@PathVariable Integer id) {
        Optional<ServiceCause> serviceCause = serviceCauseService.findById(id);

        if(serviceCause.isEmpty()) {
            throw new NotFoundException("Service Cause id not found - " + id);
        }

        return ResponseEntity.ok(serviceCause.get());
    }


}
