import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager{

    public static List<Product>productList=new ArrayList<>();

    public WestminsterShoppingManager(){
        productList=new ArrayList<>();
    }

    public void Start() {
        System.out.println("-----------------------------");
        System.out.println("Welcome to Westminster Shopping :)");
        System.out.println("-----------------------------");
        System.out.println("Please Select an Option:\n 1. Add New Product \n 2. Delete Existing Product\n 3. Print list of all Products\n 4. Save in a file\n 5. Open Customer Interface\n 6. Quit");
        System.out.println("-----------------------------");
    }


    @Override
    public void addProduct() {
        Scanner input = new Scanner(System.in);
        String pro_Id = GenerateID(productList,"product");
        if (productList.size() <= 50) {
            System.out.println(50-productList.size()+" Entries Left");
            System.out.print("Enter Product Name: ");
            String pro_Name = input.nextLine().trim();
            while (pro_Name.isEmpty()) {
                System.out.println("Please enter a Name to Continue!");
                System.out.print("Enter Product Name: ");
                pro_Name = input.nextLine().trim();
            }

            System.out.print("Enter Available Quantity: ");
            int available_quantity;
            while (true) {
                try {
                    available_quantity = input.nextInt();
                    if (available_quantity <= 0) {
                        System.out.println("\nPlease Enter a Valid Integer!");
                        System.out.print("Enter Available Quantity: ");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println(" \nPlease Enter a valid Integer Value");
                    input.next();
                    System.out.print("Enter Available Quantity: ");
                }
            }

            System.out.print("Enter the Product Price: ");
            double pro_price;
            while (true) {
                try {
                    pro_price = input.nextDouble();
                    if (pro_price <= 0) {
                        System.out.println("\nPlease Enter a valid Integer");
                        System.out.print("Enter the Product Price: ");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.Please enter a valid number.");
                    input.next();
                    System.out.print("Enter the Product Price: ");
                }
            }

            System.out.println("Choose Product Type: ");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");
            System.out.print("Enter your choice: ");
            String product_type = input.next();

            switch(product_type){
            case "1"-> {
                System.out.print("Enter Brand Name: ");
                String brandName = input.next();

                System.out.print("Enter Warranty Period(Months): ");
                int warrantyPeriod;
                while (true) {
                    try {
                        warrantyPeriod = input.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPlease Enter valid Integer!");
                        input.next();
                        System.out.print("Enter the warranty period: ");
                    }
                }
                Electronics pr1=new Electronics(pro_Id, pro_Name, available_quantity, pro_price,"Electronics", brandName, warrantyPeriod);
                productList.add(pr1);
                System.out.println("Electronics Product Added successfully.");

            } case "2"-> {
                System.out.print("Enter the size: ");
                String size = input.next();

                System.out.print("Enter the colour: ");
                String color = input.next();

                Clothing pr2=new Clothing(pro_Id, pro_Name, available_quantity,pro_price,"Clothing", size, color);
                productList.add(pr2);
                System.out.println("Clothing Product is added successfully.");
            }default -> System.out.println("Invalid Input!\nTry Again from First! ");
            }
        } else {
            System.out.println("\nMaximum value of 50 Products reached! Delete Product to Add!");
        }

    }
    public static String GenerateID(List<Product> currentIDs, String prefix) {
        int maximumID = 1;
        for (Product currentID : currentIDs) {
            if (currentID.getProduct_id().startsWith(prefix)) {
                try {
                    int id = Integer.parseInt(currentID.getProduct_id().substring(prefix.length()));
                    if (id > maximumID) {
                        maximumID = id;

                    }
                } catch (NumberFormatException exe) {
                    System.out.println(exe);
                }
            }maximumID++;
        }

        return String.format("%s%03d", prefix, maximumID);
    }


    @Override
    public void deleteProduct()  { // delete the product according to
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        String remove_id = input.next();
        boolean productFound = false;

        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProduct_id().equals(remove_id)) {

                System.out.println("Deleted Product Information:");
                System.out.println("Product ID: " + product.getProduct_id());
                System.out.println("Product Type: " + (product instanceof Electronics ? "Electronics" : "Clothing"));

                iterator.remove();
                productFound = true;
                break;
            }
        }
        if (productFound) {
            System.out.println("Product deleted successfully.");
            System.out.println("Total number of products left in the system: " + productList.size());
        } else {
            System.out.println("Product with ID " + remove_id + " not found.");
        }
    }

    @Override
    public void printProduct() {    // prints all the product details
        List<Product> productList2 = new ArrayList<>(productList);
        productList2.sort(Comparator.comparing(Product::getProduct_id));

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-20s | %-10s | %-15s | %-15s | %-10s |\n",
                "ProductID", "Product Name", "Available Quantity", "Price", "Type","Brand / Size","Warranty / Colour");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        for (Product product : productList2) {
            if (product instanceof Electronics option1) {
                System.out.printf("| %-10s | %-20s | %-20d | %-10f | %-15s | %-15s | %-15s |\n",
                        option1.getProduct_id(),option1.getProductName(), option1.getAvailableQuantity(),
                        option1.getPriceProduct(), "Electronics",option1.getBrandName(),option1.getWarrantyPeriod());
            } else if (product instanceof Clothing option2) {
                System.out.printf("| %-10s | %-20s | %-20d | %-10f | %-15s | %-15s | %-15s |\n",
                        option2.getProduct_id(),option2.getProductName(), option2.getAvailableQuantity(),
                        option2.getPriceProduct(), "Clothing",option2.getSize(),option2.getColor());
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    }



    @Override
    public void saveProduct() {
        try {
            FileOutputStream f_out = new FileOutputStream("product2.ser");
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(productList);
            f_out.close();
            System.out.println("Products saved successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }



    @Override
    public void loadProduct() {
        File newFile = new File("product2.ser");
        if (newFile.exists()) {
            try {
                FileInputStream f_in = new FileInputStream("product2.ser");
                ObjectInputStream obj_in = new ObjectInputStream(f_in);
                productList = (ArrayList<Product>) obj_in.readObject();
            } catch (Exception e) {
                System.out.println(e);

            }
        }else
            System.out.println("File Not exist");
    }



}



