package com.mercadolibre.genomax.service;

import com.mercadolibre.genomax.common.NotificationCode;
import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.dto.StatDto;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.util.DnaUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GenomeXServiceImplTest {

    @InjectMocks
    private GenomeXServiceImpl genomeXService;

    @Mock
    private StatService statService;

    @Mock
    private DnaUtil dnaUtil;

    private static final String A="AAAA";
    private static final String C="CCCC";
    private static final String T="TTTT";
    private static final String G="GGGG";


    private List<DnaUtil.Code> codes(){
        DnaUtil.Code codeA=new DnaUtil.Code();
        codeA.setCode(A);
        DnaUtil.Code codeC=new DnaUtil.Code();
        codeC.setCode(C);
        DnaUtil.Code codeT=new DnaUtil.Code();
        codeT.setCode(T);
        DnaUtil.Code codeG=new DnaUtil.Code();
        codeG.setCode(G);
        List<DnaUtil.Code> codes=new ArrayList<>();
        codes.add(codeA);
        codes.add(codeC);
        codes.add(codeT);
        codes.add(codeG);
        return codes;
    }

    private DnaInDto dnaHappyPath(){
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

    private DnaInDto dnaHumanHappyPath(){
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        dna.getDna().add("ATGCGA");
        dna.getDna().add("CCGTGC");
        dna.getDna().add("TTATGT");
        dna.getDna().add("AGACGG");
        dna.getDna().add("CACATA");
        dna.getDna().add("TCACTG");
        return dna;
    }

    private DnaInDto notArrayNxN(){
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        dna.getDna().add("ATGCGA");
        dna.getDna().add("CAGTGC");
        dna.getDna().add("TTATGT");
        dna.getDna().add("AGAAGG");
        return dna;
    }

    private DnaInDto minLength(){
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        dna.getDna().add("ATGC");
        dna.getDna().add("CAGT");
        dna.getDna().add("TTAT");
        dna.getDna().add("AGAA");
        return dna;
    }
    private DnaInDto arrayEmpty(){
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        return dna;
    }

    private DnaInDto dnaLower(){
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        dna.getDna().add("atgcga");
        dna.getDna().add("cagtgc");
        dna.getDna().add("ttatgt");
        dna.getDna().add("agaagg");
        dna.getDna().add("ccccta");
        dna.getDna().add("tcactg");
        return dna;
    }

    private DnaInDto dnaDifferent(){
        DnaInDto dna = new DnaInDto();
        dna.setDna(new ArrayList<>());
        dna.getDna().add("atghga");
        dna.getDna().add("cagtgc");
        dna.getDna().add("ttatgt");
        dna.getDna().add("agaagg");
        dna.getDna().add("ccccta");
        dna.getDna().add("tcactg");
        return dna;
    }


    @Test
    public void isMutant() throws GenomeBusinessException {
        Mockito.when(dnaUtil.getDna()).thenReturn(codes());
        boolean response= genomeXService.isMutant(dnaHappyPath());
        Assertions.assertTrue(response);
    }

    @Test
    public void isHuman() throws GenomeBusinessException {
        Mockito.when(dnaUtil.getDna()).thenReturn(codes());
        boolean response= genomeXService.isMutant(dnaHumanHappyPath());
        Assertions.assertTrue(response==false);
    }

    @Test
    public void isMutantLowerCase() throws GenomeBusinessException {
        Mockito.when(dnaUtil.getDna()).thenReturn(codes());
        boolean response= genomeXService.isMutant(dnaLower());
        Assertions.assertTrue(response);
    }

    @Test(expected = GenomeBusinessException.class)
    public void isMutantDnaDifferent() throws GenomeBusinessException {
        Mockito.when(dnaUtil.getDna()).thenReturn(codes());
        boolean response= genomeXService.isMutant(dnaDifferent());
        Assertions.assertTrue(response);
    }

    @Test(expected = GenomeBusinessException.class)
    public void isMutantDnaNotNxN() throws GenomeBusinessException {
        Mockito.when(dnaUtil.getDna()).thenReturn(codes());
        boolean response= genomeXService.isMutant(notArrayNxN());
        Assertions.assertTrue(response);
    }

    @Test(expected = GenomeBusinessException.class)
    public void isMutantMinLength() throws GenomeBusinessException {
        Mockito.when(dnaUtil.getDna()).thenReturn(codes());
        boolean response= genomeXService.isMutant(minLength());
        Assertions.assertTrue(response);
    }

    @Test(expected = GenomeBusinessException.class)
    public void isMutantArrayEmpty() throws GenomeBusinessException {
        Mockito.when(dnaUtil.getDna()).thenReturn(codes());
        boolean response= genomeXService.isMutant(arrayEmpty());
        Assertions.assertTrue(response);
    }

    @Test
    public void statsHumanMinor() throws GenomeBusinessException {
        Mockito.when(statService.getNumMutant()).thenReturn(Integer.valueOf(2));
        Mockito.when(statService.getNumHuman()).thenReturn(Integer.valueOf(1));
        StatDto statDto=genomeXService.stats();
        Assertions.assertTrue(statDto!=null);
        Assertions.assertTrue(statDto.getCount_mutant_dna()==2);
        Assertions.assertTrue(statDto.getCount_human_dna()==1);
        Assertions.assertTrue(statDto.getRate()==0.5);
    }

    @Test
    public void statsMutantMinor() throws GenomeBusinessException {
        Mockito.when(statService.getNumMutant()).thenReturn(Integer.valueOf(1));
        Mockito.when(statService.getNumHuman()).thenReturn(Integer.valueOf(2));
        StatDto statDto=genomeXService.stats();
        Assertions.assertTrue(statDto!=null);
        Assertions.assertTrue(statDto.getCount_mutant_dna()==1);
        Assertions.assertTrue(statDto.getCount_human_dna()==2);
        Assertions.assertTrue(statDto.getRate()==0.5);
    }

    @Test(expected = GenomeBusinessException.class)
    public void statsWhenZeroMutant() throws GenomeBusinessException {
        Mockito.when(statService.getNumMutant()).thenReturn(Integer.valueOf(0));
        Mockito.when(statService.getNumHuman()).thenReturn(Integer.valueOf(1));
        StatDto statDto=genomeXService.stats();
    }

    @Test(expected = GenomeBusinessException.class)
    public void statsWhenResponseNull() throws GenomeBusinessException {
        Mockito.when(statService.getNumMutant()).thenThrow(new GenomeBusinessException(NotificationCode.EMPTY_DATA));
        StatDto statDto=genomeXService.stats();
    }


}