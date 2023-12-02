package br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbmodelo WHERE tbmodelo.momstatus = ?1",
            nativeQuery = true
    )
    public List<Model> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbmodelo SET tbmodelo.momstatus = 2 WHERE tbmodelo.momcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateModelById(Integer id);

}
