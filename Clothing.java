public class Clothing extends Product  {

    private String size;
    private String color;

    public Clothing(String product_id,String productName, int availableQuantity, double priceProduct,String product_type,String size,String color) {
        super(product_id,productName,availableQuantity,priceProduct,product_type);
        this.size = size;
        this.color=color;

    }

    public Clothing( String productName, String product_id, double priceProduct) {
        super(product_id, productName, priceProduct);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "Name: "+getProductName()+ "\nId: "+getProduct_id()+ "\nAvailable Quantity:"+ getAvailableQuantity()+"\nPrice:"+getPriceProduct()+"\nType: Clothing"+"\nSize:"+getSize()+"\nColor:"+getColor();
    }
}
