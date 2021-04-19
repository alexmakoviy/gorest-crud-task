import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {
    public String schemaFileName = "UserJsonSchema.json";
    public String newEmail = new Faker().name().username() + "@newmail.com";
    public static String codeKey = "code";

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup(){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BaseConfig.BASE_URI)
                .setBasePath(BaseConfig.BASE_PATH)
                .addHeader("Authorization", "Bearer " + BaseConfig.BEARER_TOKEN)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;

    }
}
