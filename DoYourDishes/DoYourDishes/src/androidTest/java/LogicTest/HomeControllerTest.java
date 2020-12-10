package LogicTest;

import android.widget.TextView;

import com.control.logic.HomeController;
import com.view.gui.HomeActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HomeControllerTest {
    private HomeController hC;
    TextView whoAmItextView;
    String token = "some token";
    HomeActivity homeActivity;


    //methode nennt sich WhoAmI
    /**
     *  HomeController endet mit AsynchWhoAmI request.execute() Thread
     */
    @Test
    public void test1WhoAmI(){
        hC = new HomeController(whoAmItextView, token, homeActivity);
    }




}
