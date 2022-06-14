package Homework_5;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductTest {

    static ProductService productService;
    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
    @Test
    void getProductByIdPositiveTest() throws IOException {
        Response<Product> response = productService.getProductById(3).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assertThat(response.message(), equalTo(""));
        assertThat(response.body().getId(), equalTo(3));
        assertThat(response.body().getTitle(), equalTo("Cheese"));
        assertThat(response.body().getPrice(), equalTo(360));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
    }
}
