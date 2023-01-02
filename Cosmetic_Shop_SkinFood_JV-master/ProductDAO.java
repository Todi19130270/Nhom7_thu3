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

    public List<Product> getAllUser() {
	List<Product> products = new ArrayList<Product>();
	String query = "select * from product";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
			rs.getInt(5), rs.getDouble(6), rs.getInt(7),rs.getInt(8),rs.getInt(9), rs.getInt(10) ));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return products;
    }
}
