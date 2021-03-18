package com.mercadolibre.genomax.service;

import com.mercadolibre.genomax.entity.StatEntity;
import com.mercadolibre.genomax.exception.GenomeBusinessException;

public interface StatService {
    void saveStat(StatEntity statEntity) throws GenomeBusinessException;
    int getNumMutant() throws GenomeBusinessException;
    int getNumHuman() throws GenomeBusinessException;
}
