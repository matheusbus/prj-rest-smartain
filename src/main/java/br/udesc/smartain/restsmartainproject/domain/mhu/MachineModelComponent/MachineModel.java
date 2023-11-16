package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent.MachineModelType;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

//@Entity
//@Table(schema = "mhu", name = "tbmodelomaquina")
public class MachineModel {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "momcodigo")
//    @Comment("Tabela de cadastros de modelos de máquinas")
    private Integer id;

//    @ManyToOne()

    private String model;

    private String dimensions;

    private MachineModelType machineModelType;

}
