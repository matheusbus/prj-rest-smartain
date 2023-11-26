package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent.MachineModelType;
import br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent.Manufacturer;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

public class MachineModelRequest {

    private Integer id;

    private Integer manufacturerId;

    private String model;

    private String dimensions;

    private Integer machineModelTypeId;

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

    public Integer getMachineModelTypeId() {
        return machineModelTypeId;
    }

    public void setMachineModelTypeId(Integer machineModelTypeId) {
        this.machineModelTypeId = machineModelTypeId;
    }

    public RegisterState getStatus() {
        return status;
    }

    public void setStatus(RegisterState status) {
        this.status = status;
    }
}
