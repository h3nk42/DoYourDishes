package componentTests.networkHTTPTests;

import java.io.IOException;

public interface Memory {

    void save(String einString) throws IOException;

    String restore() throws IOException;

}
