package br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentModelComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;

public class EquipmentModelRequest {

    private Integer id;

    private Integer manufacturerId;

    private String model;

    private String dimensions;

    private Integer modelTypeId;

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

    public Integer getModelTypeId() {
        return modelTypeId;
    }

    public void setModelTypeId(Integer modelTypeId) {
        this.modelTypeId = modelTypeId;
    }

    public RegisterState getStatus() {
        return status;
    }

    public void setStatus(RegisterState status) {
        this.status = status;
    }

}
