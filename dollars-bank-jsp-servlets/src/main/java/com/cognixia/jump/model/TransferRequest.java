package com.cognixia.jump.model;

public class TransferRequest {
    private int targetId;
    private double amount;

    public TransferRequest() {
        this.targetId = -1;
        this.amount = 0.0;
    }

    public TransferRequest(int targetId, double amount) {
        this.targetId = targetId;
        this.amount = amount;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
            " targetId='" + getTargetId() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }
}