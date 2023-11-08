package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "glo", name = "tbcidade")
public class City {

    @EmbeddedId
    private CityId id;

    @Column(name = "cidnome")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<ManufacturingUnit> manufacturingUnits;

    public City() {

    }

    public City(CityId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityId getId() {
        return id;
    }

    public void setId(CityId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
