package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineModelRepository extends JpaRepository<MachineModel, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbmodelomaquina WHERE tbmodelomaquina.momstatus = ?1",
            nativeQuery = true
    )
    public List<MachineModel> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbmodelomaquina SET tbmodelomaquina.momstatus = 2 WHERE tbmodelomaquina.momcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateMachineModelById(Integer id);

}
