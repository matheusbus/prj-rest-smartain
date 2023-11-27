package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.Professional;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriority;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbservicosolicitacao")
@Comment("Tabela de solicitacao de serviços de manutenção")
public class ServiceSolicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srscodigo")
    @Comment("Codigo sequencial da solicitacao")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unfcodigo")
    @Comment("Código da unidade fabril")
    private ManufacturingUnit unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maqcodigo")
    @Comment("Código da máquina")
    private Machine machine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usucodigo")
    @Comment("Código do usuário responsável pela solicitação")
    private User responsibleUser;

    @Column(name = "srsstatus")
    @Comment("Status da solicitação (0-Aberta, 1-Aprovada, 2-Negada)")
    private Short status;

    @Column(name = "srsdescricao", length = 500)
    @Comment("Descrição do Serviço e/ou Defeito constatado")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "srsdatabr")
    @Comment("Data/hora de abertura da solicitação de serviço")
    private LocalDateTime openingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "svpcodigo")
    @Comment("Código do nível de prioridade do serviço")
    private ServicePriority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stscodigo")
    @Comment("Código do sintoma de manutenção")
    private ServiceSymptom symptom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tpmcodigo")
    @Comment("Código do tipo de manutenção")
    private MaintenanceType maintenanceType;

    public ServiceSolicitation() {

    }

    public ServiceSolicitation(Integer id, ManufacturingUnit unit, Machine machine, User responsibleUser, ServiceSolicitationStatus status, String description, LocalDateTime openingDate, ServicePriority priority, ServiceSymptom symptom, MaintenanceType maintenanceType) {
        this.id = id;
        this.unit = unit;
        this.machine = machine;
        this.responsibleUser = responsibleUser;
        this.status = status.getValue();
        this.description = description;
        this.openingDate = openingDate;
        this.priority = priority;
        this.symptom = symptom;
        this.maintenanceType = maintenanceType;
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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public User getResponsibleProfessional() {
        return responsibleUser;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public ServiceSolicitationStatus getStatus() {
        return ServiceSolicitationStatus.valueOf(status);
    }

    public void setStatus(ServiceSolicitationStatus status) {
        this.status = status.getValue();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public ServicePriority getPriority() {
        return priority;
    }

    public void setPriority(ServicePriority priority) {
        this.priority = priority;
    }

    public ServiceSymptom getSymptom() {
        return symptom;
    }

    public void setSymptom(ServiceSymptom symptom) {
        this.symptom = symptom;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    @Override
    public String toString() {
        return "ServiceSolicitation{" +
                "id=" + id +
                ", unit=" + unit +
                ", machine=" + machine +
                ", responsibleUser=" + responsibleUser +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", openingDate=" + openingDate +
                ", priority=" + priority +
                ", symptom=" + symptom +
                ", maintenanceType=" + maintenanceType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceSolicitation that = (ServiceSolicitation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
