package br.udesc.smartain.restsmartainproject.controller.glo;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent.Customer;
import br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/glo/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Customer> getCustomer() {

        if(customerService.getCustomer().isEmpty()) {
            throw new NotFoundException("The customer is not yet defined.");
        }

        return ResponseEntity.ok(customerService.getCustomer().get());
    }

}
