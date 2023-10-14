import java.util.Random;
import java.util.Scanner;

public class Main_Normal {
    public static void main(String[] args) throws InterruptedException {

       // Thread.sleep(30000);
        int upperbound = 20000;
        Random rand = new Random(1234);
        Scanner in = new Scanner(System.in);

        int int_random = rand.nextInt(upperbound);

        BinaryTree d = new BinaryTree ();


        int f [] ={6, 26, 26, 29, 49, 6, 65, 29, 85, 19,2,4};
        String numbers = "";
        System.out.println("Thread 1 Started.");
        long startTime = System.nanoTime();



        for (int i=0 ; i<20000;i++){
            d.push(int_random);
            numbers += int_random + " ,";






            int_random = rand.nextInt(upperbound);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;



        d.print();
        System.out.println(numbers);
        System.out.println("Duracao insercao : "+ duration + " ms");

        System.out.print("What value do you want to search? ");
        int x = in.nextInt();
        Thread.sleep(20000);
        startTime = System.nanoTime();
        d.search(x);
        endTime = System.nanoTime();
        duration = endTime - startTime ;
        System.out.println("Duration of search : " + duration + " ns");
        Thread.sleep(5000);
      /*  int x = 0;
        System.out.println("What value do you want to delete");
        x= in.nextInt();
        Thread.sleep(30000);

        startTime = System.nanoTime();
        d.delete_node(d.getRoot(),x);
        endTime = System.nanoTime();
        d.print();
        duration = endTime - startTime;
        System.out.println("Duracao remocao : " + duration + " ns");
        System.out.println();
        Thread.sleep(10000);
        */
        }


    }

