package com.mercadolibre.genomax.service;


import com.mercadolibre.genomax.entity.StatEntity;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.repository.StatRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class StatServiceImplTest {

    @InjectMocks
    private StatService statService;

    @Mock
    private StatRepository statRepository;

    private StatEntity buildToSave(){
        return StatEntity.builder().id(UUID.randomUUID()).mutant(1).human(0).build();

    }


    @Test
    public void saveStat() throws GenomeBusinessException {
        Mockito.when(statRepository.save(buildToSave()));
        statService.saveStat(buildToSave());

    }


}