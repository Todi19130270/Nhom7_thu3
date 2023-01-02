package web.java.model;

import java.util.List;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.ImageDAO;

public class Product {
    private int id;
    private String title;
    private String description;
    private double rating;
    private int sold;
    private double priceDefault;
    private int category;
    private int collection;
    private int brand;
    private int discount;
    private int qtt;
    

    public Product(int id, String title, String description, double rating, int sold, double priceDefault, int category,
	    int collection, int brand, int discount, int qtt) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.rating = rating;
	this.sold = sold;
	this.priceDefault = priceDefault;
	this.category = category;
	this.collection = collection;
	this.brand = brand;
	this.discount = discount;
	this.qtt = qtt;
    }
    
    public String getFirstImage() {
	return new ImageDAO().getFirstImage(Integer.toString(this.getId())).getImage();
    }

    public Product(int id, String title, String description, double rating, int sold, double priceDefault, int category,
	    int collection, int brand, int discount) {
	this.id = id;
	this.title = title;
	this.description = description;
	this.rating = rating;
	this.sold = sold;
	this.priceDefault = priceDefault;
	this.category = category;
	this.collection = collection;
	this.brand = brand;
	this.discount = discount;
    }
   
    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public double getPriceDefault() {
        return priceDefault;
    }
    
    public double getPrice() {
	return this.priceDefault*(100-this.discount)/100;
    }
    
    public static String getPriceStaticFormat(String price) {
	Double price2 = Double.parseDouble(price);
	return String.format("%1$,.0f", price2);
    }
    
    public String getFormatPriceDefault() {
	return String.format("%1$,.0f", (double)this.getPriceDefault());
    }
    
    public String getFormatPriceStandard() {
	double price = this.getPriceDefault()*(100-this.getDiscount())/100; 
	return String.format("%1$,.0f", price);
    }
    
    public String getFormatPrice(double price) {
	return String.format("%1$,.0f", (double)price );
    }

    public void setPriceDefault(double priceDefault) {
        this.priceDefault = priceDefault;
    }

    public int getCategory() {
        return category;
    }
    
    public String getCategoryName() {
	return new CategoryDAO().getCategoryNameById(Integer.toString(this.category));
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }
    
    public String getBrandName() {
	return new BrandDAO().getBrandById(Integer.toString(this.id));
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public List<Image> getListImg(){
	return (new ImageDAO().getAllImageById(Integer.toString(this.id)));
    }

    @Override
    public String toString() {
	return "Product [id=" + id + ", title=" + title + ", description=" + description + ", rating=" + rating
		+ ", sold=" + sold + ", priceDefault=" + priceDefault + ", category=" + category + ", collection="
		+ collection + ", brand=" + brand + ", discount=" + discount + ", qtt=" + qtt + "]";
    }
    
    
    
}
