package com.mercadolibre.genomax.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class StatDto {
    private int count_mutant_dna;
    private int count_human_dna;
    private double rate;


}
