/**
 * Testet User funktionalitäten
 * -createUser
 * -deleteUser
 * -readUserScore
 * -update
 */

package TestsJUnit;

import android.app.Activity;

import com.control.logic.DebugState;
import com.control.logic.LandingController;
import com.control.logic.LandingControllerInterface;
import com.control.logic.UserControllerInterface;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
