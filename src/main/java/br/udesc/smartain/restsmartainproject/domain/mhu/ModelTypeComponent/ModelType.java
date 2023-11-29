package br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import br.udesc.smartain.restsmartainproject.domain.types.DomainModelType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbmodelo")
@Comment("Tabela de cadastro dos modelos")
public class ModelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tmmcodigo")
    @Comment("Código sequencial do modelo")
    private Integer id;

    @Column(name = "tmmdescricao", length = 250, nullable = false)
    @Size(min = 3, max = 250, message = "A descrição do tipo de modelo deve conter entre 3 e 250 caracteres.")
    @Comment("Descrição do tipo de modelo")
    private String description;

    @Column(name = "tmmtipo", nullable = false)
    @Range(min = 1, max = 3, message = "O tipo domínio deve ser um dos valores (1-Máquina, 2-Componente, 3-Equipamento")
    @Comment("Tipo domínio (1-Máquina, 2-Componente, 3-Equipamento)")
    private Short domainType;

    @Column(name = "tmmstatus", nullable = false)
    @Comment("Status do registro (1-Ativo, 2-Inativo)")
    private Short status;

    @JsonIgnore
    @OneToMany(mappedBy = "machineModelType", fetch = FetchType.LAZY)
    private List<MachineModel> machineModels = new ArrayList<>();

    public ModelType() {
    }

    public ModelType(Integer id, String description, DomainModelType domainType, RegisterState status) {
        this.id = id;
        this.description = description;
        this.domainType = domainType.getValue();
        this.status = status.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DomainModelType getDomainType() {
        return DomainModelType.valueOf(domainType);
    }

    public void setDomainType(DomainModelType domainType) {
        this.domainType = domainType.getValue();
    }

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    public List<MachineModel> getMachineModels() {
        return machineModels;
    }

    @Override
    public String toString() {
        return "ModelType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", domainType=" + domainType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelType that = (ModelType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
