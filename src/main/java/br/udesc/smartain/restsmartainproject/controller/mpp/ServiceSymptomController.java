package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent.Supplier;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mpp/serviceSymptom")
public class ServiceSymptomController {

    @Autowired
    private ServiceSymptomService serviceSymptomService;

    @GetMapping
    public ResponseEntity<List<ServiceSymptom>> findAll() {
        List<ServiceSymptom> serviceSymptoms = serviceSymptomService.findAll();

        if(serviceSymptoms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(serviceSymptoms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceSymptom> findById(@PathVariable Integer id) {
        Optional<ServiceSymptom> serviceSymptom = serviceSymptomService.findById(id);

        if(serviceSymptom.isEmpty()) {
            throw new NotFoundException("Service Symptom id not found - " + id);
        }

        return ResponseEntity.ok(serviceSymptom.get());
    }



}
