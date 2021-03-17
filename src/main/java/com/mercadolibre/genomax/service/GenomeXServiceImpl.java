package com.mercadolibre.genomax.service;

import com.mercadolibre.genomax.common.NotificationCode;
import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.util.DnaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenomeXServiceImpl implements GenomeXService {


    private final DnaUtil dnaUtil;

    private char[][] matriz;


    @Override
    public Boolean isMutant(DnaInDto dna) throws GenomeBusinessException {
        List<DnaUtil.Code> codes = dnaUtil.getDna();
        int count = 0;
        try {
            matriz = getMatrizDna(dna);
        } catch (GenomeBusinessException g) {
            throw new GenomeBusinessException(g.getErrorCode(), g.getMessage());
        }

        for (DnaUtil.Code temp : codes) {
            if (count > 1)
                return true;
            if (resolver(temp.getCode()))
                count++;
        }
       return false;
    }


    private char[][] getMatrizDna(DnaInDto dna) throws GenomeBusinessException {
        int row, column = 0;

        if (dna.getDna().isEmpty()) {
            throw new GenomeBusinessException(NotificationCode.EMPTY_ARRAY);
        } else {
            char[][] matrizToVerificar = dna
                    .getDna()
                    .stream()
                    .map(cadena -> cadena.toCharArray())
                    .collect(Collectors.toList())
                    .toArray(new char[0][]);

            row = matrizToVerificar.length;
            column = matrizToVerificar[0].length;

            if (row == column && row + column > 8) {
                return matrizToVerificar;
            } else {
                throw new GenomeBusinessException(NotificationCode.NOT_ARRAY_NXN);
            }
        }

    }


    private Boolean resolver(String palabra) {

        for (int[] pos : posiblesSolucionesDe(palabra)) {
            // Buscar horizontalmente hacia derecha.
            String palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 0, 1);
            if(palabraEncontrada.equals(palabra))
                return true;

            // Buscar horizontalmente hacia izquierda.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 0, -1);
            if(palabraEncontrada.equals(palabra))
                return true;

            // Buscar verticalmente hacia abajo.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 1, 0);
            if(palabraEncontrada.equals(palabra))
                return true;

            // Buscar verticalmente hacia arriba.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), -1, 0);
            if(palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal superior derecha.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), -1, 1);
            if(palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal superior izquierda.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), -1, -1);
            if(palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal inferior derecha.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 1, 1);
            if(palabraEncontrada.equals(palabra))
                return true;

            // Buscar diagonal inferior izquierda.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 1, -1);
            if(palabraEncontrada.equals(palabra))
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
