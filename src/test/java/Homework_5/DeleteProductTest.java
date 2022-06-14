package Homework_5;

import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductTest {

    static ProductService productService;
    Product product = null;
    int id;

    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle("Marmalade sandwich")
                .withCategoryTitle("Food")
                .withPrice(70);
    }
    @Test
    void deleteProductByIdPositiveTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        Response<ResponseBody> response2 = productService.deleteProduct(id).execute();

        assertThat(response2.isSuccessful(), CoreMatchers.is(true));
        assertThat(response2.code(), equalTo(200));
        assertThat(response2.message(), equalTo(""));
    }
}
