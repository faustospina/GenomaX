package com.mercadolibre.genomax.service;


import com.mercadolibre.genomax.entity.StatEntity;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.repository.StatRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class StatServiceImplTest {

    @InjectMocks
    private StatServiceImpl statService;

    @Mock
    private StatRepository statRepository;


    private StatEntity buildToSave() {
        return StatEntity.builder().id(UUID.randomUUID()).mutant(1).human(0).build();
    }


    @Test
    public void saveStat() throws GenomeBusinessException {
        statService.saveStat(buildToSave());
    }

    @Test(expected = GenomeBusinessException.class)
    public void saveStatException() throws GenomeBusinessException {
        statService.saveStat(null);
    }


    @Test
    public void getNumMutant() throws GenomeBusinessException {
        Mockito.when(statRepository.ValueMutant()).thenReturn(3);
        Integer var = statService.getNumMutant();
        Assertions.assertTrue(var == 3);
    }

    @Test(expected = GenomeBusinessException.class)
    public void getNumMutantException() throws GenomeBusinessException {
        Mockito.when(statRepository.ValueMutant()).thenReturn(null);
        Integer var = statService.getNumMutant();
        Assertions.assertTrue(var == 3);
    }

    @Test
    public void getNumHuman() throws GenomeBusinessException {
        Mockito.when(statRepository.ValueHuman()).thenReturn(3);
        Integer var = statService.getNumHuman();
        Assertions.assertTrue(var == 3);
    }

    @Test(expected = GenomeBusinessException.class)
    public void getNumHumanException() throws GenomeBusinessException {
        Mockito.when(statRepository.ValueHuman()).thenReturn(null);
        Integer var = statService.getNumHuman();
        Assertions.assertTrue(var == 3);
    }


}