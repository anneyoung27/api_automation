package payloads;

import com.github.javafaker.Faker;
import pojo.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Payload {
    private static final Faker faker = new Faker();
    private static final List<String> product_categories = Arrays.asList("electronics", "jewelry", "men's clothing", "women's clothing");

    // Product
    public static Product productPayload() {
        String name = faker.commerce().productName();
        double price = Double.parseDouble(faker.commerce().price());
        String description = faker.lorem().sentence();
        String imageUrl = "https://i.pravatar.cc/100";
        String category = product_categories.get(getRandomNumber(0, product_categories.size()));

        return new Product(name, price, description, imageUrl, category);
    }

    // User
    public static User userPayload() {
        double latitude = Double.parseDouble(faker.address().latitude());
        double longitude = Double.parseDouble(faker.address().longitude());
        String city = faker.address().city();
        String street = faker.address().streetName();
        int number = Integer.parseInt(faker.address().buildingNumber());
        String zipCode = faker.address().zipCode();
        String email = faker.internet().emailAddress();
        String userName = faker.name().username();
        String password = faker.internet().password(1, 7);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().phoneNumber();
        int __v = faker.number().numberBetween(1, 10);

        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setLat(latitude);
        geoLocation.setL_ong(longitude);

        Address address = new Address();
        address.setGeoLocation(geoLocation);
        address.setCity(city);
        address.setStreet(street);
        address.setNumber(number);
        address.setZipCode(zipCode);

        Name name = new Name();
        name.setFirstName(firstName);
        name.setLastName(lastName);

        User user = new User();
        user.setAddress(address);
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.set__v(__v);

        return new User(address, user.getEmail(), user.getUserName(), user.getPassword(), name, user.getPhone(), user.get__v());
    }

    public static Cart cartPayload() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startDate = sdf.parse("2025-01-01T00:00:00.000Z");
        Date endDate = sdf.parse("2025-12-31T00:00:00.000Z");

        int userId = faker.number().numberBetween(1, 20);
        Date date = faker.date().between(startDate, endDate);
        int productId = faker.number().numberBetween(1, 20);
        int quantity = faker.number().numberBetween(1, 50);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(productId);
        productDetail.setQuantity(quantity);

        return new Cart(userId, date, productDetail.getProductId(), productDetail.getQuantity());


    }


    // Login
    public static Login loginPayload(String username, String password) {
        return new Login(username, password);
    }

    public static int getRandomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

}
