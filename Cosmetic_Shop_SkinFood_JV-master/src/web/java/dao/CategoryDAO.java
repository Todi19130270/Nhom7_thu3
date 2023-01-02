package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Category;
import web.java.utils.ConnectDB;

public class CategoryDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Category> getAllCategory() {
	List<Category> categories = new ArrayList<Category>();
	String query = "select * from category";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return categories;
    }
    
    public String getCategoryNameById(String id) {
	String query = "select * from category where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return rs.getString(2);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "";
    }
    
    public String getCategoryNameByIdRandom(String id) {
	String query = "select * from category where id = ? order by rand()";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return rs.getString(2);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "";
    }
    
    public String getCollectionNameById(String id) {
	String query = "select * from category where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return rs.getString(3);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "";
    }
    
    public List<Category> getCategorySames(String id) {
	List<Category> categories = new ArrayList<Category>();
	String query = "select * from category where collection_id = ? limit 6";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return categories;
    }
    
    
    public List<Category> getMakeupCate() {
	List<Category> categories = new ArrayList<Category>();
	String query = "select * from category where collection_id = 2 limit 6";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return categories;
    }
}
