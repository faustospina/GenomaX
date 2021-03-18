package com.mercadolibre.genomax.service;

import com.mercadolibre.genomax.common.NotificationCode;
import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.dto.StatDto;
import com.mercadolibre.genomax.entity.StatEntity;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.util.DnaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenomeXServiceImpl implements GenomeXService {


    private final DnaUtil dnaUtil;
    private final StatService statService;

    private char[][] matriz;


    @Override
    public Boolean isMutant(DnaInDto dna) throws GenomeBusinessException {
        List<DnaUtil.Code> codes = dnaUtil.getDna();
        int count = 0;

        try {
            matriz = getMatrizDna(dna);
        } catch (GenomeBusinessException g) {
            throw new GenomeBusinessException(g.getErrorCode());
        }

        for (DnaUtil.Code code : codes) {
            if (resolver(code.getCode())) {
                count++;
            }
            if (count > 1) {
                try {
                    statService.saveStat(StatEntity.builder().id(UUID.randomUUID()).mutant(1).human(0).build());
                    return true;
                } catch (GenomeBusinessException g) {
                    throw new GenomeBusinessException(g.getErrorCode());
                }

            }
        }
        try {
            statService.saveStat(StatEntity.builder().id(UUID.randomUUID()).mutant(0).human(1).build());
            return false;
        } catch (GenomeBusinessException g) {
            throw new GenomeBusinessException(g.getErrorCode());
        }
    }

    @Override
    public StatDto stats() throws GenomeBusinessException {
        int mutant = 0;
        int human = 0;
        double rate = 0.0;
        try {
            mutant = statService.getNumMutant();
            human = statService.getNumHuman();
            rate = rate(mutant, human);
        } catch (GenomeBusinessException g) {
            throw new GenomeBusinessException(g.getErrorCode());
        }
        return StatDto.builder()
                .count_mutant_dna(mutant)
                .count_human_dna(human)
                .rate(rate)
                .build();
    }

    private double rate(int mutantNum,int humanNum) throws GenomeBusinessException {
        if (mutantNum==0||humanNum==0){
            throw new GenomeBusinessException(NotificationCode.DIVISION_BY_ZERO);
        }

        if (mutantNum>humanNum){
            return (double)humanNum/mutantNum;
        }else{
            return (double)mutantNum/humanNum;
        }

    }


    private char[][] getMatrizDna(DnaInDto dna) throws GenomeBusinessException {
        int row = 0;
        if (dna.getDna().isEmpty()) {
            throw new GenomeBusinessException(NotificationCode.EMPTY_ARRAY);
        } else {
            char[][] matrixToValidate = dna
                    .getDna()
                    .stream()
                    .map(cadena -> cadena.toCharArray())
                    .collect(Collectors.toList())
                    .toArray(new char[0][]);

            row = matrixToValidate.length;

            for (int i = 0; i < matrixToValidate.length; i++) {
                if (row != matrixToValidate[i].length) {
                    throw new GenomeBusinessException(NotificationCode.NOT_ARRAY_NXN);
                }
            }

            if (row > 4) {
                return matrixToValidate;
            } else {
                throw new GenomeBusinessException(NotificationCode.MIN_LENGTH_ARRAY);
            }
        }

    }


    private Boolean resolver(String palabra) {

        for (int[] pos : posiblesSolucionesDe(palabra)) {
            // Buscar horizontalmente hacia derecha.
            String palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 0, 1);
            if (palabraEncontrada.equals(palabra))
                return true;

            // Buscar horizontalmente hacia izquierda.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 0, -1);
            if (palabraEncontrada.equals(palabra))
                return true;

            // Buscar verticalmente hacia abajo.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 1, 0);
            if (palabraEncontrada.equals(palabra))
                return true;

            // Buscar verticalmente hacia arriba.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), -1, 0);
            if (palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal superior derecha.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), -1, 1);
            if (palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal superior izquierda.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), -1, -1);
            if (palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal inferior derecha.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 1, 1);
            if (palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal inferior izquierda.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 1, -1);
            if (palabraEncontrada.equals(palabra))
                return true;
        }
        return false;
    }

    /*
     * Retorna indice invertido de las posiciones donde puede
     * resolverse una palabra buscada.
     */
    private int[][] posiblesSolucionesDe(String palabra) {
        char primeraLetra = palabra.charAt(0);
        List<int[]> indiceInvertido = new ArrayList<int[]>();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == primeraLetra) {
                    indiceInvertido.add(new int[]{i, j}); // Guardar la posicion de la letra en la matriz.
                }
            }
        }
        return toArrayInt(indiceInvertido);
    }

    /*
     * Transforma un objeto List a un multi arreglo
     * de nœmeros enteros.
     * @param list la lista a transformar.
     */
    private int[][] toArrayInt(List<int[]> list) {
        return (int[][]) list.toArray(new int[list.size()][list.get(0).length]);
    }

    /*
     * Algoritmo que busca palabras en la matriz de palabras de forma
     * recursiva usando la tŽcnica de backtracking.
     */
    public String palabraEnMatriz(int[] posInicial, int numeroCaracteres, int moverEnFila, int moverEnColumna) {
        String palabra = "";
        int recorrido = 0, fila = posInicial[0], columna = posInicial[1];

        while ((recorrido < numeroCaracteres) &&
                (fila < matriz.length && columna < matriz.length) &&
                (fila > -1 && columna > -1)) {

            palabra += matriz[fila][columna];
            fila = fila + moverEnFila;
            columna = columna + moverEnColumna;
            recorrido++;
        }

        return palabra;
    }


}
