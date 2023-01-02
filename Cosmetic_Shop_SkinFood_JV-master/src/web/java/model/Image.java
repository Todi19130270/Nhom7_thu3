package web.java.model;

public class Image {
    private int id;
    private String image;
    private int product;
    
    
    public Image(int id, String image, int product) {
	super();
	this.id = id;
	this.image = image;
	this.product = product;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getProduct() {
        return product;
    }
    public void setProduct(int product) {
        this.product = product;
    }
    
    
}
