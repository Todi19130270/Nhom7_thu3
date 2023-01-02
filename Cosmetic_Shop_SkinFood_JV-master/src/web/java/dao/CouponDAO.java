package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Coupon;
import web.java.utils.ConnectDB;

public class CouponDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Coupon> getAllCoupon() {
	List<Coupon> coupons = new ArrayList<Coupon>();
	String query = "select * from coupon";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		coupons.add(new Coupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return coupons;
    }
    
    public double getCouponDiscountAmount(String code) {
	String query = "select * from coupon where code = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, code);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return rs.getDouble(4);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return 0;
    }
    
    public void addCoupon(String coupon, String event, String discount, String number) {
	String query = "insert into coupon(code, event_id, discount_amount, remain) values (?, ?, ?, ?)";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, coupon);
	    ps.setString(2, event);
	    ps.setString(3, discount);
	    ps.setString(4, number);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
    }
}
