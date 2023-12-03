package br.udesc.smartain.restsmartainproject.domain.mhu.AlertComponent;

public enum AlertType {

    ALERT_BY_WARRANTY((short) 1),
    ALERT_BY_USER((short) 2);

    private Short value;

    private AlertType(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return value;
    }

    public static AlertType valueOf(Short value) {
        for(AlertType alertType : AlertType.values()) {
            if(alertType.getValue().equals(value)) {
                return alertType;
            }
        }
        throw new IllegalArgumentException("Invalid AlertType Value. Error at: [AlertType.valueOf(short value)].");
    }


}
