package br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbmarca")
@Comment("Tabela de cadastro de marcas")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marcodigo")
    @Comment("Código sequencial da marca")
    private Integer id;

    @Column(name = "marnome", nullable = false, length = 150)
    @NotBlank
    @Size(min = 3, max = 150, message = "O nome da marca deve conter entre 3 e 150 caracteres.")
    @Comment("Nome da marca")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "mardatcad", nullable = false)
    @Comment("Data de cadastro da marca")
    private LocalDate createdDate;

    @Column(name = "marstatus", nullable = false)
    @Comment("Status da marca (1- Ativo, 2- Inativo)")
    @Range(min = 1, max = 2, message = "O status deve assumir somentes os valores  1 ou 2")
    private Short status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usucodigo", nullable = false)
    @Comment("Código do usuário de cadastro da marca")
    private User user;

    public Brand() {

    }

    public Brand(Integer id, String name, LocalDate createdDate, User user, RegisterState status) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.user = user;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
