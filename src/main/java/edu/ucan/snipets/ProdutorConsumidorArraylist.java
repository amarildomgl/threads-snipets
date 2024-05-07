package edu.ucan.snipets;

import java.util.ArrayList;
import java.util.List;

public class ProdutorConsumidorArraylist {

    public static void main(String[] args) {
        List<Integer> buffer = new ArrayList<>();

        Thread threadProdutor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    synchronized (buffer) {
                        // estamos limitando o tamanho do buffer a 5 elementos
                        while (buffer.size() == 5) {

                            buffer.wait();
                        }
                        buffer.add(i);
                        System.out.println("Produzindo: " + i);
                        buffer.notifyAll();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadConsumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    synchronized (buffer) {
                        while (buffer.isEmpty()) {
                            buffer.wait();
                        }
                        int valor = buffer.remove(0);
                        System.out.println("Consumindo: " + valor);
                        buffer.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadProdutor.start();
        threadConsumidor.start();
    }
}
