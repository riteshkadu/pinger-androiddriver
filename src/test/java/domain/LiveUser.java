package domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Live users used in testing.  These are accounts maintained in Production.
 *
 * @author Ritesh Kadu
 */

public enum LiveUser {

	//Invalid User
    QA_USER_WRONG("QAadmin@pinger.com", "5paxjlvu", "QA Admin", "TestAccount"),
    QA_USER_NO_DATA("", "5paxjlvu", "FirstAdmin", "TestAccount");
    
    //TODO: Add Valid test users

    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;

    private LiveUser(final String username, final String password, final String firstName, final String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Obtains the live user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtains the live user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtains the live user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Obtains the live user's last name
     */
    public String getLastName() {
        return lastName;
    }

    public static LiveUser getUser(final String userName) {
        LiveUser result = null;
        for (LiveUser user : values()) {
            if (StringUtils.equalsIgnoreCase(userName, user.username)) {
                result = user;
            }
        }
        return result;
    }
}