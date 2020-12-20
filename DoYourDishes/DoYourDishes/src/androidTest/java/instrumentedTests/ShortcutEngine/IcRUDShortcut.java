package instrumentedTests.ShortcutEngine;

public interface IcRUDShortcut {

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
     * delete th plan you have (only one plan)
     */
    void deletePlan();

    /**
     * delete your current useraccount
     */
    void deletUser();

    /**
     * add a task to your Plan
     *
     * @param taskname taskname
     * @param points   points
     */
    void addTask(String taskname, int points);

}
