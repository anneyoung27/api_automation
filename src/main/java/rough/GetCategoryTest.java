package rough;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GetCategoryTest {
    public static void main(String[] args) {
        String category = getCategory();
        System.out.println("Get category: " + category);
    }
    static String getCategory(){
        Random rdm = new Random();

        List<String> product_categories = Arrays.asList("electronics", "jewelery", "men's clothing", "women's clothing");

        int index = rdm.nextInt(1, product_categories.size());
        return product_categories.get(index);
    }
}
