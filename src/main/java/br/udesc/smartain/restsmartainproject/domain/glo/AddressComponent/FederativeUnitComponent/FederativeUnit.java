package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "glo", name = "tbunifederativa")
public class FederativeUnit {

    @EmbeddedId
    private FederativeUnitId id;

    @Column(name = "unfnome", length = 100, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "id.federativeUnit")
    private List<City> cities = new ArrayList<>();

    public FederativeUnit() {

    }

    public FederativeUnit(FederativeUnitId id, String name) {
        this.id = id;
        this.name = name;
    }

    public FederativeUnitId getId() {
        return id;
    }

    public void setId(FederativeUnitId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public String toString() {
        return "FederativeUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FederativeUnit that = (FederativeUnit) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
