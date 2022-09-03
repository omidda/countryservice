package com.nordea.assigment.countryservice.model.ouputs;

public class OperationOutput extends Output{

    public OperationOutput(OutputType type) {
        super(type);
    }

    public OperationOutput(OutputType type, String message) {
        super(type, message);
    }
}
