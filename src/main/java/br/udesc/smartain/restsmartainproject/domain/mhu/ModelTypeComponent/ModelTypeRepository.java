package br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelTypeRepository extends JpaRepository<ModelType, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbtipomodelomaquina WHERE tbtipomodelomaquina.tmmstatus = ?1",
            nativeQuery = true
    )
    public List<ModelType> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbtipomodelomaquina SET tbtipomodelomaquina.tmmstatus = 2 WHERE tbtipomodelomaquina.tmmcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateMachineModelTypeById(Integer id);

}
