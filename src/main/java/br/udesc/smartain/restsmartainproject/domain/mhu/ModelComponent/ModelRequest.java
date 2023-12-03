package br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;

public class ModelRequest {

    private Integer id;

    private Integer manufacturerId;

    private String model;

    private String dimensions;

    private Short domainType;

    private RegisterState status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Short getDomainType() {
        return domainType;
    }

    public void setDomainType(Short domainType) {
        this.domainType = domainType;
    }

    public RegisterState getStatus() {
        return status;
    }

    public void setStatus(RegisterState status) {
        this.status = status;
    }
}
