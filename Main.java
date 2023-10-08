import java.util.*;
public class Main {
    public static void main(String[] args) {



        int upperbound = 100;
        Random rand = new Random();
        Scanner in = new Scanner(System.in);

        int int_random = rand.nextInt(upperbound);

        Avl d = new Avl();

        int c [] = new int [10];

        System.out.println("Thread 1 Started.");
        for (int i=0 ; i<10;i++){
            c[i] = int_random;
            System.out.print(c[i] + ", ");




            int_random = rand.nextInt(upperbound);
        }
        for (int i =0 ; i<10;i++){
            d.insert(c[i]);
            d.print();
        }


    }}
