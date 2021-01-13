package instrumentedTests.ShortcutEngine;

public interface ICrudShortcut {
    /**
     * Create User with username and password
     *
     * @param username username to input
     * @param password password to input
     */
    void createUser(String username, String password);

    /**
     * Login with credentials
     *
     * @param userName registered username to input
     * @param password registered passwort to input
     */
    void login(String userName, String password);

    /**
     * Create a Plan in your Account
     *
     * @param planName planname to input
     */
    void createPlan(String planName);

    /**
     * delete the plan you have (only one plan)
     */
    void deletePlan();

    /**
     * delete your current useraccount
     */
    void deletUser();

    /**
     * add a task to your Plan + press Hardware Button back
     *
     * @param taskname taskname
     * @param points   points
     */
    void addTask(String taskname, int points);

    /**
     * add a task to your plan
     *
     * @param taskname - taskname to add
     * @param points   -task points worth
     */
    void addTask1(String taskname, int points);

    void markAsDone(String taskName);

    void markAsDone1(String taskName);

    /**
     * choosen Tasks
     * delete your choosen Task in your Plan
     *
     * @param taskName taskname
     */
    void deleteTask(String taskName);

    /**
     * click on hardware Button back
     */
    void pressHardwareButtonBack();

    /**
     * Add a User to your plan
     *
     * @param username - Username to add in your plan
     */
    void addUserToPlan(String username);

    /**
     * add a user to your plan whenin USer Fragment
     *
     * @param username
     */
    void addUserToPlan1(String username);

    /**
     * click on Plan button
     */
    void clickOnPlan();

    /**
     * click on User Fragment in Tab
     */
    void clickOnUsersTab();

    /**
     * remove a User from your Plan
     *
     * @param username - Username to be removed from plan
     */
    void removeUser(String username);

    void clickRefresh();
}
