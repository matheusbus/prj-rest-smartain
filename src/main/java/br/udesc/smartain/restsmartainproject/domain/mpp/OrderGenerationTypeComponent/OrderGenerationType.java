package br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbtipogeraordem")
@Comment("Tabela dos tipos de geração de ordem de manutenção")
public class OrderGenerationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tgocodigo")
    @Comment("Código sequencial do tipo de geração de ordem de serviço")
    private Integer id;

    @Column(name = "tgodescricao")
    @Comment("Descrição do tipo de geração de ordem de serviço")
    private String description;

    public OrderGenerationType() {

    }

    public OrderGenerationType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderGenerationType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGenerationType that = (OrderGenerationType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
