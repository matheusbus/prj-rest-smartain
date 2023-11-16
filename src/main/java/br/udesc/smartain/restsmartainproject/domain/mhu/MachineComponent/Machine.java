package br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;

//@Entity
//@Table(schema = "mhu", name = "tbmaquina")
//@Comment("Tabela de cadastros de máquinas dos setores/células de produção")
public class Machine {

    private Integer id;

    private Sector sector;

    private ManufacturingUnit unit;

    private String technicalData;



}
