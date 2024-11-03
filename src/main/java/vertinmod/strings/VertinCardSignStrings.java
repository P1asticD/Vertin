package vertinmod.strings;

import org.jetbrains.annotations.Nullable;
import java.util.Map;

public class VertinCardSignStrings {
    public static String DEFAULT_TITLE;

    private static Map<String, VertinCardSignStrings> strings = null;

    public String SIGN;

    public String[] EXTENDED_DESCRIPTION = null;

    public static void init(Map<String, VertinCardSignStrings> strings) {
        VertinCardSignStrings.strings = strings;
    }

    @Nullable
    public static VertinCardSignStrings get(String id) {
        if (!strings.containsKey(id))
            return null;
        return strings.get(id);
    }
}
