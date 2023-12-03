package br.udesc.smartain.restsmartainproject.domain.mhu.AlertComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbalerta WHERE tbalerta.alestatus = ?1",
            nativeQuery = true
    )
    public List<Alert> findAllByStatus(Short status);


    @Query(
            value = "SELECT * FROM mhu.tbalerta WHERE tbalerta.aletipo = ?1",
            nativeQuery = true
    )
    public List<Alert> findAllByType(Short type);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbalerta SET tbalerta.alestatus = 2 WHERE tbalerta.alecodigo = ?1",
            nativeQuery = true
    )
    public void completeAlertById(Integer id);


}
