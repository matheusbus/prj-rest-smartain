package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationType;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationTypeService;
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
@RequestMapping("/api/mpp/orderGenerationType")
public class OrderGenerationTypeController {

    @Autowired
    private OrderGenerationTypeService orderGenerationTypeService;

    @GetMapping
    public ResponseEntity<List<OrderGenerationType>> findAll() {
        List<OrderGenerationType> orderGenerationTypes = orderGenerationTypeService.findAll();

        if(orderGenerationTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orderGenerationTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderGenerationType> findById(@PathVariable Integer id) {
        Optional<OrderGenerationType> orderGenerationTypes = orderGenerationTypeService.findById(id);

        if(orderGenerationTypes.isEmpty()) {
            throw new NotFoundException("Order Generation Type id not found - " + id);
        }

        return ResponseEntity.ok(orderGenerationTypes.get());
    }

}
