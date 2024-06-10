package com.meli.otavio.model;

import java.time.OffsetDateTime;

public class Transacao {
    private double valor;
    private OffsetDateTime dataHora;

    // Getters and Setters
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
