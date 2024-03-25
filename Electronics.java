public class Electronics extends Product  {

    private String brandName;
    private int warrantyPeriod;

    public Electronics(String product_id,String productName, int availableQuantity, double priceProduct,String product_type,String brandName, int warrantyPeriod) {
        super(product_id,productName,availableQuantity,priceProduct,product_type);
        this.brandName = brandName;
        this.warrantyPeriod=warrantyPeriod;
    }
    public Electronics(String product_id, String productName,double priceProduct){
        super(product_id,productName,priceProduct);

    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Name: "+getProductName()+ "\nId: "+getProduct_id()+ "\nAvailable Quantity:"+ getAvailableQuantity()+"\nPrice:"+getPriceProduct()+"\nType: Electronics"+"\nBrand Name:"+getBrandName()+"\nWarranty Period:"+getWarrantyPeriod()+" Months";
    }
}
