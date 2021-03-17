package com.mercadolibre.genomax.service;

import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.dto.GenomeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenomeXServiceImpl implements GenomeXService {

    private static final String GENOME_A = "AAAA";
    private static final String GENOME_T = "TTTT";
    private static final String GENOME_C = "CCCC";
    private static final String GENOME_G = "GGGG";


    @Override
    public Boolean isMutant(DnaInDto dna) {
        boolean flag = false;
        int count = 0;

     

        GenomeDto genomeDtoA = new GenomeDto();
        genomeDtoA.setGenomeType(GENOME_A);
        GenomeDto genomeDtoT = new GenomeDto();
        genomeDtoT.setGenomeType(GENOME_T);
        GenomeDto genomeDtoC = new GenomeDto();
        genomeDtoC.setGenomeType(GENOME_C);
        GenomeDto genomeDtoG = new GenomeDto();
        genomeDtoG.setGenomeType(GENOME_G);
        List<GenomeDto> listGenomeDto = new ArrayList<>();
        listGenomeDto.add(genomeDtoA);
        listGenomeDto.add(genomeDtoT);
        listGenomeDto.add(genomeDtoC);

        listGenomeDto.add(genomeDtoG);
        for (GenomeDto temp : listGenomeDto) {
            flag = resolver(temp.getGenomeType());
            if (count > 1)
                return true;
            if (flag)
                count++;
        }
        return flag;
    }


    private char[][] matriz = {{'A', 'T', 'G', 'C', 'G', 'A'},
            {'C', 'A', 'G', 'T', 'G', 'C'},
            {'T', 'T', 'A', 'T', 'G', 'T'},
            {'A', 'G', 'A', 'A', 'G', 'G'},
            {'C', 'C', 'C', 'C', 'T', 'A'},
            {'T', 'C', 'A', 'C', 'T', 'G'}};


    public Boolean resolver(String palabra) {

        for (int[] pos : posiblesSolucionesDe(palabra)) {
            // Buscar horizontalmente hacia derecha.
            String palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 0, 1);
            if (palabraEncontrada.equals(palabra))
                return true;
            // Buscar verticalmente hacia abajo.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), 1, 0);
            if (palabraEncontrada.equals(palabra))
                return true;
            // Buscar diagonal superior derecha.
            palabraEncontrada = palabraEnMatriz(pos, palabra.length(), -1, 1);
            if (palabraEncontrada.equals(palabra))
                return true;            // Buscar diagonal superior izquierda.
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
    public int[][] posiblesSolucionesDe(String palabra) {
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
