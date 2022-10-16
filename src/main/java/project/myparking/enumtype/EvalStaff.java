package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalStaff {

    FRIENDLY("FRIENDLY", 1),
    MODERATE("MODERATE", 2),
    COLD("COLD", 3);

    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(FRIENDLY.value)) return FRIENDLY.code;
        if (value.equals(MODERATE.value)) return MODERATE.code;
        return COLD.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("FRIENDLY")) return FRIENDLY.value;
        if(code.equals("MODERATE")) return MODERATE.value;
        return COLD.value;
    }

    public static EvalStaff returnEnumByValue(Integer value) {
        if (value.equals(FRIENDLY.value))
            return FRIENDLY;
        if (value.equals(MODERATE.value))
            return MODERATE;
        return COLD;
    }

    public static EvalStaff returnEnumByCode(String code) {
        if(code.equals("FRIENDLY")) return FRIENDLY;
        if(code.equals("MODERATE")) return MODERATE;
        return COLD;
    }
}
