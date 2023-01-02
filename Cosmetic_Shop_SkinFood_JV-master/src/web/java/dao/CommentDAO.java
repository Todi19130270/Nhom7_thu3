package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.java.model.Comment;
import web.java.utils.ConnectDB;

public class CommentDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List<Comment> getAllComment() {
	List<Comment> comments = new ArrayList<Comment>();
	String query = "select * from comment";
	try {
	    conn = new ConnectDB().getDBConnection();
	    ps = conn.prepareStatement(query);
	    rs = ps.executeQuery();
	    while (rs.next()) {
		comments.add(new Comment(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
			rs.getDate(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return comments;
    }
}
