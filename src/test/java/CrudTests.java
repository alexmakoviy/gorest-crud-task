import io.restassured.response.Response;
import org.junit.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class CrudTests extends BaseTest {
    public static String userIdBase;
    public static UserRequestModel baseUser;

    @BeforeClass
    public static void prepare(){
        baseUser = new UserRequestModel();
        userIdBase = given().
                             body(baseUser).
                     when().
                             post(BaseConfig.ALL_USERS).
                             jsonPath().getString("data.id");
    }

    @Test
    public void getAllUsers(){
        given().
        when().
                get(BaseConfig.ALL_USERS).
        then().
                body(codeKey, equalTo(Integer.valueOf(BaseConfig.CODE_OK)));
    }

    @Test
    public void getSingleUser(){
        Response response = given().
                                   pathParam(BaseConfig.PATH_PARAMETER, userIdBase).
                            when().
                                   get(BaseConfig.SINGLE_USER).
                            then().
                                  body(matchesJsonSchemaInClasspath(schemaFileName)).
                                  extract().response();

        ResponseModel responseModel = response.as(ResponseModel.class);

        assertTrue(responseModel.getCode().equals(BaseConfig.CODE_OK));
        assertTrue(responseModel.getUserResponse().getEmail().equals(baseUser.getEmail()));
        assertTrue(responseModel.getUserResponse().getName().equals(baseUser.getName()));
        assertTrue(responseModel.getUserResponse().getGender().equals(baseUser.getGender()));
        assertTrue(responseModel.getUserResponse().getStatus().equals(baseUser.getStatus()));
    }

    @Test
    public void createUser(){
        UserRequestModel requestUser = new UserRequestModel();

        Response response = given().
                                    body(requestUser).
                            when().
                                    post(BaseConfig.ALL_USERS).
                            then().
                                    body(matchesJsonSchemaInClasspath(schemaFileName)).
                                    extract().response();

        ResponseModel responseModel = response.as(ResponseModel.class);

        assertTrue(responseModel.getCode().equals(BaseConfig.CODE_CREATED));
        assertTrue(responseModel.getUserResponse().getEmail().equals(requestUser.getEmail()));
        assertTrue(responseModel.getUserResponse().getName().equals(requestUser.getName()));
        assertTrue(responseModel.getUserResponse().getGender().equals(requestUser.getGender()));
        assertTrue(responseModel.getUserResponse().getStatus().equals(requestUser.getStatus()));

    }

    @Test
    public void updateUser(){
        baseUser.setEmail(newEmail);

        Response response = given().
                                    body(baseUser).
                                    pathParam(BaseConfig.PATH_PARAMETER, userIdBase).
                            when().
                                    patch(BaseConfig.SINGLE_USER).
                            then().
                                    body(matchesJsonSchemaInClasspath(schemaFileName)).
                                    extract().response();

        ResponseModel responseModel = response.as(ResponseModel.class);

        assertTrue(responseModel.getCode().equals(BaseConfig.CODE_OK));
        assertTrue(responseModel.getUserResponse().getEmail().equals(baseUser.getEmail()));

    }


    @Test
    public void deleteUser(){
        Response response = given().
                                    pathParam(BaseConfig.PATH_PARAMETER, userIdBase).
                            when().
                                    delete(BaseConfig.SINGLE_USER).
                            then().
                                    extract().response();

        ResponseModel responseModel = response.as(ResponseModel.class);

        assertTrue(responseModel.getCode().equals(BaseConfig.CODE_DELETED));

                    given().
                           pathParam(BaseConfig.PATH_PARAMETER, userIdBase).
                    when().
                           delete(BaseConfig.SINGLE_USER).
                    then().
                           body(codeKey,equalTo(Integer.valueOf(BaseConfig.CODE_NOT_FOUND)));
    }

}
