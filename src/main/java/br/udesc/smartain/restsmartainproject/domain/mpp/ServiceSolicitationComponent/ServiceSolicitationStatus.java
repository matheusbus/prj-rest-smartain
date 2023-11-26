package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent;

import br.udesc.smartain.restsmartainproject.domain.types.DomainMachineModelType;

public enum ServiceSolicitationStatus {

    OPENED((short)1),
    APPROVED((short)2),
    DENIED((short) 3),
    CANCELED((short) 4);

    private Short value;

    private ServiceSolicitationStatus(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return this.value;
    }

    public static ServiceSolicitationStatus valueOf(Short value) {
        for(ServiceSolicitationStatus sss : ServiceSolicitationStatus.values()){
            if(sss.getValue().equals(value)){
                return sss;
            }
        }
        throw new IllegalArgumentException("Invalid ServiceSolicitationStatus Value. Error at: [ServiceSolicitationStatus.valueOf(short value)].");
    }

}
