package br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbcomponente WHERE tbcomponente.comstatus = ?1",
            nativeQuery = true
    )
    public List<Component> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbcomponente SET tbcomponente.comstatus = 2 WHERE tbcomponente.comcodigo = ?1",
            nativeQuery = true
    )
    public void inactivateComponentById(Integer id);

}
