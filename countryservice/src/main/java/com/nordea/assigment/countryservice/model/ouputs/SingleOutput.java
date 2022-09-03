package com.nordea.assigment.countryservice.model.ouputs;

public class SingleOutput extends Output {

    IResult result;

    public SingleOutput() {

    }

    public SingleOutput(Output output){
        super(output.getType(), output.getMessage());
    }


    public SingleOutput(OutputType type, String message) {
        super(type, message);
    }

    public SingleOutput(OutputType type, String message, IResult result) {
        super(type, message);
        this.result = result;
    }
    public SingleOutput(OutputType type, IResult result) {
        super(type);
        this.result = result;
    }
    public IResult getResult() {
        return result;
    }

    public void setResult(IResult result) {
        this.result = result;
    }
}
