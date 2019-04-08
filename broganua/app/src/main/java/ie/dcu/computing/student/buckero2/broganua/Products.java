package ie.dcu.computing.student.buckero2.broganua;

public class Products{

    private String brand;
    private String model;
    private String price;
    private String retailer;
    private String image;
    private String link;
    public Products(){

    }

    public Products(String brand, String model, String price, String retailer, String image, String link) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.retailer = retailer;
        this.image = image;
        this.link = link;


    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
