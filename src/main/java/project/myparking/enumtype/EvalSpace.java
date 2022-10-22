package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalSpace {
    SMALL("좁아요", 1),
    MIDDLE("적당해요", 2),
    LARGE("넓어요", 3);

    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(SMALL.value)) return SMALL.code;
        if (value.equals(MIDDLE.value)) return MIDDLE.code;
        return LARGE.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("좁아요")) return SMALL.value;
        if(code.equals("적당해요")) return MIDDLE.value;
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
        if(code.equals("좁아요")) return SMALL;
        if(code.equals("적당해요")) return MIDDLE;
        return LARGE;
    }
}
