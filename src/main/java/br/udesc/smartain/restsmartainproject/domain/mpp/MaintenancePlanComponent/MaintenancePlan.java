package br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbplanomanutencao")
@Comment("Tabela que armazena os planos de manutencao")
public class MaintenancePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plmcodigo")
    @Comment("Código sequencial do plano de manutenção")
    private Integer id;

    @Column(name = "plmnome")
    @Size(min = 3)
    @Comment("Nome do Plano")
    private String name;

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

    @JsonIgnore
    @OneToMany(mappedBy = "maintenancePlan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServiceOrder> orders = new ArrayList<>();

    public MaintenancePlan() {

    }

    public MaintenancePlan(Integer id, String name, ManufacturingUnit unit, MaintenancePlanStatus status, User user, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.status = status.getValue();
        this.user = user;
        this.createdDate = createdDate;
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

    public List<ServiceOrder> getOrders() {
        return orders;
    }

    public void addServiceOrder(ServiceOrder serviceOrder) {
        this.orders.add(serviceOrder);
        serviceOrder.setMaintenancePlan(this);
    }

    @Override
    public String toString() {
        return "MaintenancePlan{" +
                "id=" + id +
                ", nome=" + name +
                ", unit=" + unit +
                ", status=" + status +
                ", user=" + user +
                ", createdDate=" + createdDate +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenancePlan that = (MaintenancePlan) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}