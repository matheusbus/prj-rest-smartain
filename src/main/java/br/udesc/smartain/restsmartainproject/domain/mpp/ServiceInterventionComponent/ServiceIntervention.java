package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbservico")
@Comment("Tabela de domínio dos serviços de intervenção")
public class ServiceIntervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sercodigo")
    @Comment("Código sequencial do servicço de intervenção")
    private Integer id;

    @Column(name = "sernome", length = 50)
    @Comment("Nome do serviço de intervenção")
    private String name;

    @Column(name = "sersigla")
    @Comment("Sigla do serviço de intervenção")
    private String acronym;

    @Column(name = "serdescricao", length = 500)
    @Comment("Descrição do serviço de intervenção")
    private String description;

    public ServiceIntervention() {

    }

    public ServiceIntervention(Integer id, String name, String acronym, String description) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.description = description;
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ServiceIntervention{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceIntervention that = (ServiceIntervention) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
