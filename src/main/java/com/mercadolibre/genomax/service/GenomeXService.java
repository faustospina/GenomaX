package com.mercadolibre.genomax.service;

import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.dto.StatDto;
import com.mercadolibre.genomax.exception.GenomeBusinessException;

public interface GenomeXService {

    Boolean isMutant(DnaInDto dna) throws GenomeBusinessException;
    StatDto stats();

}
