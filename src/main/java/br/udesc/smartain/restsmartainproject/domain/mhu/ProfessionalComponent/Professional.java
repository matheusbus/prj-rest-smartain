package br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbprofissional")
@Comment("Tabela de cadastro dos profissionais")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prfcodigo")
    @Comment("Código sequencial do profissional")
    private Integer id;

    @Column(name = "prfnome", length = 150)
    @Comment("Nome do profissional")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "prfdatnasc")
    @Comment("Data de nascimento do profissional")
    private LocalDate birthDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "prfdatcad")
    @Comment("Data de cadastro do profissional")
    private LocalDateTime createdDate;

    @Column(name = "prfcpf")
    @Comment("CPF do profissional")
    private String cpf;


    @Column(name = "prfstatus")
    @Comment("Status do profissional (1-Ativo 2-Inativo)")
    private Short status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usucodigo")
    private User user;

    public Professional() {

    }

    public Professional(Integer id, String name, LocalDate birthDate, LocalDateTime createdDate, String cpf, RegisterState status, User user) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.createdDate = createdDate;
        this.cpf = cpf;
        this.status = status.getValue();
        this.user = user;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        return "Professional{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", createdDate=" + createdDate +
                ", cpf='" + cpf + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professional that = (Professional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
