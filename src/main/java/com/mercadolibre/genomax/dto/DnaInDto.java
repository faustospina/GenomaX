package com.mercadolibre.genomax.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * dto para mappear la cadena de ADN
 */
@Data
@RequiredArgsConstructor
public class DnaInDto {

    @NotEmpty
    @NotNull
    @Size
    private List<String> dna;

}
