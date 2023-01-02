package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Brand;
import web.java.model.Product;
import web.java.utils.ConnectDB;

public class BrandDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Brand> getAllBrand() {
	List<Brand> brands = new ArrayList<Brand>();
	String query = "select * from brand";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		brands.add(new Brand(rs.getInt(1), rs.getString(2)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return brands;
    }
    
    public List<Brand> getTopFiveBrandRan(){
	List<Brand> brands = new ArrayList<Brand>();
	String query = "select * from brand limit 10";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		brands.add(new Brand(rs.getInt(1), rs.getString(2)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return brands;
    }
    
    public String getBrandById(String id) {
	String query = "select * from brand where id = ?";
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
    
    public List<Product> topfiveproducts(String id) {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where brand_id = ? order by sold desc limit 5 ";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
			rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return products;
    }
}
