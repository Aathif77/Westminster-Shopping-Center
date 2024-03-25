import java.io.Serializable;

public abstract class Product implements Serializable {



    private String product_id;
    private String productName;
    private int availableQuantity;
    private double priceProduct;
    private String product_type;






    public Product(String product_id,String productName, int availableQuantity, double priceProduct, String product_type ) {
        this.productName = productName;
        this.product_id=product_id;
        this.availableQuantity=availableQuantity;
        this.priceProduct=priceProduct;
        this.product_type=product_type;

    }
    public Product(String product_id,String productName,double priceProduct){
        this.productName=productName;
        this.product_id=product_id;
        this.priceProduct=priceProduct;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public double getPriceProduct() {
        return priceProduct;
    }


    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }
}
