package com.mercadolibre.genomax.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * dto para mostrar las estadisticas
 */
@Data
@Builder
public class StatDto {
    private int count_mutant_dna;
    private int count_human_dna;
    private double rate;


}
