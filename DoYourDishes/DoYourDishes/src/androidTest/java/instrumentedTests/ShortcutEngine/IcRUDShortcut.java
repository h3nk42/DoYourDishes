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
     * delete the plan you have (only one plan)
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

    /**
     * OHNE DRUCK AUF DEN HARDWARE BUTTON um in Planansicht zurückzukehren
     * @param taskname
     * @param points
     */
    void addTask1(String taskname, int points);

    void markAsDone(String taskName);

    /**
     * multiple Tasks
     * delete your choosen Task in your Plan
     * @param taskName taskname
     */
    void deleteTask(String taskName);

    /**
     * delete all Tasks in your Plan
     */
    void deleteTask();


    void pressHardwareButtonBack();

}
