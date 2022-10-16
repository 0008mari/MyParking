package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalSpace {
    SMALL("SMALL", 1),
    MIDDLE("MIDDLE", 2),
    LARGE("LARGE", 3);

    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(SMALL.value)) return SMALL.code;
        if (value.equals(MIDDLE.value)) return MIDDLE.code;
        return LARGE.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("SMALL")) return SMALL.value;
        if(code.equals("MIDDLE")) return MIDDLE.value;
        return LARGE.value;
    }

    public static EvalSpace returnEnumByValue(Integer value) {
        if (value.equals(SMALL.value))
            return SMALL;
        if (value.equals(MIDDLE.value))
            return MIDDLE;
        return LARGE;
    }

    public static EvalSpace returnEnumByCode(String code) {
        if(code.equals("SMALL")) return SMALL;
        if(code.equals("MIDDLE")) return MIDDLE;
        return LARGE;
    }
}
