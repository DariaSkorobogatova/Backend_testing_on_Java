package Homework_5;

import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest {

    static ProductService productService;
    Product product = null;
    Product productModified = null;
    int id;

    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @Test
    void modifyProductPositiveTest() throws IOException {
        product = new Product()
                .withTitle("Pizza")
                .withCategoryTitle("Food")
                .withPrice(333);
        Response<Product> response = productService.createProduct(product).execute();
        id =  response.body().getId();
        product = new Product()
                .withId(id)
                .withTitle("Pizza Pepperoni")
                .withCategoryTitle("Food")
                .withPrice(331);
        Response<Product> response2 = productService.modifyProduct(product).execute();

        assertThat(response2.isSuccessful(), CoreMatchers.is(true));
        assertThat(response2.code(), equalTo(200));
        assertThat(response2.message(), equalTo(""));


    }
    @AfterEach
    void tearDown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
