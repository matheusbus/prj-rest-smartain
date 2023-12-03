package br.udesc.smartain.restsmartainproject.domain.mhu.AlertComponent;

public enum AlertStatus {

    PENDING((short) 1),
    ATTENDED((short) 2);

    private Short value;

    private AlertStatus(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return value;
    }

    public static AlertStatus valueOf(Short value) {
        for(AlertStatus alertStatus : AlertStatus.values()) {
            if(alertStatus.getValue().equals(value)) {
                return alertStatus;
            }
        }
        throw new IllegalArgumentException("Invalid AlertStatus Value. Error at: [AlertStatus.valueOf(short value)].");
    }

}
