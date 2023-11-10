package br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbturno")
@Comment(value = "Tabela de cadastro dos turnos de trabalho.")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turcodigo")
    @Comment(value = "Código sequencial do turno de trabalho")
    private Integer id;

    @Column(name = "turdescri")
    @Comment(value = "Descrição do turno")
    @Size(min = 3, max = 100, message = "A descrição do turno deve conter entre 3 e 100 caracteres.")
    private String descritpion;

    @Column(name = "turstatus")
    @Comment(value = "Status do turno")
    @Range(min = 1, max = 2)
    private Short status;

    @Temporal(TemporalType.TIME)
    @Column(name = "turhoraini")
    @Comment(value = "Hora de início do turno")
    private LocalTime initHour;

    @Temporal(TemporalType.TIME)
    @Column(name = "turhorafim")
    @Comment(value = "Hora de término do turno")
    private LocalTime endHour;

    public Shift() {

    }

    public Shift(Integer id, String descritpion, Short status, LocalTime initHour, LocalTime endHour) {
        this.id = id;
        this.descritpion = descritpion;
        this.status = status;
        this.initHour = initHour;
        this.endHour = endHour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    public LocalTime getInitHour() {
        return initHour;
    }

    public void setInitHour(LocalTime initHour) {
        this.initHour = initHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", descritpion='" + descritpion + '\'' +
                ", status=" + status +
                ", initHour=" + initHour +
                ", endHour=" + endHour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return Objects.equals(id, shift.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
