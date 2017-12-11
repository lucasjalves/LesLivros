package finalCore.util;

import java.util.UUID;

public class GeradorCodigoCupom {
	
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.substring(0, 6);
        return uuid.toUpperCase();
    }
}
