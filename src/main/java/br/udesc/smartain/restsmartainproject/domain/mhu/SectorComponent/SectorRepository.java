package br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbsetor WHERE tbsetor.setstatus = ?1",
            nativeQuery = true
    )
    public List<Sector> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbsetor SET tbsetor.setstatus = 2 WHERE tbsetor.setcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateShiftById(Integer id);


}
