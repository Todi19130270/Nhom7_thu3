package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Event;
import web.java.model.EventAndProduct;
import web.java.model.Product;
import web.java.utils.ConnectDB;

public class EventDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Event> getAllEvent() {
	List<Event> events = new ArrayList<Event>();
	String query = "select * from event";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		events.add(new Event(rs.getInt(1), rs.getString(2), rs.getString(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return events;
    }
    public Event getEventById(String id) {
	Event event = null;
	String query = "select * from event where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		event = new Event(rs.getInt(1), rs.getString(2), rs.getString(3));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return event;
    }
    
    public List<EventAndProduct> getAllEventProduct(){
	List<EventAndProduct> eventAndPros = new ArrayList<EventAndProduct>();
	String query = "select * from event_product";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		eventAndPros.add(new EventAndProduct(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return eventAndPros;
    }
    
    public List<EventAndProduct> getEventProductByEventId(String id){
	List<EventAndProduct> eventAndPros = new ArrayList<EventAndProduct>();
	String query = "select * from event_product where event_id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		eventAndPros.add(new EventAndProduct(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return eventAndPros;
    }
    
    public void addEventProduct(String eventId, String productId) {
	String query = "insert into event_product(event_id, product_id) values (?, ?)";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, eventId);
	    ps.setString(2, productId);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void deleteEventProduct(String eventId, String productId) {
	String query = "delete from event_product where event_id = ? and product_id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, eventId);
	    ps.setString(2, productId);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public List<Product> getProductNotInEvent(String id){
	String query = "select * from product where id not in (select product_id from event_product where event_id = ?)";
	List<Product> products = new ArrayList<Product>();
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while(rs.next()) {
		products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
			rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return products;
    }
    
    public List<Product> getProductInEvent(String start){
	String query = "select * from product where id in (select product_id from event_product where event_id = 8) limit ?,6 ";
	List<Product> products = new ArrayList<Product>();
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, Integer.parseInt(start) );
	    rs = ps.executeQuery();
	    while(rs.next()) {
		products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
			rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return products;
    }
    
    public List<Product> getProductInEventRan(String start){
	String query = "select * from product where id in (select product_id from event_product where event_id = ?) order by rand() ";
	List<Product> products = new ArrayList<Product>();
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, Integer.parseInt(start) );
	    rs = ps.executeQuery();
	    while(rs.next()) {
		products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
			rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return products;
    }
    
    public void deleteCouponById(String id) {
	String query = "delete from coupon where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
}
