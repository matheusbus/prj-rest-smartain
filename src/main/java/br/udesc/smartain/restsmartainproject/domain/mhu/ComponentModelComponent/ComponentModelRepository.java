package br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComponentModelRepository extends JpaRepository<ComponentModel, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbmodelocomponente WHERE tbmodelocomponente.mocstatus = ?1",
            nativeQuery = true
    )
    public List<ComponentModel> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbmodelocomponente SET tbmodelocomponente.mocstatus = 2 WHERE tbmodelocomponente.moccodigo = ?1",
            nativeQuery = true
    )
    public void inactivateComponentModelById(Integer id);

}
