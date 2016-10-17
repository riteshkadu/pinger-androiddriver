Basic Pinger Automation Project

PingerAndroidDriverTest.java under package 'base' is a class that handles the basic AndroidDriver lifecycle for a test. This class has a declaration of @BeforeClass which has details about the grid which runs before every test.

1. Package 'domain'
LiveUser.java is a class which keeps record of all the standard user values.
Timeout.java is a class which keeps implicit timeout values.

2. Package 'page.pinger'
a. HomePage.java - Is a base class which holds all the common elements across the projects
b. PingerTemplatePage.java - Is a class which is primarily designed to hold navigations links and modules common elements
c. PingerLogInPage.java - Is a page class which represents the log in page and has elements and its respective methods: At the moment there are two main methods for valid and invalid credentials. 
d. PingerSignUpPage.java - Is a page class which represents the sign up page (which is incomplete at the moment)

3. Package basic_test
a. PingerLogInTest is test class which at the moment has two working tests and other test cases needs to be developed.  
@Before hold steps which runs every time before running a test
All the @Test can be run independently, they include logger info, steps and verification steps

This projects is maven based and configuration is specified at prom.xml 
