package br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbequipamento WHERE tbequipamento.eqpstatus = ?1",
            nativeQuery = true
    )
    public List<Equipment> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbequipamento SET tbequipamento.eqpstatus = 2 WHERE tbequipamento.eqpcodigo = ?1",
            nativeQuery = true
    )
    public void inactivateEquipmentById(Integer id);

}
