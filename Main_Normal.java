import java.util.Random;
import java.util.Scanner;

public class Main_Normal {
    public static void main(String[] args) throws InterruptedException {

        //Thread.sleep(30000);
        int upperbound = 10000;
        Random rand = new Random(1234);
        Scanner in = new Scanner(System.in);

        int int_random = rand.nextInt(upperbound);

        BinaryTree d = new BinaryTree();

        String numbers = "";
        long startTime = System.nanoTime();


        for (int i = 0; i < 10000; i++) {
            d.push(int_random);
            numbers += int_random + " ,";


            int_random = rand.nextInt(upperbound);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;


        d.print();
        System.out.println(numbers);
        System.out.println("Duracao insercao : " + duration + " ms");
        //Thread.sleep(10000);
       /* System.out.print("What value do you want to search? ");
        int x = in.nextInt();
        Thread.sleep(20000);
        startTime = System.nanoTime();
        d.search(x);
        endTime = System.nanoTime();
        duration = endTime - startTime ;
        System.out.println("Duration of search : " + duration + " ns");
        Thread.sleep(5000); */
        int x = 0;
        System.out.println("What value do you want to delete");
        x = in.nextInt();
        Thread.sleep(30000);

        startTime = System.nanoTime();
        d.delete_node(d.getRoot(), x);
        endTime = System.nanoTime();
        d.print();
        duration = endTime - startTime;
        System.out.println("Duracao remocao : " + duration + " ns");
        System.out.println();
        Thread.sleep(10000);

    }


}

