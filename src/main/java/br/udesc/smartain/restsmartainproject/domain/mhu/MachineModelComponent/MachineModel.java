package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent.MachineModelType;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbmodelomaquina")
@Comment("Tabela de cadastros de modelos de máquinas")
public class MachineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "momcodigo")
    @Comment("Código sequencial do modelo de máquina")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fabcodigo")
    @Comment("Código do fabricante do modelo")
    private Manufacturer manufacturer;

    @Column(name = "mommodelo", length = 250)
    @Comment("Modelo da máquina")
    private String model;

    @Column(name = "momdimensoes", length = 250)
    @Comment("Dimensões do modelo de máquina")
    private String dimensions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tmmcodigo")
    @Comment("Código do tipo de modelo")
    private MachineModelType machineModelType;

    @Column(name = "momstatus")
    @Comment("Status do Modelo (1-Ativo 2-Inativo)")
    @Range(min = 1, max = 2)
    private Short status;

    public MachineModel() {

    }

    public MachineModel(Integer id, Manufacturer manufacturer, String model, String dimensions, MachineModelType machineModelType, RegisterState status) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.dimensions = dimensions;
        this.machineModelType = machineModelType;
        this.status = status.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public MachineModelType getMachineModelType() {
        return machineModelType;
    }

    public void setMachineModelType(MachineModelType machineModelType) {
        this.machineModelType = machineModelType;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MachineModel{" +
                "id=" + id +
                ", manufacturer=" + manufacturer +
                ", model='" + model + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", machineModelType=" + machineModelType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MachineModel that = (MachineModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
