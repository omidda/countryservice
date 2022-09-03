package com.nordea.assigment.countryservice.model.ouputs;

import java.util.List;

public class ListOutput extends Output {
    List<IResult> results;

    public ListOutput(OutputType type, String message) {
        super(type, message);
    }

    public ListOutput(OutputType type, String message, List<IResult> results) {
        super(type, message);
        this.results = results;
    }

    public List<IResult> getResults() {
        return results;
    }

    public void setResults(List<IResult> results) {
        this.results = results;
    }
}
