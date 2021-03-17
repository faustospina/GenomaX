package com.mercadolibre.genomax.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@RequiredArgsConstructor
public class DnaInDto {

    @NotEmpty
    @NotNull
    @Size
    private List<String> dna;

}
