package br.udesc.smartain.restsmartainproject.domain.mhu.AlertComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlan;
import br.udesc.smartain.restsmartainproject.domain.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbalerta")
@Comment("Tabela dos alertas emitidos")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alecodigo")
    @Comment("Código sequencial do Alerta")
    private Integer id;

    @Column(name = "aletipo")
    @Comment("Tipo do Alerta (1- Alerta de Garantia, 2- Alerta cadastrado pelo Usuário)")
    private Short type;

    @Column(name = "aletitulo", length = 100, nullable = false)
    @Comment("Título do alerta")
    private String title;

    @Column(name = "aledescricao", nullable = false)
    @Comment("Descrição do alerta")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "usucodigo")
    @Comment("Código do usuário que cadastrou o alerta (nulo quando alerta de garantia)")
    private User createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "aledatcad")
    @Comment("Data da emissão/cadastro do alerta")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "aledatexp")
    @Comment("Data de expiração do alerta")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "maqcodigo", nullable = true)
    @Comment("Código da máquina (quando alerta emitido para garantia da máquina)")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "plmcodigo", nullable = true)
    @Comment("Código do plano de manutenção (quando alerta é emitido para plano de manutenção)")
    private MaintenancePlan plan;

    @Column(name = "alestatus")
    @Comment("Status do alerta (1- Pendente, 2- Atendido)")
    private Short status;

    public Alert() {

    }

    public Alert(Integer id, AlertType type, String title, String description, User createdUser, LocalDateTime createdDate, LocalDate expirationDate, Machine machine, MaintenancePlan plan, AlertStatus status) {
        this.id = id;
        this.type = type.getValue();
        this.title = title;
        this.description = description;
        this.createdUser = createdUser;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
        this.machine = machine;
        this.plan = plan;
        this.status = status.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AlertType getType() {
        return AlertType.valueOf(type);
    }

    public void setType(AlertType type) {
        this.type = type.getValue();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AlertStatus getStatus() {
        return AlertStatus.valueOf(status);
    }

    public void setStatus(AlertStatus status) {
        this.status = status.getValue();
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public MaintenancePlan getPlan() {
        return plan;
    }

    public void setPlan(MaintenancePlan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdUser=" + createdUser +
                ", createdDate=" + createdDate +
                ", expirationDate=" + expirationDate +
                ", machine=" + machine +
                ", plan=" + plan +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return Objects.equals(id, alert.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
