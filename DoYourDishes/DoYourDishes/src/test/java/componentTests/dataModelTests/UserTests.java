package componentTests.dataModelTests;

import com.model.dataModel.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * User Testklasse
 * Standart Tests
 * robuste Implementierung sicherstellen
 * + unten BIG Test
 */

public class UserTests {

    private User user;
    User userOhneParameter;
    public final String USERNAME = "myName";
    public final String PLAN = " Plan";
    public final int POINTSINPLAN = 100;

    @Before
    public void userErzeugen() {
        user = new User(USERNAME, PLAN, POINTSINPLAN);
    }


    @Test
    public void gutTestSetPlan() {
        user.setPlan("plan2");
        Assert.assertEquals("plan2", user.getPlan());
    }

    @Test(expected = NullPointerException.class)
    public void schlechtTestSetPlanWennUserOhneParameter() {
        User nutzerOhnePlan = new User();
        nutzerOhnePlan.setPlan(PLAN);
    }

    @Test(expected = NullPointerException.class)
    public void FehlerFallSetPlan() {
        String leererPlan = null;
        user.setPlan(leererPlan);
    }


    //----------------------------------------------------

    @Test
    public void gutTestsetUserName() {
        user.setUserName("Max Mustermann");
        Assert.assertEquals("Max Mustermann", user.getUserName());
    }

    @Test(expected = NullPointerException.class)
    public void schlechtTestsetUserNameWennKeinUserVorhanden() {
        User nutzerOhnePlan = new User();
        nutzerOhnePlan.setUserName(USERNAME);
    }

    @Test(expected = NullPointerException.class)
    public void fehlerFallsetUsername() {
        String leererUsername = null;
        user.setPlan(leererUsername);
    }

    //----------------------------------------------------

    @Test
    public void gutTestgetPlan() {
        Assert.assertEquals(PLAN, user.getPlan());
    }

    @Test(expected = NullPointerException.class)
    public void schlechtTestgetPlanWennKeinPlanVorhanden() {
        userOhneParameter.getPlan();
    }

    @Test(expected = NullPointerException.class)
    public void fehlerFallTestgetPlan() {
        String leererPlan = null;
        user.setPlan(leererPlan);
        user.getPlan();
    }

    //----------------------------------------------------


    @Test
    public void gutTestgetUserName() {
        Assert.assertEquals(USERNAME, user.getUserName());
    }

    @Test(expected = NullPointerException.class)
    public void schlechtTestgetUserNameWennKeinUserVorhanden() {
        userOhneParameter.getUserName();
    }

    @Test(expected = NullPointerException.class)
    public void grenzTestgetUsername() {
        String leererUser = null;
        user.setUserName(leererUser);
        user.getUserName();
    }

    //----------------------------------------------------

    @Test
    public void gutTestgetPointsInPlan() {
        Assert.assertEquals(POINTSINPLAN, user.getPointsInPlan(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void schlechtTestgetPointsInPlanWennKeinPlanVorhanden() {
        userOhneParameter.getPointsInPlan();
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void grenzTestgetPointsInPlan() {
        int keinePunkte = 0;
        user.setPointsInPlan(keinePunkte);
    }

    //----------------------------------------------------

    @Test
    public void gutTestsetPointsInPlan() {
        Assert.assertEquals(POINTSINPLAN, user.getPointsInPlan(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void schlechtTestsetPointsNegativePoints() {
        user.setPointsInPlan(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void grenzTestsetPointsInPlan() {
        user.setPointsInPlan(0);
    }

    @Test(expected = NullPointerException.class)
    public void fehlerfallSetPointsInPlanMitUserOhneParameter() {
        userOhneParameter.setPointsInPlan(0);
    }

    @Test(expected = NullPointerException.class)
    public void fehlerfallSetPointsInPlanNegativMitUserOhneParameter() {
        userOhneParameter.setPointsInPlan(-100);
    }

    /////////////////////////////////////////////////////////////////////////////////
    ///                             BIG TEST                                    ////
    ///////////////////////////////////////////////////////////////////////////////

    @Test
    public void gutTestBigTest() {

        user.setUserName("Max Mustermann");
        user.setPlan("Mein Plan");

        Integer points = 500;
        user.setPointsInPlan(500);

        Assert.assertEquals("Max Mustermann", user.getUserName());
        Assert.assertEquals("Mein Plan", user.getPlan());
        Assert.assertEquals(points, user.getPointsInPlan());


    }


}
