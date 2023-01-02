package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Product;
import web.java.utils.ConnectDB;

public class ProductDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Product> getAllProduct() {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
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
    public List<Product> getProductByCategoryId(String id) {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where cate_id = ?";
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
    public List<Product> getProductByCollectionId(String id) {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where collection_id = ?";
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
    public List<Product> getProductByBrandId(String id) {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where brand_id = ?";
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
    public List<Product> getProductByCategoryIdRandom(String id) {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where cate_id = ?";
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
    
    public List<Product> topfiveproducts(String id) {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where cate_id = ? order by sold desc limit 5 ";
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

    public void addProductAdmin(String title, String description, String price, String category,String collection, String brand, String discount) {
	String query = "insert into product(title, description, rating, sold, price_default, cate_id, "
		+ "collection_id, brand_id, discountAmount) values (?, ? ,5, 0, ?, ?, ?, ?, ?)";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, title);
	    ps.setString(2, description);
	    ps.setString(3, price);
	    ps.setString(4, category);
	    ps.setString(5, collection);
	    ps.setString(6, brand);
	    ps.setString(7, discount);
	    System.out.println(ps);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public List<Product> getAllProductInPage(int page) {
	List<Product> products = new ArrayList<Product>();
	int start = (page-1)*10;
	String query = "select * from product limit ?, ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, start);
	    ps.setInt(2, 10);
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
    
    public List<Product> getAllProductInPageHaveQtt(int page) {
	List<Product> products = new ArrayList<Product>();
	int start = (page-1)*10;
	String query = "select * from product limit ?, ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, start);
	    ps.setInt(2, 10);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
			rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return products;
    }
    
    
    public Product getProductById(String id) {
	Product product = null;
	String query = "select * from product where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
			rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return product;
    }
    
    public int countProduct() {
	String query = "select count(id) from product";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return rs.getInt(1);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return 1;
    }
    
    public void deleteProductById(String id) {
	String query = "delete from product where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    ps.executeUpdate();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void editProductById(String id, String title, String description, String price,String category, String collection, String brand, String discount) {
	String query = "update product set title=?, description=?, price_default =?, cate_id=?, collection_id=?,brand_id=?,discountAmount=? where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, title);
	    ps.setString(2, description);
	    ps.setString(3, price);
	    ps.setString(4, category);
	    ps.setString(5, collection);
	    ps.setString(6, brand);
	    ps.setString(7, discount);
	    ps.setString(8, id);
	    System.out.print(ps);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public List<Product> getAllProductBestSeller() {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product order by sold desc limit 20";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
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
    public List<Product> getProductMakeUp() {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where collection_id = ? order by rand() limit 30";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, 2);
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
    
    public List<Product> getProductSkinCare(){
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product where collection_id = ? order by rand() limit 30";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, 3);
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
    
    public int getQttById(int id) {
	String query = "select * from product where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, id);
	    rs = ps.executeQuery();
	    while(rs.next()) {
		return rs.getInt(11);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return 0;
    }
    
    public void updateQtt(String product, String qtt) {
	String query = "update product set quantity = ? where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, qtt);
	    ps.setString(2, product);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
}
