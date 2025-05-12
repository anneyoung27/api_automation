package pojo;

import java.util.Date;
import java.util.List;

public class Cart {
    private int userId;
    private Date date;
    private List<ProductDetail> productDetail;
    private int __v;

    public Cart(int userId, Date date, List<ProductDetail> productDetail, int __v) {
        this.userId = userId;
        this.date = date;
        this.__v = __v;
        this.productDetail = productDetail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public List<ProductDetail> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetail> productDetail) {
        this.productDetail = productDetail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
