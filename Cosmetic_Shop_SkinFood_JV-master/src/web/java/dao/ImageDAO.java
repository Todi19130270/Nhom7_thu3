package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Image;
import web.java.utils.ConnectDB;

public class ImageDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Image> getAllImageById(String id) {
	List<Image> images = new ArrayList<Image>();
	String query = "select * from image where product_id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		images.add(new Image(rs.getInt(1), rs.getString(2), rs.getInt(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return images;
    }
    
    public void addImageById(String id, String image) {
	String query = "insert into image(image, product_id) values (?, ?)";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, image);
	    ps.setString(2, id);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public String findProductByImageId(String id) {
	String query = "select * from image where id = ?";
	String idUser = "";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    if(rs.next()) {
		idUser = rs.getString(3);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return idUser;
    }
    
    public void deleteImageById(String id) {
	String query = "delete from image where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public List<Image> getAllImage() {
	List<Image> images = new ArrayList<Image>();
	String query = "select * from image";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		images.add(new Image(rs.getInt(1), rs.getString(2), rs.getInt(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return images;
    }
    
    public Image getFirstImage(String id) {
	String query = "select * from image where product_id = ? limit 1";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return new Image(rs.getInt(1), rs.getString(2), rs.getInt(3));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return new Image(0, "", 0);
    }
    

}
