/**
 * Testet User funktionalitäten
 * -createUser
 * -deleteUser
 * -readUserScore
 * -update
 */

package TestsJUnit;

import android.app.Activity;

import com.control.controllerLogic.DebugState;
import com.control.controllerLogic.LandingControllerInterface;

import org.junit.Test;

public class CreatUserTests {

    DebugState state;
    LandingControllerInterface UserCreateMachine;
    Activity mainActivity;

    //TODO: vielleicht persistenz mit file IDEE und Prinzip vorhanden



    /**
     * Ein User wir erstellt
     */
    @Test
    public void createUserGutTest(){

    }

    /**
     * User kann nicht erstellt werden, weill
     */
    @Test
    public void createUserSchlechtTest(){

    }

    @Test
    public void readUserScore(){

    }


}
