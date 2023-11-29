package br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent;

public class ComponentRequest {

    private Integer id;

    private String name;

    private String technicalData;

    private Integer brandId;

    private Integer machineId;

    private Integer componentModelId;

    private Short status;

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

    public String getTechnicalData() {
        return technicalData;
    }

    public void setTechnicalData(String technicalData) {
        this.technicalData = technicalData;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getComponentModelId() {
        return componentModelId;
    }

    public void setComponentModelId(Integer componentModelId) {
        this.componentModelId = componentModelId;
    }
}
