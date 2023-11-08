package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class FederativeUnitId implements Serializable {

    @Column(name = "unfcodigo")
    private Integer federativeUnitId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "paicodigo", referencedColumnName = "paicodigo")
    private Country country;

    public FederativeUnitId(Integer federativeUnitId, Country country) {
        this.federativeUnitId = federativeUnitId;
        this.country = country;
    }

    public Integer getFederativeUnitId() {
        return federativeUnitId;
    }

    public void setFederativeUnitId(Integer federativeUnitId) {
        this.federativeUnitId = federativeUnitId;
    }

    public Country getCountryId() {
        return country;
    }

    public void setCountryId(Country country) {
        this.country = country;
    }
}
