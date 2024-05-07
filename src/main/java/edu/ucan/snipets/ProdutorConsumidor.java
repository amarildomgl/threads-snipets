package edu.ucan.snipets;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdutorConsumidor {

    public static void main(String[] args) {

//
//        Uma BlockingQueue em Java é uma interface que estende a interface java.util.Queue e fornece métodos
//        adicionais que suportam operações de bloqueio. são úteis em programação
//        concorrente, pois permitem que uma thread aguarde até que uma operação na fila possa ser realizada.

        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);

        Thread threadProdutor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Produzindo: " + i);
                    buffer.put(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });


        Thread threadConsumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int valor = buffer.take();
                    System.out.println("Consumindo: " + valor);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });


        threadProdutor.start();
        threadConsumidor.start();
    }
}
