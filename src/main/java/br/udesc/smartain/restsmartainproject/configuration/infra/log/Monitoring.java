package br.udesc.smartain.restsmartainproject.configuration.infra.log;

import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "glo", name = "tbmonitoracao")
public class Monitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mtrcodigo")
    @Comment(value = "Código sequencial da tabela")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "usucodigo")
    @Comment(value = "Código do usuário")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @ColumnDefault(value = "now()")
    @Column(name = "mtrdathora")
    @Comment(value = "Data e hora da monitoração")
    private LocalDateTime dateTime;

    @NotNull
    @Column(name = "opecodigo")
    @Comment(value = "Código da operação executada")
    private Short operation;

    @NotBlank
    @Column(name = "mtrdescricao")
    @Comment(value = "Descritivo da monitoração")
    private String description;

    public Monitoring() {

    }

    public Monitoring(Long id, User user, LocalDateTime dateTime, Short operation, String description) {
        this.id = id;
        this.user = user;
        this.dateTime = dateTime;
        this.operation = operation;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Short getOperation() {
        return operation;
    }

    public void setOperation(Short operation) {
        this.operation = operation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Monitoring{" +
                "id=" + id +
                ", user=" + user +
                ", dateTime=" + dateTime +
                ", operation=" + operation +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitoring that = (Monitoring) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
