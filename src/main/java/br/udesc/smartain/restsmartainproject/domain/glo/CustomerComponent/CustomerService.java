package br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomer() {
        return customerRepository.findAll().stream().findFirst();
    }

    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    public Optional<List<Customer>> findByCorporateReason(String corporateReason) {
        return customerRepository.findByCorporateReason(corporateReason);
    }

    public Optional<Customer> findByCnpj(String cnpj) {
        return customerRepository.findByCnpj(cnpj);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

}
