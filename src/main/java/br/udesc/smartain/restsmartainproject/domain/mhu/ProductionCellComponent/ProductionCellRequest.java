package br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent;

import java.time.LocalDateTime;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;

public class ProductionCellRequest {

    private Integer id;
    private Integer sectorId;
    private String name;
    private String description;
    private RegisterState status;
    private LocalDateTime createdDate;
    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RegisterState getStatus() {
        return status;
    }

    public void setStatus(RegisterState status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
