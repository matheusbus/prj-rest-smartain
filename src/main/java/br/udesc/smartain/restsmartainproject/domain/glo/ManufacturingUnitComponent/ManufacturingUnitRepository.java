package br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ManufacturingUnitRepository extends JpaRepository<ManufacturingUnit, Integer> {

    @Query(
            value = "SELECT * FROM glo.tbunidadefabril",
            nativeQuery = true
    )
    public Optional<List<ManufacturingUnit>> findAllUnits();

    @Query(
            value = "SELECT * FROM glo.tbunidadefabril WHERE tbunidadefabril.ufbstatus = ?1",
            nativeQuery = true
    )
    public Optional<List<ManufacturingUnit>> findAllByStatus(Short status);

    @Query(
            value = "SELECT * FROM glo.tbunidadefabril WHERE tbunidadefabril.ufbcodigo = ?1",
            nativeQuery = true
    )
    public Optional<ManufacturingUnit> findById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE glo.tbunidadefabril SET tbunidadefabril.ufbstatus = 2 WHERE tbunidadefabril.ufbcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateUnitById(Integer id);

}
