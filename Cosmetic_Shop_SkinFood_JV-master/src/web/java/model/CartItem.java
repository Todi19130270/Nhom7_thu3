package web.java.model;

public class CartItem {
    public Product product;
    public int number;
  
    public CartItem(Product product, int number) {
	super();
	this.product = product;
	this.number = number;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public double getTotalSingle() {
        return this.getProduct().getPrice()*this.getNumber();
    }
    
    public String getTotalSingleFormat() {
	//this.getProduct().getPrice()*this.getNumber()
	return String.format("%1$.0f", getTotalSingle());
    }
    @Override
    public String toString() {
	return "CartItem [product=" + product + ", number=" + number + "]\n";
    }
    
    
    
}
