package br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbtipounidade")
@Comment(value = "Tabela domínio de tipo de unidade")
public class UnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tiucodigo")
    @Comment(value = "Código sequencial do tipo de unidade")
    private Integer id;

    @Column(name = "tiucodfaz")
    @Comment(value = "Código do tipo de unidade de acordo com o ministério da fazenda")
    private Integer code;

    @Column(name = "tiunome", length = 50)
    @Comment(value = "Nome do tipo de unidade")
    private String name;

    @Column(name = "tiudescricao", length = 500)
    @Comment(value = "Descrição do tipo de unidade")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<ManufacturingUnit> units = new ArrayList<>();

    public UnitType() {

    }

    public UnitType(Integer id, Integer code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UnitType{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitType unitType = (UnitType) o;
        return Objects.equals(id, unitType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
