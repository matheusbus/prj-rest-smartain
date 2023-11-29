package br.udesc.smartain.restsmartainproject.domain.types;

public enum DomainModelType {

    MACHINE((short)1),
    COMPONENT((short)2),
    EQUIPAMENT((short) 3);

    private Short value;

    private DomainModelType(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return this.value;
    }

    public static DomainModelType valueOf(Short value) {
        for(DomainModelType dom : DomainModelType.values()){
            if(dom.getValue() == value){
                return dom;
            }
        }
        throw new IllegalArgumentException("Invalid DomainMachineModelType Value. Error at: [DomainMachineModelType.valueOf(short value)].");
    }

}
