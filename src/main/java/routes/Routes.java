package routes;

public class Routes {
    public static final String BASE_URL = "https://fakestoreapi.com";

    // Product
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String GET_PRODUCT_WITH_LIMIT = "/products?limit={limit}";
    public static final String GET_PRODUCTS_SORTED = "/products?sort={order}";
    public static final String GET_PRODUCTS_LIMIT_AND_SORTED = "/products?limit={limit}&sort={order}";
    public static final String GET_ALL_CATEGORIES = "/products/categories";
    public static final String GET_PRODUCTS_BY_CATEGORY = "/products/category/{category}";
    public static final String CREATE_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT = "/products/{id}";
    public static final String DELETE_PRODUCT = "/products/{id}";

    //  Users
    public static final String LOGIN = "/auth/login";
    public static final String GET_SINGLE_USER = "/users/{id}";
    public static final String GET_ALL_USER = "/users";
    public static final String CREATE_USER = "/users";
    public static final String UPDATE_USER = "/users/{id}";
    public static final String DELETE_USER = "/users/{id}";

    // Cart

}
