package domain;

/**
 * Defines the URLs for each of the application, varying by test environment
 *
 * @author Ritesh Kadu
 */
public enum Environment {

    TEST;
    
    public static String getEarnestURL() {
        return "https://jungle-socks.herokuapp.com/";
    }
}
