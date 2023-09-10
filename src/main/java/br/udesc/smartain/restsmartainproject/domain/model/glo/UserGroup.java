package br.udesc.smartain.restsmartainproject.domain.model.glo;

import br.udesc.smartain.restsmartainproject.domain.model.glo.User;
import br.udesc.smartain.restsmartainproject.domain.model.states.RegisterDataState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "glo", name = "tbgrupousuario")
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grucodigo")
    private Short id;

    @Column(name = "grunome", length = 100)
    @NotBlank(message = "O nome do grupo não pode ser nulo ou estar vazio.")
    private String name;

    @Column(name = "grudescricao", length = 300)
    @NotBlank(message = "A descrição do grupo não pode ser nulo ou estar vazio.")
    private String description;

    @Column(name = "gruativo")
    private Short active;

    @OneToMany(mappedBy = "userGroup", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public UserGroup(Short id, String name, String description, RegisterDataState active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active.getValue();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
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

    public RegisterDataState getActive() {
        return RegisterDataState.valueOf(active);
    }

    public void setActive(RegisterDataState active) {
        this.active = active.getValue();
    }

    public Set<User> getUsers() {
        return users;
    }

    public boolean addUser(User user) {
        return this.users.add(user);
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return Objects.equals(id, userGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
