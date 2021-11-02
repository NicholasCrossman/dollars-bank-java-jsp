package com.cognixia.jump.model;

public class Amount {
    private double value;

    public Amount() {
        this.value = 0.0;
    }    

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
            " value='" + getValue() + "'" +
            "}";
    }

}
