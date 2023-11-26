package br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductionCellRepository extends JpaRepository<ProductionCell, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbcelulcaproducao WHERE tbcelulcaproducao.clpstatus = ?1",
            nativeQuery = true
    )
    public List<ProductionCell> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbcelulcaproducao SET tbcelulcaproducao.clpstatus = 2 WHERE tbcelulcaproducao.clpcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateProductionCellById(Integer id);


}
