package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "grupapel", nullable = false)
    private String role;

    @OneToMany(mappedBy = "userGroup", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public UserGroup() {

    }

    public UserGroup(Short id, String name, String description, RegisterState active, String role) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.role = role;
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

    public RegisterState getActive() {
        return RegisterState.valueOf(active);
    }

    public void setActive(RegisterState active) {
        this.active = active.getValue();
    }

    public String getRole() { return role; }

    public void setRole(String tole) { this.role = role; }

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
