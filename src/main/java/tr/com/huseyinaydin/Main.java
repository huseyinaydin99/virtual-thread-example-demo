package tr.com.huseyinaydin;

import java.util.stream.IntStream;

public class Main {
    static int i = 0;
    static int j = 0;

    public static void main(String[] args) throws InterruptedException {
        //        Thread.ofPlatform()
//                .start(()->System.out.println("Platform Thread : "+Thread.currentThread()));
//        System.out.println("------------------------------");
//
//        Thread.ofVirtual()
//                .start(()->System.out.println("Virtual Thread : "+Thread.currentThread()));
//    }

        var start = System.currentTimeMillis();
        var totalThread = 1000;
        var threads = IntStream.range(0, totalThread)
                .mapToObj(thCount -> Thread.ofVirtual().unstarted(() -> {

                    Main.i = 0;
                    while (Main.i < 2) {
                        //System.out.print(Thread.currentThread().getName() + " " + Main.i + " ");
                        System.out.println(Thread.currentThread().getName() + ". Thread'in " + Main.i + "'. işi. ");
                        Main.i++;
                    }
                    if (Main.i == 2)
                        System.out.print("\n");
                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/
                })).toList();
        threads.forEach((thread)->{
            thread.setName(String.valueOf(Main.j));
            Main.j++;
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var end = System.currentTimeMillis();
        System.out.println("\nHer Thread'ın (1000 tane) çalışması ve sonlanması için toplamda geçen süre.\nToplam Thread sayısı: " + totalThread + "\nTüm Thread'lerin başlangıcından bitişine kadar toplamda geçen süre (vthreads):" + (end - start) + "ms");
    }
}