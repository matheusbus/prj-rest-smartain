package br.udesc.smartain.restsmartainproject.domain.mhu.RoleComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbcargo")
@Comment(value = "Tabela de cadastro dos cargos dos profissionais")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "carcodigo")
    @Comment(value = "Código sequencial do cargo")
    private Integer id;

    @Column(name = "carnome")
    @Comment(value = "Nome do cargo")
    @NotBlank
    @Size(min = 3, max = 100, message = "O nome do cargo deve conter entre 3 e 100 caracteres.")
    private String name;

    @Column(name = "cardescricao")
    @Comment(value = "Descrição do cargo")
    @NotBlank
    @Size(min = 3, max = 250, message = "A descrição do cargo deve conter entre 3 e 250 caracteres.")
    private String description;

    @Column(name = "carstatus")
    @Comment(value = "Status do cargo (1-Ativo, 2-Inativo)")
    @Range(min = 1, max = 2)
    private Short status;

    public Role() {

    }

    public Role(Integer id, String name, String description, Short status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
