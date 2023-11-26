package br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCell;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbsetor")
@Comment("Tabela de cadastro dos setores das unidades fabris.")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setcodigo")
    @Comment("Código sequencial do setor")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unfcodigo", nullable = false)
    @Comment("Código da unidade fabril")
    private ManufacturingUnit unit;

    @Column(name = "setnome")
    @Size(min = 3, max = 50, message = "O nome do setor deve conter entre 3 e 50 caracteres.")
    @Comment("Nome do setor")
    private String name;

    @Column(name = "setdescricao")
    @Size(min = 3, max = 250, message = "A descrição do setor deve conter entre 3 e 250 caracteres")
    @Comment("Descrição das atividades desempenhadas pelo setor")
    private String description;

    @Column(name = "setativo")
    @Comment("Status do setor (1-Ativo, 2-Inativo)")
    private Short status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "setdatcad")
    @Comment("Data de cadastro do setor")
    private LocalDateTime createdDate;

    @Column(name = "settag")
    @Comment("Tag do setor (Ex.: ABC-001)")
    private String tag;

    @OneToMany(mappedBy = "sector")
    private List<ProductionCell> cells = new ArrayList<>();

    public Sector() {

    }

    public Sector(Integer id, ManufacturingUnit unit, String name, String description, Short status, LocalDateTime createdDate, String tag) {
        this.id = id;
        this.unit = unit;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.tag = tag;
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

    public RegisterState getStatus() {
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<ProductionCell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", unit=" + unit +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + status +
                ", createdDate=" + createdDate +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sector sector = (Sector) o;
        return Objects.equals(id, sector.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
