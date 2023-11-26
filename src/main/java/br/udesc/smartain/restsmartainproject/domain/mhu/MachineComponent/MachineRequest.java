package br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCell;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MachineRequest {

    private Integer id;
    private Integer productionCellId;

    private String technicalData;

    private Integer machineModelId;

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

    public Integer getMachineModelId() {
        return machineModelId;
    }

    public void setMachineModelId(Integer machineModel) {
        this.machineModelId = machineModel;
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
