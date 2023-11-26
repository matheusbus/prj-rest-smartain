package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbsintomaservico")
@Comment("Tabela dos sintomas para geração de serviços")
public class ServiceSymptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stscodigo")
    @Comment("Código sequencial do sintoma de manutenção")
    private Integer id;

    @Column(name = "stssintoma", length = 50)
    @Comment("Sintoma de manutenção")
    private String name;

    @Column(name = "stssigla", length = 3)
    @Comment("Sigla do sintoma de manutenção")
    private String acronym;

    @Column(name = "stsdescricao", length = 500)
    @Comment("Descrição do sintoma de manutenção")
    private String description;

    public ServiceSymptom() {

    }

    public ServiceSymptom(Integer id, String name, String acronym, String description) {
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
        return "ServiceSymptom{" +
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
        ServiceSymptom that = (ServiceSymptom) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
