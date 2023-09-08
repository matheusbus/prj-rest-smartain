package br.udesc.smartain.restsmartainproject.domain.model.states;

public enum RegisterDataState {

    ACTIVE((short)1),
    INACTIVE((short) 2);

    private Short value;

    private RegisterDataState(Short value){
        this.value = value;
    }

    public short getValue(){
        return this.value;
    }

    public static RegisterDataState valueOf(short value){
        for(RegisterDataState reg : RegisterDataState.values()){
            if(reg.getValue() == value){
                return reg;
            }
        }
        throw new IllegalArgumentException("Invalid RegisterDataState Value. Error at: [RegisterDataState.valueOf(short value)].");
    }

}
