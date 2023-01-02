package web.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import web.java.model.User;
import web.java.utils.ConnectDB;

public class UserDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public static String getEncodedString(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        String query = "select * from user";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getAllUserInPage(int page) {
        List<User> users = new ArrayList<User>();
        int start = (page - 1) * 10;
        String query = "select * from user limit ?, ? ";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, start);
            ps.setInt(2, 10);
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public int countUser() {
        String query = "select count(id) from User";
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

    public void signUp(String username, String password, String email, String fullname, String publicKey, boolean valid) {
        String query = "insert into User(username, password, email, fullname, publicKey, valid) values (?, ?, ?, ?, ?, ?)";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, fullname);
            ps.setString(5, publicKey);
            ps.setBoolean(6, valid);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteUserById(String id) {
        String query = "delete from user where id = ?";
        try {
            System.out.print(Integer.parseInt(id));
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String id) {
        User user = new User();
        String query = "select * from user where id = ?";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getBoolean(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = new User();
        String query = "select * from user where username = ?";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getBoolean(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUserById(String id, String avatar, String password, String fullname, String isAdmin) {
        String query = "update user set password = ?, fullname=?, role=?,avatar=? where id = ?";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, fullname);
            ps.setString(3, isAdmin);
            ps.setString(4, avatar);
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePublicKeyById(String id, String publicKey) {
        String query = "update user set publicKey=? where id = ?";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, publicKey);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateValidById(String id, boolean valid) {
        String query = "update user set valid=? where id = ?";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, valid);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUserByTotalDesc() {
        List<User> users = new ArrayList<User>();
        String query = "select user_id, sum(total) from ordertotal GROUP BY user_id order by sum(total) desc";
        try {
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getFloat(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean login(String username, String password) {
        String query = "select * from user where username = ? and password = ?";
        try {
            System.out.print(password);
            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void editUserNotPass(String id, String email, String fullname, String phone, String avatar) {
        String query = "update user set email=?, fullname=?, phone = ?, avatar=? where id = ?";
        try {

            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, fullname);
            ps.setString(3, phone);
            ps.setString(4, avatar);
            ps.setString(5, id);
            System.out.print(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editUserHavePass(String id, String password, String email, String fullname, String phone, String avatar) {
        String query = "update user set password = ?, email=?, fullname=?, phone = ?, avatar=? where id = ?";
        try {
            String newPass = getEncodedString(password);

            conn = new ConnectDB().getDBConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newPass);
            ps.setString(2, email);
            ps.setString(3, fullname);
            ps.setString(4, phone);
            ps.setString(5, avatar);
            ps.setString(6, id);
            System.out.print(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
