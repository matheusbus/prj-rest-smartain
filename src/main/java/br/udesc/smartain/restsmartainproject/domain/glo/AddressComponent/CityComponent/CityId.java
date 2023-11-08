package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnit;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class CityId implements Serializable {

    @Column(name = "cidcodigo")
    private Integer cityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "unfcodigo", referencedColumnName = "unfcodigo"),
            @JoinColumn(name = "paicodigo", referencedColumnName = "paicodigo")})
    private FederativeUnit federativeUnit;

    public CityId() {
    }

    public CityId(Integer cityId, FederativeUnit federativeUnit) {
        this.cityId = cityId;
        this.federativeUnit = federativeUnit;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public FederativeUnit getFederativeUnitId() {
        return federativeUnit;
    }

    public void setFederativeUnitId(FederativeUnit federativeUnit) {
        this.federativeUnit = federativeUnit;
    }
}
