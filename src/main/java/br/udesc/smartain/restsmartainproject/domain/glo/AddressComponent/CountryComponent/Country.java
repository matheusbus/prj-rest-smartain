package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "glo", name = "tbpais")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paicodigo")
    private Integer id;

    @Column(name = "painome", length = 100)
    private String name;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Set<FederativeUnit> units = new HashSet<>();

    public Country(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<FederativeUnit> getUnits(){
        return units;
    }

    public void addUnit(FederativeUnit federativeUnit) {
        this.units.add(federativeUnit);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
