package com.api;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static int[] geraSequenciaReferencia(int numeroReferencia, int maximoNumeroPagina) {
        Random random = new Random();
        int[] sequenciaReferencia = new int[numeroReferencia];

        for (int i = 0; i < numeroReferencia; i++) {
            sequenciaReferencia[i] = random.nextInt(maximoNumeroPagina);
        }
        return sequenciaReferencia;
    }

    public static int simularFifo(int[] sequenciaReferncia, int moldura) {
        int faltaPagina = 0;
        List<Integer> molduras = new ArrayList<>();

        for (int pagina : sequenciaReferncia) {
            if (!molduras.contains(pagina)) {
                faltaPagina++;
                if (molduras.size() == moldura) {
                    molduras.remove(0);
                }
                molduras.add(pagina);
            }
        }
        return faltaPagina;
    }

    public static int simularEnvelhecimento(int[] sequenciaReferencia, int moldura) {
        int faltaPagina = 0;
        Map<Integer, Integer> molduras = new HashMap<>();

        for (int pagina : sequenciaReferencia) {
            if (!molduras.containsKey(pagina)) {
                faltaPagina++;
                if (molduras.size() == moldura) {
                    int molduraVelha = Collections.min(molduras.entrySet(), Map.Entry.comparingByValue()).getKey();
                    molduras.remove(molduraVelha);
                }
                molduras.put(pagina, Integer.MAX_VALUE);
            } else {
                molduras.put(pagina, Integer.MAX_VALUE);
            }

            molduras.replaceAll((k, v) -> v >> 1);
        }

        return faltaPagina;
    }

    public static void main(String[] args) {
        int numeroReferencia = 1000;
        int[] tamanhoMoldura = {4};
        int maximoNumeroPagina = 50;

        int[] sequenciareferencia = geraSequenciaReferencia(numeroReferencia, maximoNumeroPagina);

        try (FileWriter escritor = new FileWriter("sequencia.txt")) {
            for (int referencia : sequenciareferencia) {
                escritor.write(referencia + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Comparação dos algoritimos FIFO e envelhecimento");
        System.out.println();
        for (int moldura : tamanhoMoldura) {
            int faltaPaginaFifo = simularFifo(sequenciareferencia, moldura);
            int faltaPaginaEnvelhecimento = simularEnvelhecimento(sequenciareferencia, moldura);

            System.out.println("Tamanho das molduras: " + moldura);
            System.out.println("Falta de páginas FIFO: " + faltaPaginaFifo);
            System.out.println("Falta de páginas envelhecimento: " + faltaPaginaEnvelhecimento);
            System.out.println();
        }

    }
}
