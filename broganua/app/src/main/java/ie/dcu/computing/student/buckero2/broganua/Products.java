package ie.dcu.computing.student.buckero2.broganua;

public class Products {

    private String brand;
    private String model;
    private String price;
    private String image;
    public Products(){

    }

    public Products(String brand, String model, String price, String image) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.image = image;

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

    public void setProductImage(String image) {
        this.image = image;
    }
}
