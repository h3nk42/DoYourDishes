package LogicTest;

import android.widget.TextView;

import com.control.networkHttp.HttpRequest;
import com.control.networkHttp.HttpRequestImpl;
import com.view.gui.LoginActivity;

import org.json.JSONObject;
import org.junit.Test;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginControllerTest {

    // ActiveState zustand;
    TextView ausgabeObLoginAccesOrDenied;
    TextView ausgabeObLoginAccesOrDeniedTest;
    TextView userNameAusEingabe;
    TextView userNameAusEingabeTest;

    TextView passwortAusEingabe;
    TextView passwortAusEingabeTest;
    LoginActivity mainActivity;

    /**
     * Prüft ob Login funktioniert
     * Setzt einen Username und Passwort als eingabe des Nutzers, stellt damit eine Verbindung zum Server her
     * ...TODO
     * @throws Exception wird geworfen wenn keine Antwort vom Server
     */
    @Test
    public void LoginTest() {

        // setze den UserName auf henk
        //userNameAusEingabe = (TextView) userNameAusEingabeTest.findViewById(R.id.userNameTextView);
        String userName = "henk";
        userNameAusEingabe.setText(userName);


        // setze das Passwort auf "password"
        //passwortAusEingabe = (TextView) userNameAusEingabe.findViewById(R.id.passwordTextView);
        String password = "password";
        passwortAusEingabe.setText(password);

        // Create http connection and create request on Server
        HttpRequest http = new HttpRequestImpl();

        //Create a RequestBody requestBody for HttpRequest
        RequestBody requestBody;
        requestBody = new FormBody.Builder().add("userName",userName).add("password",password).build();

        //do http request with requestBody
        JSONObject JSONresponse = null;
        try {
            JSONresponse = http.POST("http://10.0.2.2:3001/api/auth/login", requestBody, "");
            //give response as String
            String AusgabeObLoginAccesOrDeniedAlsString = JSONresponse.getString("customMessage");

            //set String as TExtView to use for LoginControllerInterface parameter
            ausgabeObLoginAccesOrDenied.setText(AusgabeObLoginAccesOrDeniedAlsString);

        } catch (Exception e) {
            e.printStackTrace();
        }



        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////                    LoginControllerTest                                         //
        /////////////////////////////////////////////////////////////////////////////////////////

        //LoginControllerInterface logData = new LoginController(ausgabeObLoginAccesOrDenied, userNameAusEingabe, passwortAusEingabe,mainActivity);

        // Assert.assertTrue(zustand==ActiveState.LOGIN);

    }



    // so wie es aussieht umstieg auf espresso oder test kleiner machen, noch kleiner!

}
