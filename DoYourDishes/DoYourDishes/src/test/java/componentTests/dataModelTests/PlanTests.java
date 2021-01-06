package componentTests.dataModelTests;

import com.model.dataModel.Plan;
import com.model.dataModel.Task;
import com.model.dataModel.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.Stack;

public class PlanTests {

    Plan planOfOwner;
    Plan planToJoin;

    public final String PLANOWNER = "PlanAdmin";
    public final String USER1 = "TheOneWhoWantsToJoinPlan";
    public final String USER2 = "AnotherUser";
    public final String PLANID = "123456789";

    public final String USERNAME = "myName";
    public final String PLAN = " Plan";
    public final int POINTSINPLAN = 100;



    BigInteger points = BigInteger.valueOf(500);


    User harun = new User(USERNAME,PLAN,POINTSINPLAN);
    User henk = new User("Henk","Plan2",100);
    Task aufraeumen = new Task("TaskName","PlanName",50,points, "456789");

    @Before
    public void erzeugePlan(){

        List<User> usersList = new Stack<>();
        List<Task> taskList = new Stack<>();

        //TODO überlegen welche TESTteile Redundant!

        usersList.add(harun);
        usersList.add(henk);
        taskList.add(aufraeumen);


        planOfOwner = new Plan(PLANOWNER,PLANOWNER,PLANID,usersList,taskList);
        planToJoin = new Plan(PLANOWNER,USER1,PLANID,usersList,taskList);
    }


    @Test
    public void gutTestsetNameOfPlan1(){
        planOfOwner.setName("Plan101");
        Assert.assertEquals("Plan101",planOfOwner.getName());
    }

    @Test
    public void gutTestsetNameOfPlan2(){
        planToJoin.setName("Plan10101");
        Assert.assertEquals("Plan10101",planToJoin.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void schlechtTestsetName(){
        String leererPlanName = "";
        planOfOwner.setName(leererPlanName);
    }

    @Test(expected = NullPointerException.class)
    public void fehlerFallTestsetName(){
        String leererPlanName = null;
        planOfOwner.setName(leererPlanName);
    }

    //-------------------------------------

    @Test
    public void gutTestsetTask(){

        List<Task> taskListNew = new Stack<>();
        Task aufraeumen = new Task("Aufräumen","PlanName",50,points, "456789");
        Task staubsaugen = new Task("Staubsaugen","PlanName",100,points, "123456");
        Task wischen = new Task("Wischen","PlanName",75,points, "789123");

        taskListNew.add(aufraeumen);
        taskListNew.add(staubsaugen);
        taskListNew.add(wischen);

        planOfOwner.setTasks(taskListNew);

        Assert.assertEquals(3,planOfOwner.getTaskListSize());

    }

    @Test
    public void schlechtTestsetTask(){

        List<Task> taskListNew = new Stack<>();
        taskListNew.clear();
        planOfOwner.setTasks(taskListNew);

        Assert.assertEquals(0,planOfOwner.getTaskListSize());
    }

    /**
     * Erstelle Tasks, Taskliste, füge Tasks der Liste hinzu
     * + Erstelle User, erstelle Userliste, füge User der Liste hinzu
     * + Erstelle Plan mit TaskListe und UserListe
     * --> lösche die TaskListe und versuche eine leere Liste an Tasks zu erstellen
     */
    @Test
    public void schlechTestsetTask2(){
        List<Task> taskListNew = new Stack<>();
        Task aufraeumen = new Task("Aufräumen","PlanName",50,points, "456789");
        Task staubsaugen = new Task("Staubsaugen","PlanName",100,points, "123456");
        Task wischen = new Task("Wischen","PlanName",75,points, "789123");

        taskListNew.add(aufraeumen);
        taskListNew.add(staubsaugen);
        taskListNew.add(wischen);

        List<User> userListNew = new Stack<>();
        User harun = new User(USERNAME,PLAN,POINTSINPLAN);
        userListNew.add(harun);


        Plan planTofail = new Plan(PLANOWNER,PLANOWNER,PLANID,userListNew,taskListNew);

        taskListNew.clear();

        planTofail.setTasks(taskListNew);

        Assert.assertTrue(taskListNew.isEmpty());
        Assert.assertEquals(0,planTofail.getTaskListSize());

    }

    //-----------------------------------------

    @Test
    public void gutTestsetUsers1(){

        List<User> userListNew = new Stack<>();
        User testUser = new User(USERNAME,PLAN,POINTSINPLAN);
        userListNew.add(testUser);

        planOfOwner.setUsers(userListNew);

        Assert.assertEquals(1,planOfOwner.getUserListSize());

    }

    @Test
    public void gutTestsetUsers2(){
        List<User> userListNew = new Stack<>();
        User testUser = new User(USERNAME,PLAN,POINTSINPLAN);
        userListNew.add(testUser);
        userListNew.add(harun);
        userListNew.add(henk);

        planOfOwner.setUsers(userListNew);

        Assert.assertEquals(3,planOfOwner.getUserListSize());
    }

    @Test
    public void schlechtTestsetUsers1(){
        List<User> userListNew = new Stack<>();
        userListNew.add(harun);
        userListNew.remove(harun);
        planOfOwner.setUsers(userListNew);
        Assert.assertTrue(userListNew.isEmpty());
    }

    @Test
    public void schlechtTestsetUsers2(){
        List<User> userListNew = new Stack<>();
        userListNew.add(harun);
        userListNew.add(henk);
        userListNew.add(new User("Nadine","plan3",10));
        planOfOwner.setUsers(userListNew);
        Assert.assertEquals(3,planOfOwner.getUserListSize());
    }

    //-----------------------------------------

    @Test
    public void gutTestgetName(){
        Assert.assertEquals(USER1,planToJoin.getName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void fehlerFallTestgetName(){
        planToJoin.setName("");
    }

    //-----------------------------------------

    @Test
    public void gutTestgetOwner(){
        Assert.assertEquals(PLANOWNER,planToJoin.getOwner());
    }

    //------------------------------------------

    @Test
    public void gutTestgetTasks(){
        List<Task> taskListNew = new Stack<>();

        Task aufraeumen = new Task("Aufräumen","PlanName",50,points, "456789");
        Task staubsaugen = new Task("Staubsaugen","PlanName",100,points, "123456");
        Task wischen = new Task("Wischen","PlanName",75,points, "789123");

        taskListNew.add(aufraeumen);
        taskListNew.add(staubsaugen);
        taskListNew.add(wischen);

        planToJoin.setTasks(taskListNew);

        Assert.assertEquals(taskListNew,planToJoin.getTasks());

    }

    //----------------------------------------------

    @Test
    public void gutTestgetUsers(){

        List<User> userListNew = new Stack<>();

        userListNew.add(harun);
        userListNew.add(henk);
        userListNew.add(new User("Nadine","plan3",10));
        planToJoin.setUsers(userListNew);

        Assert.assertEquals(userListNew,planToJoin.getUsers());

    }


    /////////////////////////////////////////////////////////////////////////////////
    ///                             BIG TEST                                    ////
    ///////////////////////////////////////////////////////////////////////////////


    @Test
    public void gutTestBigTest(){

       List<User> nutzerListe = new Stack<>();
       List<Task> aufgabenListe = new Stack<>();

       nutzerListe.add(harun);
       nutzerListe.add(henk);

       aufgabenListe.add(aufraeumen);

       Plan haushaltsPlan = new Plan(PLANOWNER,USERNAME,PLANID,nutzerListe,aufgabenListe);

       Assert.assertEquals(2,haushaltsPlan.getUserListSize());

       nutzerListe.add(new User("Nadine","TestPlan",500));
       haushaltsPlan.setUsers(nutzerListe);

       aufgabenListe.add(new Task("staubsaugen","TestPlan",50,points, "456789"));

       Assert.assertEquals(3,haushaltsPlan.getUserListSize() );
       Assert.assertEquals(nutzerListe,haushaltsPlan.getUsers());
       Assert.assertEquals(aufgabenListe,haushaltsPlan.getTasks());
       Assert.assertEquals(2,aufgabenListe.size());
    }

}
