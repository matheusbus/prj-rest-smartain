package br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbcelulcaproducao")
@Comment("Tabela de células de produção de um setor")
public class ProductionCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clpcodigo")
    @Comment("Código sequencial da célula de produção")
    private Integer id;

    @Column(name = "clpnome", nullable = false, length = 100)
    @NotBlank
    @Size(min = 3, max = 100, message = "O nome do setor deve conter entre 3 e 100 caracteres.")
    @Comment("Nome da célula de produção")
    private String name;

    @Column(name = "clpdescription", length = 250)
    @NotBlank
    @Size(min = 3, max = 250, message = "A descrição do setor deve conter entre 3 e 250 caracteres.")
    @Comment("Descrição da função da célula de produção")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull
    @JoinColumn(name = "ufbcodigo")
    @Comment("Código da unidade fabril")
    private ManufacturingUnit unit;

    @Column(name = "clpstatus")
    @NotNull
    @Comment("Status da célula de produção (1-Ativo, 2-Inativo)")
    private Short status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "clpdatcad")
    @NotNull
    @Comment("Data de cadastro da célula de produção")
    private LocalDateTime createdDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "setcodigo")
    @Comment("Código do setor")
    private Sector sector;

    @Column(name = "clptag")
    @Comment("Tag da célula de produção (Ex.: ABC-001)")
    private String tag;

    public ProductionCell() {

    }

    public ProductionCell(Integer id, String name, String description, ManufacturingUnit unit, RegisterState status, LocalDateTime createdDate, Sector sector, String tag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.status = status.getValue();
        this.createdDate = createdDate;
        this.sector = sector;
        this.tag = tag;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ManufacturingUnit getUnit() {
        return unit;
    }

    public void setUnit(ManufacturingUnit unit) {
        this.unit = unit;
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

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ProductionCell{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unit=" + unit +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", sector=" + sector +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionCell that = (ProductionCell) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
