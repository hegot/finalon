package finalon.globalReusables;

import java.util.UUID;

public class RandomString {
    public static String get() {
        UUID uid = new UUID(3, 6);
        String out = uid.randomUUID().toString();
        return out;
    }
}
