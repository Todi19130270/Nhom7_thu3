package web.java.model;

public class Address {
    private int id;
    private int user;
    private int address;
    
    
    public Address(int id, int user, int address) {
	super();
	this.id = id;
	this.user = user;
	this.address = address;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUser() {
        return user;
    }
    public void setUser(int user) {
        this.user = user;
    }
    public int getAddress() {
        return address;
    }
    public void setAddress(int address) {
        this.address = address;
    }
    
    
    
}
