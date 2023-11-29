package br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentModelComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentModelRepository extends JpaRepository<EquipmentModel, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbmodeloequipamento WHERE tbmodeloequipamento.moestatus = ?1",
            nativeQuery = true
    )
    public List<EquipmentModel> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbmodeloequipamento SET tbmodeloequipamento.moestatus = 2 WHERE tbmodeloequipamento.moecodigo = ?1",
            nativeQuery = true
    )
    public void inactivateEquipmentModelById(Integer id);

}
