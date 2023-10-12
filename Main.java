import java.util.*;
public class Main {
    public static void main(String[] args) {



        int upperbound = 100;
        Random rand = new Random();
        Scanner in = new Scanner(System.in);

        int int_random = rand.nextInt(upperbound);

        Avl d = new Avl();

        int c [] = new int [10];
        int f [] ={6, 26, 26, 29, 49, 6, 65, 29, 85, 19};

        System.out.println("Thread 1 Started.");
        for (int i=0 ; i<10;i++){
            c[i] = int_random;
            System.out.print(c[i] + ", ");




            int_random = rand.nextInt(upperbound);
        }
        System.out.println();
        for (int i =0 ; i<10;i++){
            d.insert(f[i]);
            System.out.println((i+1)+ " step:");
            d.print();
        }
        System.out.println("Inordem: ");
        d.inordem(d.getRoot());
        System.out.println();
        System.out.println("Posordem: ");
        d.posordem(d.getRoot());
        System.out.println();
        System.out.println("Preordem: ");
        d.preordem(d.getRoot());
        while (true) {
            int x = 0;
            System.out.println("What value do you want to delete");
            x= in.nextInt();
            if(x==-1){
                break;
            }
            else {
                d.delete(d.getRoot(),x);
                d.print();
                System.out.println();

            }
        }
    }}
