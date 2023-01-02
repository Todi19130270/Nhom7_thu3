package web.java.model;

import web.java.dao.AddressDAO;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private int role;
    private String avatar;
    public float total;
    public String phone;
    private String publicKey;


    private boolean valid;

    public User() {
        super();
    }

    public User(int id, float total) {
        super();
        this.id = id;
        this.total = total;
    }

    public User(int id, String username, String password, String email, String fullname, int role, String avatar) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
        this.avatar = avatar;
    }

    public User(int id, String username, String password, String email, String fullname, int role, String avatar,
                float total) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
        this.avatar = avatar;
        this.total = total;
    }


    public User(int id, String username, String password, String email, String fullname, int role, String avatar,
                float total, String phone, String publicKey, boolean valid) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
        this.avatar = avatar;
        this.total = total;
        this.phone = phone;
        this.publicKey = publicKey;
        this.valid = valid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDefaultAddress() {
        return new AddressDAO().getDefaultAddressByUserId(this.getId());
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
                + ", fullname=" + fullname + ", role=" + role + ", avatar=" + avatar + ", total=" + total + "]";
    }

}
