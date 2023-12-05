package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlan;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationType;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCause;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceIntervention;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriority;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitation;
import br.udesc.smartain.restsmartainproject.domain.utils.LocalDateTimeSerializer;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbservicoordem")
@Comment("Tabela de ordens de serviços de manutencao")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "svocodigo")
    @Comment("Código sequencial da ordem de manutenção")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unfcodigo", nullable = false)
    @Comment("Código da unidade fabril")
    private ManufacturingUnit unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "srscodigo", nullable = true)
    @Comment("Código da solicitação que gerou a ordem (quando ordem é gerara por uma solicitação)")
    private ServiceSolicitation serviceSolicitation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maqcodigo", nullable = false)
    @Comment("Código da máquina para manutenção")
    private Machine machine;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "svodatabr")
    @Comment("Data de abertura da ordem de manuteção")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime openingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usucodigo", nullable = false)
    @Comment("Código do usuário de abertura")
    private User openingUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "svpcodigo", nullable = false)
    @Comment("Código da prioridade do serviço")
    private ServicePriority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tgocodigo", nullable = false)
    @Comment("Código do tipo da geração da ordem")
    private OrderGenerationType generationType;

    @Column(name = "svostatus", nullable = false)
    @Comment("Status da ordem de serviço (1-Não iniciada, 2-Programada, 3-Em andamento, 4-Suspensa, 5-Completa)")
    private Short status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sercodigo", nullable = true)
    @Comment("Código do tipo de intervenção (Quando encerrada)")
    private ServiceIntervention serviceIntervention;

    @Temporal(TemporalType.TIME)
    @Column(name = "svoduracaoestimada", nullable = true)
    @Comment("Duração estimada de realização da ordem de serviço (HH:MM:SS)")
    private LocalTime estimatedDuration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plmcodigo", nullable = true)
    @Comment("Código do plano de manutenção (caso ordem tenha sido gerada a partir de um)")
    private MaintenancePlan maintenancePlan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tpmcodigo", nullable = false)
    @Comment("Código do tipo de manutenção")
    private MaintenanceType maintenanceType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "csscodigo", nullable = true)
    @Comment("Código da causa de manutenção")
    private ServiceCause serviceCause;

    public ServiceOrder() {

    }

    public ServiceOrder(Integer id, ManufacturingUnit unit, ServiceSolicitation serviceSolicitation, Machine machine, LocalDateTime openingDate, User openingUser, ServicePriority priority, OrderGenerationType generationType, ServiceOrderStatus status, ServiceIntervention serviceIntervention, LocalTime estimatedDuration, MaintenancePlan maintenancePlan, MaintenanceType maintenanceType, ServiceCause serviceCause) {
        this.id = id;
        this.unit = unit;
        this.serviceSolicitation = serviceSolicitation;
        this.machine = machine;
        this.openingDate = openingDate;
        this.openingUser = openingUser;
        this.priority = priority;
        this.generationType = generationType;
        this.status = status.getValue();
        this.serviceIntervention = serviceIntervention;
        this.estimatedDuration = estimatedDuration;
        this.maintenancePlan = maintenancePlan;
        this.maintenanceType = maintenanceType;
        this.serviceCause = serviceCause;
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

    public ServiceSolicitation getServiceSolicitation() {
        return serviceSolicitation;
    }

    public void setServiceSolicitation(ServiceSolicitation serviceSolicitation) {
        this.serviceSolicitation = serviceSolicitation;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public User getOpeningUser() {
        return openingUser;
    }

    public void setOpeningUser(User openingUser) {
        this.openingUser = openingUser;
    }

    public ServicePriority getPriority() {
        return priority;
    }

    public void setPriority(ServicePriority priority) {
        this.priority = priority;
    }

    public OrderGenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(OrderGenerationType generationType) {
        this.generationType = generationType;
    }

    public ServiceOrderStatus getStatus() {
        return ServiceOrderStatus.valueOf(status);
    }

    public void setStatus(ServiceOrderStatus status) {
        this.status = status.getValue();
    }

    public ServiceIntervention getServiceIntervention() {
        return serviceIntervention;
    }

    public void setServiceIntervention(ServiceIntervention serviceIntervention) {
        this.serviceIntervention = serviceIntervention;
    }

    public LocalTime getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(LocalTime estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public MaintenancePlan getMaintenancePlan() {
        return maintenancePlan;
    }

    public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
        this.maintenancePlan = maintenancePlan;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public ServiceCause getServiceCause() {
        return serviceCause;
    }

    public void setServiceCause(ServiceCause serviceCause) {
        this.serviceCause = serviceCause;
    }

    @Override
    public String toString() {
        return "ServiceOrder{" +
                "id=" + id +
                ", unit=" + unit +
                ", serviceSolicitation=" + serviceSolicitation +
                ", machine=" + machine +
                ", openingDate=" + openingDate +
                ", openingUser=" + openingUser +
                ", priority=" + priority +
                ", generationType=" + generationType +
                ", status=" + status +
                ", serviceIntervention=" + serviceIntervention +
                ", estimatedDuration=" + estimatedDuration +
                ", maintenancePlan=" + maintenancePlan +
                ", maintenanceType=" + maintenanceType +
                ", serviceCause=" + serviceCause +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrder that = (ServiceOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
