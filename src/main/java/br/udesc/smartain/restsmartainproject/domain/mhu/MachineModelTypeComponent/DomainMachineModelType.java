package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent;

public enum DomainMachineModelType {

    MACHINE((short)1),
    COMPONENT((short)2),
    EQUIPAMENT((short) 3);

    private Short value;

    private DomainMachineModelType(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return this.value;
    }

    public static DomainMachineModelType valueOf(Short value) {
        for(DomainMachineModelType dom : DomainMachineModelType.values()){
            if(dom.getValue() == value){
                return dom;
            }
        }
        throw new IllegalArgumentException("Invalid DomainMachineModelType Value. Error at: [DomainMachineModelType.valueOf(short value)].");
    }

}
