package com.mercadolibre.genomax.controller;

import com.mercadolibre.genomax.common.NotificationCode;
import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.dto.StatDto;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.service.GenomeXService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class GenomeXControllerTest {

    @Mock
    private GenomeXService genomeXService;

    @InjectMocks
    private GenomeXController genomeXController;


    private DnaInDto dnaHappyPath() {
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        dna.getDna().add("ATGCGA");
        dna.getDna().add("CAGTGC");
        dna.getDna().add("TTATGT");
        dna.getDna().add("AGAAGG");
        dna.getDna().add("CCCCTA");
        dna.getDna().add("TCACTG");
        return dna;
    }

    private DnaInDto dnaEmptyDta() {
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        return dna;
    }

    private StatDto buildResponse() {
        return StatDto.builder().count_mutant_dna(2).count_human_dna(1).rate(0.5).build();
    }

    private StatDto buildResponseZero() {
        return StatDto.builder().count_mutant_dna(0).count_human_dna(1).rate(0.5).build();
    }


    @Test
    public void addMutant() throws GenomeBusinessException {
        Mockito.when(genomeXService.isMutant(dnaHappyPath())).thenReturn(true);
        ResponseEntity<?> responseEntity = genomeXController.isMutant(dnaHappyPath());
        Assertions.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void addHuman() throws GenomeBusinessException {
        Mockito.when(genomeXService.isMutant(dnaHappyPath())).thenReturn(false);
        ResponseEntity<?> responseEntity = genomeXController.isMutant(dnaHappyPath());
        Assertions.assertTrue(responseEntity.getStatusCode() == HttpStatus.FORBIDDEN);
    }

    @Test
    public void addHumanFail() throws GenomeBusinessException {
        Mockito.when(genomeXService.isMutant(dnaEmptyDta())).thenThrow(new GenomeBusinessException(NotificationCode.EMPTY_DATA));
        ResponseEntity<?> responseEntity = genomeXController.isMutant(dnaEmptyDta());
        Assertions.assertTrue(responseEntity.getStatusCode() == HttpStatus.FORBIDDEN);
    }

    @Test
    public void getStats() throws GenomeBusinessException {
        Mockito.when(genomeXService.stats()).thenReturn(buildResponse());
        ResponseEntity<StatDto> responseEntity = (ResponseEntity<StatDto>) genomeXController.getStats();
        Assertions.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        Assertions.assertTrue(responseEntity.getBody().getCount_mutant_dna() == 2);
        Assertions.assertTrue(responseEntity.getBody().getCount_human_dna() == 1);
        Assertions.assertTrue(responseEntity.getBody().getRate() == 0.5);
    }

    @Test
    public void getStatsError() throws GenomeBusinessException {
        Mockito.when(genomeXService.stats()).thenThrow(new GenomeBusinessException(NotificationCode.DIVISION_BY_ZERO));
        ResponseEntity<StatDto> responseEntity = (ResponseEntity<StatDto>) genomeXController.getStats();
        Assertions.assertTrue(responseEntity.getStatusCode() == HttpStatus.FORBIDDEN);
    }
}