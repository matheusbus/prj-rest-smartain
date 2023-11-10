package br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbturno WHERE tbturno.turstatus = ?1",
            nativeQuery = true
    )
    public List<Shift> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbturno SET tbturno.turstatus = 2 WHERE tbturno.turcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateShiftById(Integer id);

}
