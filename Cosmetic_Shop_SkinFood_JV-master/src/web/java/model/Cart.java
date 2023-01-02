package web.java.model;

import java.util.ArrayList;

public class Cart {
    public ArrayList<CartItem> cartItems;
    public int userId;

    public Cart(int userId) {
	super();
	this.cartItems = new ArrayList<CartItem>();
	this.userId = userId;
    }

    public void addToCart(int id,CartItem cartItem, int qtt) {
	int count = 0;
	for(CartItem cartItemThis: this.cartItems) {
	    if(cartItemThis.getProduct().getId() == id) {
//		index = cartItems.indexOf(cartItemThis);
		cartItemThis.setNumber(cartItemThis.getNumber()+qtt);
		count++;
	    }
	}
	if(count==0) {
	    cartItems.add(cartItem);
	}
	
    }

    public void removeToCart(int id) {
	cartItems.remove(id);
    }
    
    public ArrayList<CartItem> getCartItems() {
	return this.cartItems;
    }
    
    public double getTotal() {
	double total = 0;
	for(CartItem cartItem : this.cartItems) {
	    total += cartItem.getTotalSingle();
	}
	return total;
    }
    
    public double getTotalDiscount(Double transport, Double percent) {
	double total = transport;
	for(CartItem cartItem : this.cartItems) {
	    total += cartItem.getTotalSingle();
	}
	return total*(1-percent/100);
    }
    
    public String getFormatTotal() {
	return String.format("%1$,.0f", this.getTotal());
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    @Override
    public String toString() {
	return "Cart [cartItems=" + cartItems + ", userId=" + userId + "]";
    }
    
   

}
