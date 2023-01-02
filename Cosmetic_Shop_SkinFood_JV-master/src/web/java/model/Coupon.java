package web.java.model;

public class Coupon {
    private int id;
    private String code;
    private int event;
    private double discount;
    private int remain;
    
    public Coupon(int id, String code, int event, double discount, int remain) {
	super();
	this.id = id;
	this.code = code;
	this.event = event;
	this.discount = discount;
	this.remain = remain;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getEvent() {
        return event;
    }
    public void setEvent(int event) {
        this.event = event;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public int getRemain() {
        return remain;
    }
    public void setRemain(int remain) {
        this.remain = remain;
    }
    
    
}
