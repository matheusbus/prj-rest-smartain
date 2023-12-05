package br.udesc.smartain.restsmartainproject.domain.mhu.MachineManualComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineManualRepository extends JpaRepository<MachineManual, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbmanualmaquina WHERE tbmanualmaquina.mamstatus = ?1",
            nativeQuery = true
    )
    public List<MachineManual> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbmanualmaquina SET tbmanualmaquina.mamstatus = 2 WHERE tbmanualmaquina.mamcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateMachineManualById(Integer id);

}
