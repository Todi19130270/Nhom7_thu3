package web.java.model;

public class EventAndProduct {
    private int id;
    private int event;
    private int product;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEvent() {
        return event;
    }
    public void setEvent(int event) {
        this.event = event;
    }
    public int getProduct() {
        return product;
    }
    public void setProduct(int product) {
        this.product = product;
    }
    public EventAndProduct(int id, int event, int product) {
	super();
	this.id = id;
	this.event = event;
	this.product = product;
    }
    
    
}
