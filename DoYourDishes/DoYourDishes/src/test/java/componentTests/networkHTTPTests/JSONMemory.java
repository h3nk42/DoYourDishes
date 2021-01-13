package componentTests.networkHTTPTests;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface JSONMemory {

    /**
     * Create a JSON-File to use as response
     *
     * @throws IOException
     */
    void createJSONFileForLogin() throws IOException;

    /**
     * Get JSON-File
     *
     * @return
     */
    JSONObject restoreJSONFileForLogin() throws FileNotFoundException;

}
