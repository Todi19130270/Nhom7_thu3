package web.java.model;

import web.java.dao.TransportDAO;

public class Transport {
    private int id;
    private String name;
    private double price;
    
    public Transport(int id, String name, double price) {
	super();
	this.id = id;
	this.name = name;
	this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    
    public String getTransportNameById() {
	return new TransportDAO().getTransportNameById(Integer.toString(this.id));
    }
    
    public double getTransportFeeById() {
   	return new TransportDAO().getTransportFeeById(Integer.toString(this.id));
       }
    
    
}
