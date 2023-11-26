package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Entity
@Table(schema = "mpp", name = "tbcausaservico")
@Comment("Tabela que armazena as causas de necessidade de manutenção")
public class ServiceCause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "csscodigo")
    @Comment("Código sequencial da causa de manutenção")
    private Integer id;

    @Column(name = "csscausa", length = 50)
    @Comment("Causa de manutenção")
    private String name;

    @Column(name = "csssigla", length = 3)
    @Comment("Sigla da causa de manutenção")
    private String acronym;

    @Column(name = "cssdescricao", length = 500)
    @Comment("Descrição da causa de manutenção")
    private String descrption;

    public ServiceCause() {

    }

    public ServiceCause(Integer id, String name, String acronym, String descrption) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.descrption = descrption;
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

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Override
    public String toString() {
        return "ServiceCause{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                ", descrption='" + descrption + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCause that = (ServiceCause) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
