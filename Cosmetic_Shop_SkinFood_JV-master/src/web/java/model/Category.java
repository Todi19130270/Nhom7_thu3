package web.java.model;

public class Category {
    private int id;
    private String name;
    private int collection;
    
    public Category(int id, String name, int collection) {
	super();
	this.id = id;
	this.name = name;
	this.collection = collection;
    }
    public int getCollection() {
        return collection;
    }
    public void setCollection(int collection) {
        this.collection = collection;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
	return "Category [id=" + id + ", name=" + name + ", collection=" + collection + "]";
    }
    
    
}
