package com.nordea.assigment.countryservice.model.ouputs;

public abstract class Output {

    private OutputType type;
    private String message;

    protected Output(){

    }
    protected Output(OutputType type){
        this.type = type;
    }
    protected Output(OutputType type,String message){
        this.type = type;
        this.message = message;
    }

    public Boolean isOperationSuccessful(){

        return type != null && type.equals(OutputType.SUCCESS);
    }

    public String getMessage() {
        return message;
    }

    public OutputType getType() {
        return type;
    }
}
