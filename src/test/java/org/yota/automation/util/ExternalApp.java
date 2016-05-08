package org.yota.automation.util;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Class that manages external apps
 */
public class ExternalApp {

    private Runtime r;
    private static Process p;
    private boolean status;
    private static String path = "src/test/resources/test-slider-1.0.0-SNAPSHOT.jar";

    public ExternalApp(){
        r = Runtime.getRuntime();
        status = false;
        try {
            path = Paths.get(path).toFile().getCanonicalPath();
        }
        catch (IOException e) {}
    }

    public void startApp(){
        try {
            p = r.exec("java -jar " + path);
            status = true;
        }
        catch (IOException e) {}
    }

    public void stopApp(){
        if (p != null) {
            p.destroyForcibly();
            status = false;
        }
    }

    public boolean getStatus(){
        return status;
    }


}
