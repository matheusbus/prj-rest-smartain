package br.udesc.smartain.restsmartainproject.domain.mhu.MachineManualComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbmanualmaquina")
@Comment("Tabela de cadastros dos manuais de manutenção das máquinas")
public class MachineManual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mamcodigo")
    @Comment("Código sequencial do manual")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maqcodigo")
    @Comment("Código da máquina ao qual está associado o manual")
    private Machine machine;

    @Column(name = "mamtitulo", length = 250)
    @Comment("Título do manual")
    private String title;

    @Column(name = "mamdescricao")
    @Comment("Manual descritivo")
    private String description;

    @Column(name = "mamstatus")
    @Comment("Status do manual (1-Ativo, 2-Inativo)")
    private Short status;

    public MachineManual() {

    }

    public MachineManual(Integer id, Machine machine, String title, String description, RegisterState status) {
        this.id = id;
        this.machine = machine;
        this.title = title;
        this.description = description;
        this.status = status.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
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

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    @Override
    public String toString() {
        return "MachineManual{" +
                "id=" + id +
                ", machine=" + machine +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MachineManual that = (MachineManual) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
