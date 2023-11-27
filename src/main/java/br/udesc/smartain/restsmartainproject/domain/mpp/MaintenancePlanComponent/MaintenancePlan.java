package br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(schema = "mpp", name = "tbplanomanutencao")
@Comment("Tabela que armazena os planos de manutencao")
public class MaintenancePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plmcodigo")
    @Comment("Código sequencial do plano de manutenção")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unfcodigo")
    @Comment("Código da unidade fabril")
    private ManufacturingUnit unit;

    @Column(name = "plmstatus")
    @Comment("Status do plano de manutenção (1-Não iniciado, 2-Em progresso, 3-Finalizado, 4-Cancelado)")
    private Short status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usucodigo")
    @Comment("Código do usuário responsável pelo plano de manutenção")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "plmdatcad")
    @Comment("Data de cadastro/geração do plano de manutenção")
    private LocalDateTime createdDate;

    public MaintenancePlan() {

    }

    public MaintenancePlan(Integer id, ManufacturingUnit unit, Short status, User user, LocalDateTime createdDate) {
        this.id = id;
        this.unit = unit;
        this.status = status;
        this.user = user;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ManufacturingUnit getUnit() {
        return unit;
    }

    public void setUnit(ManufacturingUnit unit) {
        this.unit = unit;
    }

    public MaintenancePlanStatus getStatus() {
        return MaintenancePlanStatus.valueOf(status);
    }

    public void setStatus(MaintenancePlanStatus status) {
        this.status = status.getValue();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}