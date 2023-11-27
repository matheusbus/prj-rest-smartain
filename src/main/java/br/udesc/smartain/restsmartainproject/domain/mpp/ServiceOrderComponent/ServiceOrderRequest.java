package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServiceOrderRequest {

    private Integer id;
    private Integer unitId;
    private Integer solicitationId;
    private Integer machineId;
    private LocalDateTime openingDate;
    private Integer userId;
    private Integer priorityId;
    private Integer generationTypeId;
    private LocalTime estimatedDuration;
    private Integer maintenancePlanId;
    private Integer maintenanceTypeId;
    private Integer serviecCauseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getSolicitationId() {
        return solicitationId;
    }

    public void setSolicitationId(Integer solicitationId) {
        this.solicitationId = solicitationId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getGenerationTypeId() {
        return generationTypeId;
    }

    public void setGenerationTypeId(Integer generationTypeId) {
        this.generationTypeId = generationTypeId;
    }

    public LocalTime getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(LocalTime estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getMaintenancePlanId() {
        return maintenancePlanId;
    }

    public void setMaintenancePlanId(Integer maintenancePlanId) {
        this.maintenancePlanId = maintenancePlanId;
    }

    public Integer getMaintenanceTypeId() {
        return maintenanceTypeId;
    }

    public void setMaintenanceTypeId(Integer maintenanceTypeId) {
        this.maintenanceTypeId = maintenanceTypeId;
    }

    public Integer getServiecCauseId() {
        return serviecCauseId;
    }

    public void setServiecCauseId(Integer serviecCauseId) {
        this.serviecCauseId = serviecCauseId;
    }
}
