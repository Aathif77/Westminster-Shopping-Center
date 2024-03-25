
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WestminsterShoppingManager pr1 = new WestminsterShoppingManager();
        Gui pr2=new Gui();
        pr1.loadProduct();
        while (true) {

            pr1.Start();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter option number:");
            String user_input1 = input.nextLine();
            switch (user_input1) {
                case "1" -> pr1.addProduct();
                case "2" -> pr1.deleteProduct();
                case "3" -> pr1.printProduct();
                case "4" -> pr1.saveProduct();
                case "5" -> pr2.gui();
                case "6" -> {
                    System.out.println("\n*** Thank You ***\n");
                    System.exit(6);
                }
                default -> System.out.println("Please try a valid option!");
            }
        }
    }
}