package br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbfornecedor WHERE tbfornecedor.forstatus = ?1",
            nativeQuery = true
    )
    public List<Supplier> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbfornecedor SET tbfornecedor.forstatus = 2 WHERE tbfornecedor.forcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateSupplierById(Integer id);


}
