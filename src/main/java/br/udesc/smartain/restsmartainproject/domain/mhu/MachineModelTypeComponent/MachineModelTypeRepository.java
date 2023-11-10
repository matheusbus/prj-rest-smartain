package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineModelTypeRepository extends JpaRepository<MachineModelType, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbtipomodelomaquina WHERE tbtipomodelomaquina.tmmstatus = ?1",
            nativeQuery = true
    )
    public List<MachineModelType> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbtipomodelomaquina SET tbtipomodelomaquina.tmmstatus = 2 WHERE tbtipomodelomaquina.tmmcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateMachineModelTypeById(Integer id);

}
