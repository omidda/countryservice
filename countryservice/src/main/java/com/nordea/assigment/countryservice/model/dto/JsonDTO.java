package com.nordea.assigment.countryservice.model.dto;

import com.nordea.assigment.countryservice.model.ouputs.IResult;

public class JsonDTO implements IResult {

    private String json;

    public JsonDTO(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
