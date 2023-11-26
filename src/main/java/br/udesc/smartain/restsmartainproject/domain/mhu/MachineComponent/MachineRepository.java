package br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbmaquina WHERE tbmaquina.maqstatus = ?1",
            nativeQuery = true
    )
    public List<Machine> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbmaquina SET tbmaquina.maqstatus = 2 WHERE tbmaquina.maqcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateMachineById(Integer id);

}
