import java.util.Random;
import java.util.Scanner;

public class Main_Normal {
    public static void main(String[] args) {
        int upperbound = 100;
        Random rand = new Random(1234);
        Scanner in = new Scanner(System.in);

        int int_random = rand.nextInt(upperbound);

        BinaryTree d = new BinaryTree ();


        int f [] ={6, 26, 26, 29, 49, 6, 65, 29, 85, 19,2,4};
        String numbers = "";
        System.out.println("Thread 1 Started.");
        for (int i=0 ; i<100;i++){
            d.push(int_random);
            numbers += int_random + " ,";






            int_random = rand.nextInt(upperbound);
        }
        System.out.println(numbers);

        d.print();
       /* System.out.println("Inordem: ");
        d.inordem(d.getRoot());
        System.out.println();
        System.out.println("Posordem: ");
        d.posordem(d.getRoot());
        System.out.println();
        System.out.println("Preordem: ");
        d.preordem(d.getRoot()); */
        while (true) {
            int x = 0;
            System.out.println("What value do you want to delete");
            x= in.nextInt();
            if(x==-1){
                break;
            }
            else {
                d.delete_node(d.getRoot(),x);
                d.print();
                System.out.println();

            }
        }
    }
}
