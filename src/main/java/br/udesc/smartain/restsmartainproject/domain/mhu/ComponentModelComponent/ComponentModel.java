package br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent.ModelType;
import br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent.Supplier;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbmodelocomponente")
@Comment("Tabela de cadastro dos modelos de componentes")
public class ComponentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moccodigo")
    @Comment("Código sequencial do modelo de componente")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fabcodigo")
    @Comment("Código do fabricante do modelo")
    private Manufacturer manufacturer;

    @Column(name = "mocmodelo", length = 250)
    @Comment("Modelo de componente")
    private String model;

    @Column(name = "mocdimensoes", length = 250)
    @Comment("Dimensões do modelo de componente")
    private String dimensions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tmmcodigo")
    @Comment("Código do tipo de modelo")
    private ModelType modelType;

    @Column(name = "mocstatus")
    @Comment("Status do Modelo (1-Ativo 2-Inativo)")
    @Range(min = 1, max = 2)
    private Short status;

    public ComponentModel() {

    }

    public ComponentModel(Integer id, Manufacturer manufacturer, String model, String dimensions, ModelType modelType, RegisterState status) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.dimensions = dimensions;
        this.modelType = modelType;
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

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    @Override
    public String toString() {
        return "ComponentModel{" +
                "id=" + id +
                ", manufacturer=" + manufacturer +
                ", model='" + model + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", modelType=" + modelType +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentModel that = (ComponentModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
