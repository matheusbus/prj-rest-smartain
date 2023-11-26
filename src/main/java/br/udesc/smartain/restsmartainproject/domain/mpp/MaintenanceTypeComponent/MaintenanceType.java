package br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbtipomanutencao")
@Comment("Tabela de domínio que armazena os tipos de manutenção")
public class MaintenanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpmcodigo")
    @Comment("Código sequencial do tipo de manutenção")
    private Integer id;

    @Column(name = "tpmdescricao", length = 250)
    @Comment("Descrição do tipo de manutenção")
    private String descritpion;

    public MaintenanceType() {

    }

    public MaintenanceType(Integer id, String descritpion) {
        this.id = id;
        this.descritpion = descritpion;
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

    @Override
    public String toString() {
        return "MaintenanceType{" +
                "id=" + id +
                ", descritpion='" + descritpion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceType that = (MaintenanceType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
