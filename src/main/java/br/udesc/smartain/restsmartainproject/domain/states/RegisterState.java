package br.udesc.smartain.restsmartainproject.domain.states;

public enum RegisterState {

    ACTIVE((short)1),
    INACTIVE((short) 2);

    private Short value;

    private RegisterState(Short value){
        this.value = value;
    }

    public short getValue(){
        return this.value;
    }

    public static RegisterState valueOf(short value){
        for(RegisterState reg : RegisterState.values()){
            if(reg.getValue() == value){
                return reg;
            }
        }
        throw new IllegalArgumentException("Invalid RegisterDataState Value. Error at: [RegisterDataState.valueOf(short value)].");
    }

}
