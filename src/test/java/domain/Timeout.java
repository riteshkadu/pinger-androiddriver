package domain;

/**
 * Defines timeouts for WebDriver explicit waits, used to control test flows.
 *
 * @author Ritesh Kadu
 */
public enum Timeout {

	//This class has several implicit wait defined. 
	// However, explicit timeouts are defined in the tests and also HomePage at the constuctor level
	
    PAGE_LOAD_WAIT(60000),
    AJAX_WAIT(10000),
    ;

    private final int timeoutMilliseconds;

    private Timeout(final int timeoutMilliseconds) {
        this.timeoutMilliseconds = timeoutMilliseconds;
    }

    /**
     * Obtain the timeout in milliseconds
     */
    public int getTimeoutMilliseconds() {
        return timeoutMilliseconds;
    }

    /**
     * Obtain the timeout in seconds (used for WebDriverWait)
     */
    public int getTimeoutSeconds() {
        return getTimeoutMilliseconds() / 1000;
    }
}