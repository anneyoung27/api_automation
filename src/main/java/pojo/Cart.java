package pojo;

import java.util.Date;
import java.util.List;

public class Cart {
    private int userId;
    private Date date;
    private List<Product> products;
    private int __v;

    public Cart(int userId, Date date, int __v, List<Product> products) {
        this.userId = userId;
        this.date = date;
        this.__v = __v;
        this.products = products;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
