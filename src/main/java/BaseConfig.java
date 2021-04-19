public abstract class BaseConfig {
    public static final String CODE_OK = "200";
    public static final String CODE_CREATED = "201";
    public static final String CODE_DELETED = "204";
    public static final String CODE_NOT_FOUND = "404";

    public static final String BASE_URI = PropertyManager.getInstance().getBaseURI();
    public static final String BASE_PATH = PropertyManager.getInstance().getBasePath();
    public static final String BEARER_TOKEN = PropertyManager.getInstance().getBearerToken();
    public static final String ALL_USERS = PropertyManager.getInstance().getAllUsers();
    public static final String SINGLE_USER = PropertyManager.getInstance().getUserId();

    public static final String PATH_PARAMETER = "userId";
}
