package br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(
            value = "SELECT * FROM glo.tbcliente WHERE tbcliente.clicodigo = ?1",
            nativeQuery = true
    )
    public Optional<Customer> findById(Integer id);

    @Query(
            value = "SELECT * FROM glo.tbcliente WHERE tbcliente.clirazaosocial ILIKE %?1%",
            nativeQuery = true
    )
    public Optional<List<Customer>> findByCorporateReason(String corporateReason);

    @Query(
            value = "SELECT * FROM glo.tbcliente WHERE tbcliente.clicnpjprincipal ILIKE ?1",
            nativeQuery = true
    )
    public Optional<Customer> findByCnpj(String cnpj);

}
