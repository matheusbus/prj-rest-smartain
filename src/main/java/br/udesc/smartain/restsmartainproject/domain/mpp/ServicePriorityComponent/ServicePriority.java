package br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbprioridadeservico")
@Comment("Tabela de domínio das prioridades de servicos de manutencao")
public class ServicePriority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "svpcodigo")
    @Comment("Codigo sequencial da prioridade")
    private Integer id;

    @Column(name = "svpdescricao", length = 200)
    @Comment("Descricao da prioridade")
    private String descritpion;

    public ServicePriority() {

    }

    public ServicePriority(Integer id, String descritpion) {
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
        return "ServicePriority{" +
                "id=" + id +
                ", descritpion='" + descritpion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicePriority that = (ServicePriority) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
