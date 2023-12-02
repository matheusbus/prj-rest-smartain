package br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent;

import java.time.LocalDate;

public class MachineRequest {

    private Integer id;
    private Integer productionCellId;

    private String technicalData;

    private Integer modelId;

    private String tag;

    private LocalDate acquisitionDate;

    private LocalDate warrantyExpDate;

    private Short status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductionCellId() {
        return productionCellId;
    }

    public void setProductionCellId(Integer productionCellId) {
        this.productionCellId = productionCellId;
    }

    public String getTechnicalData() {
        return technicalData;
    }

    public void setTechnicalData(String technicalData) {
        this.technicalData = technicalData;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public LocalDate getWarrantyExpDate() {
        return warrantyExpDate;
    }

    public void setWarrantyExpDate(LocalDate warrantyExpDate) {
        this.warrantyExpDate = warrantyExpDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
