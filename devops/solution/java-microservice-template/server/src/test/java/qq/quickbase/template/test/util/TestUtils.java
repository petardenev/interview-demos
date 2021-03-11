package qq.quickbase.template.test.util;

public class TestUtils {
    public static String toLocalUrl(int port, String path) {
        return String.format("http://localhost:%d%s", port, path);
    }
}
