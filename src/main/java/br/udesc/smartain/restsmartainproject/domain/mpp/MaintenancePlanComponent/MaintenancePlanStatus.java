package br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent;

import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderStatus;

public enum MaintenancePlanStatus {

    NOT_STARTED((short) 1),
    IN_PROGRESS((short) 2),
    COMPLETED((short) 3),
    CANCELED((short) 4);

    private Short value;

    private MaintenancePlanStatus(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return this.value;
    }

    public static MaintenancePlanStatus valueOf(Short value) {
        for(MaintenancePlanStatus mps : MaintenancePlanStatus.values()){
            if(mps.getValue().equals(value)){
                return mps;
            }
        }
        throw new IllegalArgumentException("Invalid MaintenancePlanStatus Value. Error at: [MaintenancePlanStatus.valueOf(short value)].");
    }

}
