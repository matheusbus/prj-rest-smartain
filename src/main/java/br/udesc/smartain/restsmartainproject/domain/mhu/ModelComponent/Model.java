package br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent.Component;
import br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent.Equipment;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import br.udesc.smartain.restsmartainproject.domain.types.DomainModelType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbmodelo")
@Comment("Tabela de cadastros de modelos")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "momcodigo")
    @Comment("Código sequencial do modelo de máquina")
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabcodigo")
    @Comment("Código do fabricante do modelo")
    private Manufacturer manufacturer;

    @Column(name = "mommodelo", length = 250)
    @Comment("Modelo da máquina")
    private String model;

    @Column(name = "momdimensoes", length = 250)
    @Comment("Dimensões do modelo de máquina")
    private String dimensions;

    @Column(name = "tmmtipo", nullable = false)
    @Range(min = 1, max = 3, message = "O tipo domínio deve ser um dos valores (1-Máquina, 2-Componente, 3-Equipamento")
    @Comment("Tipo domínio (1-Máquina, 2-Componente, 3-Equipamento)")
    private Short domainType;

    @Column(name = "momstatus")
    @Comment("Status do Modelo (1-Ativo 2-Inativo)")
    @Range(min = 1, max = 2)
    private Short status;

    @JsonIgnore
    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<Component> components = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<Machine> machines = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<Equipment> equipments = new ArrayList<>();


    public Model() {

    }

    public Model(Integer id, Manufacturer manufacturer, String model, String dimensions, DomainModelType domainType, RegisterState status) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.dimensions = dimensions;
        this.domainType = domainType.getValue();
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

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    public DomainModelType getDomainType() {
        return DomainModelType.valueOf(domainType);
    }

    public void setDomainType(DomainModelType domainType) {
        this.domainType = domainType.getValue();
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", manufacturer=" + manufacturer +
                ", model='" + model + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", domainType=" + domainType +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model that = (Model) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
