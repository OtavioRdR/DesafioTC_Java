package com.meli.otavio.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Estatistica {
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;
}
