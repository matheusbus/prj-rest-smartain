package br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbfabricante WHERE tbfabricante.fabstatus = ?1",
            nativeQuery = true
    )
    public List<Manufacturer> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbfabricante SET tbfabricante.fabstatus = 2 WHERE tbfabricante.fabcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateManufacturerById(Integer id);

}
