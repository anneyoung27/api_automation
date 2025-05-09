package payloads;

import com.github.javafaker.Faker;
import pojo.Cart;
import pojo.Product;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Payload {
    private static final Faker faker = new Faker();
    private static final List<String> product_categories = Arrays.asList("electronics", "jewelry", "men's clothing", "women's clothing");

    // Product
    public static Product productPayload(){
        String name = faker.commerce().productName();
        double price = Double.parseDouble(faker.commerce().price());
        String description = faker.lorem().sentence();
        String imageUrl = "https://i.pravatar.cc/100";
        String category = product_categories.get(getRandomNumber(0, product_categories.size()));

        return new Product(name, price, description, imageUrl, category);
    }

    // Cart
//    public static Cart cartPayload(){
//        String userId;
//        Date date;
//
//
//    }

    public static int getRandomNumber(int min, int max){
        return faker.number().numberBetween(min, max);
    }

}
