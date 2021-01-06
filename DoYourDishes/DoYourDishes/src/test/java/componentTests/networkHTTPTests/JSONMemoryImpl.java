package componentTests.networkHTTPTests;

import android.util.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class JSONMemoryImpl implements JSONMemory{

    private final File file;

    public JSONMemoryImpl(String fileName)throws FileNotFoundException {
        this.file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(this.file);
    }

    public void createJSONFileForLogin() throws IOException {
        try (JsonWriter writer = new JsonWriter(new FileWriter(this.file))){

            writer.beginObject();                                                                   // {
            writer.name("plan").value("null");                                                      // "plan" : "null"
            writer.name("isVerified").value(true);                                                  // "isVerfied" : true
            writer.name("_id").value("5fd7ad827fc7c60017f1707b");                                   // "_id" : "5fd7ad827fc7c60017f1707b"
            writer.name("userName").value("harun");                                                 // "userName" : "harun"
            writer.name("userNameLowerCase").value("harun");                                        // "userNameLowerCase" : "harun"
            writer.name("createdAt").value("2020-12-14T18:22:58.768Z");                             // "createdAt" : "2020-12-14T18:22:58.768Z"
            writer.name("updatedAt").value("2020-12-20T17:33:06.303Z");                             // "updatedAt" : "2020-12-20T17:33:06.303Z"
            writer.name("__v").value(0);                                                            // "__v" : 0
            writer.name("token").value("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImhhcnVuIiwiaWF0IjoxNjA5NzAwOTQ1LCJleHAiOjE2MDk3ODczNDV9.xxm7fhsKcKQUERAN8oHTULf8gQsoL7jjGQG9NwOMW5o"); // "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImhhcnVuIiwiaWF0IjoxNjA5NzAwOTQ1LCJleHAiOjE2MDk3ODczNDV9.xxm7fhsKcKQUERAN8oHTULf8gQsoL7jjGQG9NwOMW5o"
            writer.endObject();                                                                     // }

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
