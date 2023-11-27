package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent;


import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationStatus;

public enum ServiceOrderStatus {

    NOT_STARTED((short) 1),
    SCHEDULED((short) 2),
    IN_PROGRESS((short) 3),
    SUSPENDED((short) 4),
    COMPLETED((short) 5);

    private Short value;

    private ServiceOrderStatus(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return this.value;
    }

    public static ServiceOrderStatus valueOf(Short value) {
        for(ServiceOrderStatus sos : ServiceOrderStatus.values()){
            if(sos.getValue().equals(value)){
                return sos;
            }
        }
        throw new IllegalArgumentException("Invalid ServiceOrderStatus Value. Error at: [ServiceOrderStatus.valueOf(short value)].");
    }


}
