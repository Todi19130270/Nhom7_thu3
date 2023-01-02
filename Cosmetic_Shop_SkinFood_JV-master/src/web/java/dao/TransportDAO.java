package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Transport;
import web.java.utils.ConnectDB;

public class TransportDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Transport> getAllTransport() {
	List<Transport> transports = new ArrayList<Transport>();
	String query = "select * from transport";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		transports.add(new Transport(rs.getInt(1), rs.getString(2), rs.getFloat(3) ));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return transports;
    }
    
    public String getTransportNameById(String id) {
	String query = "select * from transport where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);;
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return rs.getString(2);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "";
    }
    
    public double getTransportFeeById(String id) {
	String query = "select * from transport where id = ?";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);;
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return rs.getDouble(3);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return 0;
    }
    
    public Transport getDefaultTransport() {
	String query = "select * from transport limit 1";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		return new Transport(rs.getInt(1), rs.getString(2), rs.getFloat(3));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
}
