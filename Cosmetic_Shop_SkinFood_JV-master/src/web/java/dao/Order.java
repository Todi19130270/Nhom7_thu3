package web.java.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import web.java.model.CartItem;
import web.java.model.OrderSingle;
import web.java.model.OrderTotal;
import web.java.utils.ConnectDB;

public class Order {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<OrderSingle> getAllSingleOrder() {
	List<OrderSingle> orderSingles = new ArrayList<OrderSingle>();
	String query = "select * from ordersingle";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		orderSingles.add(
			new OrderSingle(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getInt(4), rs.getString(5)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return orderSingles;
    }

    public List<OrderTotal> getAllTotalOrder() {
	List<OrderTotal> orderTotals = new ArrayList<OrderTotal>();
	String query = "select * from ordertotal order by time_order desc";

	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		orderTotals.add(
			new OrderTotal(rs.getString(1), rs.getFloat(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
				rs.getString(6), rs.getFloat(7), rs.getInt(8), rs.getString(9), rs.getTimestamp(10)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return orderTotals;
    }

    public List<OrderTotal> getAllTotalOrder2() {
	List<OrderTotal> orderTotals = new ArrayList<OrderTotal>();
	String query = "select * from ordertotal order by time_order desc";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		orderTotals.add(
			new OrderTotal(rs.getString(1), rs.getFloat(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
				rs.getString(6), rs.getFloat(7), rs.getInt(8), rs.getString(9), rs.getTimestamp(10)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return orderTotals;
    }

    public void addOrderTotal(ArrayList<CartItem> cartItems, String orderName, String orderAddress, String orderPhone,
	    String transport, String magiamgia, String orderNote, Double total, String userLogin) {
	String id = createRandomString();
	String queryOrderTotal = "insert into ordertotal(id, total, note, transport_id, user_id, phone, discount, status, name, time_order)"
		+ "values (?,?,?,?,?,?,?,0,?,?)";
	try {
	    Date date = new Date();
	    Timestamp timestamp = new Timestamp(date.getTime());
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(queryOrderTotal);
	    ps.setString(1, id);
	    ps.setDouble(2, total);
	    ps.setString(3, orderNote);
	    ps.setString(4, transport);
	    if (userLogin.isEmpty() == true) {
		ps.setInt(5, -1);
	    } else {
		ps.setString(5, userLogin);
	    }
	    ps.setString(6, orderPhone);
	    ps.setDouble(7, new CouponDAO().getCouponDiscountAmount(magiamgia));
	    ps.setString(8, orderName);
	    ps.setTimestamp(9, timestamp);
	    System.out.println(ps);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	createOrderSingle(id, cartItems);

    }

    public void createOrderSingle(String idTotal, ArrayList<CartItem> cartItems) {
	for (CartItem cartItem : cartItems) {
	    String query = "insert into ordersingle(product_id , price, number, orderTotal_id)" + "values (?, ?, ?, ?)";
	    try {
		conn = new ConnectDB().getDBConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, cartItem.getProduct().getId());
		ps.setDouble(2, cartItem.getProduct().getPrice());
		ps.setInt(3, cartItem.getNumber());
		ps.setString(4, idTotal);
		ps.executeUpdate();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    updateProductQtt(cartItem.getProduct().getId(),
		    (new ProductDAO().getQttById(cartItem.getProduct().getId())) - cartItem.getNumber());
	    updateProductSold(cartItem.getProduct().getId(), cartItem.getProduct().getSold() + cartItem.getNumber());
	}

    }

    public void updateProductQtt(int idProduct, int quantity) {

	String query = "update product set quantity = ? where id = ? ";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, quantity);
	    ps.setInt(2, idProduct);
	    ps.executeUpdate();
	    System.out.println(ps);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void updateProductSold(int idProduct, int quantity) {
	String query = "update product set sold = ? where id = ? ";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, quantity);
	    ps.setInt(2, idProduct);
	    ps.executeUpdate();
	    System.out.println(ps);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String createRandomString() {
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
	StringBuilder sb = new StringBuilder(10);
	for (int i = 0; i < 10; i++) {
	    int index = (int) (AlphaNumericString.length() * Math.random());
	    sb.append(AlphaNumericString.charAt(index));
	}
	return sb.toString();
    }

    public List<OrderTotal> getAllHistoryTransactionById(int id) {
	List<OrderTotal> totalOrder = new ArrayList<OrderTotal>();
	if (id == -1) {
	    String query = "SELECT * FROM ordertotal where user_id = ? order by time_order DESC limit 1";
	    try {
		conn = new ConnectDB().getDBConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
		    totalOrder.add(new OrderTotal(rs.getString(1), rs.getFloat(2), rs.getString(3), rs.getInt(4),
			    rs.getInt(5), rs.getString(6), rs.getFloat(7), rs.getInt(8), rs.getString(9),
			    rs.getTimestamp(10)));
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return totalOrder;
	} else {
	    String query = "SELECT * FROM ordertotal where user_id = ? order by time_order desc";
	    try {
		conn = new ConnectDB().getDBConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
		    totalOrder.add(new OrderTotal(rs.getString(1), rs.getFloat(2), rs.getString(3), rs.getInt(4),
			    rs.getInt(5), rs.getString(6), rs.getFloat(7), rs.getInt(8), rs.getString(9),
			    rs.getTimestamp(10)));
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return totalOrder;
	}
//	return totalOrder;
    }

    public List<OrderSingle> getAllOrderSingleById(int id) {
	List<OrderSingle> orderSingles = new ArrayList<OrderSingle>();
	String query = "select * from ordersingle where orderTotal_id in ( select id from ordertotal where user_id = ?)";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, id);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		orderSingles.add(
			new OrderSingle(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getInt(4), rs.getString(5)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return orderSingles;
    }

    public void changeStatusOrder(String id, int status) {
	String query = "UPDATE ordertotal SET status = ? WHERE id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setInt(1, status);
	    ps.setString(2, id);
	    ps.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
