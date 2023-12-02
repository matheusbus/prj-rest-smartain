package br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent.Model;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.hibernate.annotations.Comment;

import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.Brand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbequipamento")
@Comment("Tabela de cadastro dos equipamentos das máquinas")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eqpcodigo")
    @Comment("Código sequencial do equipamento")
    private Integer id;

    @Column(name = "eqpnome", length = 250)
    @Size(min = 3, max = 250, message = "O nome do equipamento deve conter entre 3 e 250 caracteres.")
    @Comment("Nome do equipamento")
    private String name;

    @Column(name = "eqpdadostec")
    @Size(min = 3, message = "Os dados técnicos do equipamento deve conter entre 3 e 1000 caracteres.")
    @Comment("Dados e especificações técnicas do equipamento")
    private String technicalData;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "marcodigo")
    @Comment("Código da marca")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "momcodigo", nullable = false)
    @Comment("Código do modelo de equipamento")
    private Model model;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "maqcodigo")
    private Machine machine;

    @Column(name = "eqpstatus")
    @Comment("Status do equipamento (1-Ativo 2-Inativo)")
    private Short status;

    public Equipment() {

    }

    public Equipment(Integer id, String name, String technicalData, Brand brand, Model model, Machine machine, RegisterState status) {
        this.id = id;
        this.name = name;
        this.technicalData = technicalData;
        this.brand = brand;
        this.model = model;
        this.machine = machine;
        this.status = status.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnicalData() {
        return technicalData;
    }

    public void setTechnicalData(String technicalData) {
        this.technicalData = technicalData;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", technicalData='" + technicalData + '\'' +
                ", brand=" + brand +
                ", model=" + model +
                ", machine=" + machine +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
