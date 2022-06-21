import java.io.File;
import java.net.URISyntaxException;

public class Util {
    public static String getJarLocation() throws URISyntaxException {
     return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
    }
}
