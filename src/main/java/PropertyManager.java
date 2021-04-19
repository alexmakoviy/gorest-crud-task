import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static String propertyConfigurationFilePath = "config" + File.separator + "configuration.properties";
    private static String propertyCredentialsFilePath = "config" + File.separator + "credentials.properties";

    Properties prop = new Properties();

    //Create a Singleton instance. We need only one instance of Property Manager.
    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }

    //Get all configuration data and assign to related fields.
    private void loadData() {
        try {
            prop.load(new FileInputStream(propertyConfigurationFilePath));
            prop.load(new FileInputStream(propertyCredentialsFilePath));
        } catch (IOException e) {
            System.out.println("Properties file cannot be found");
        }

    }

    public String getBaseURI() { return prop.getProperty("baseUri"); }
    public String getBasePath() { return prop.getProperty("basePath"); }
    public String getBearerToken() { return prop.getProperty("bearerToken"); }
    public String getAllUsers() { return prop.getProperty("allUsers"); }
    public String getUserId() { return prop.getProperty("singleUser"); }
}

