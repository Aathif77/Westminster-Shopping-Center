import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.util.Comparator;

import static org.junit.Assert.*;

public class WestminsterShoppingManagerTest {

    public void setInputForTesting(String... values) {
        String inputString = String.join(System.lineSeparator(), values);
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
    }

    @Test
    public void addProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        setInputForTesting("TestProduct", "10", "20.0", "1", "TestBrand", "12");
        shoppingManager.addProduct();
        assertTrue(WestminsterShoppingManager.productList.get(0) instanceof Electronics);
        assertEquals(1, WestminsterShoppingManager.productList.size());
    }

    @Test
    public void deleteProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product productToDelete = new Electronics("product001", "TestProduct", 5, 25.0,"Electronics", "TestBrand", 12);
        WestminsterShoppingManager.productList.add(productToDelete);
        setInputForTesting("product001");
        shoppingManager.deleteProduct();
        assertEquals(0, WestminsterShoppingManager.productList.size());
    }

    @Test   //this test will fail due to the whitespace added to build a table output, can check the difference
    public void printProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product1 = new Electronics("product001", "TestProduct1", 5, 125.0,"Electronics", "TestBrand", 12);
        Product product2 = new Clothing("product002", "TestProduct2", 8, 130.0,"Clothing", "M", "Blue");
        WestminsterShoppingManager.productList.add(product1);
        WestminsterShoppingManager.productList.add(product2);

        WestminsterShoppingManager.productList.sort(Comparator.comparing(Product::getProduct_id));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        shoppingManager.printProduct();
        String expectedOutput = "---------------------------------------------------------------------------------------------------------------------------------\n";
        expectedOutput += "| ProductID     | Product Name         | Available Quantity  | Price      | Type            | Brand / Size    | Warranty / Colour |\n";
        expectedOutput += "---------------------------------------------------------------------------------------------------------------------------------\n";
        expectedOutput += "| product001    | TestProduct1         | 5                   | 125.000000 | Electronics      | TestBrand       | 12                |\n";
        expectedOutput += "| product002    | TestProduct2         | 8                   | 130.000000 | Clothing         | M               | Blue              |\n";
        expectedOutput += "---------------------------------------------------------------------------------------------------------------------------------\n";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());

    }


    @Test
    public void saveProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Product product1 = new Electronics("product001", "TestProduct1", 5, 25.0,"Electronics", "TestBrand", 12);
        Product product2 = new Clothing("product002", "TestProduct2", 8, 30.0,"Clothing", "M", "Blue");
        WestminsterShoppingManager.productList.add(product1);
        WestminsterShoppingManager.productList.add(product2);
        shoppingManager.saveProduct();
        assertEquals(2, WestminsterShoppingManager.productList.size());
    }


}