package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalParkinglevel {

    EASY("쉬워요", 1),
    FINE("적당해요", 2),
    HARD("어려워요", 3);

    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(EASY.value)) return EASY.code;
        if (value.equals(FINE.value)) return FINE.code;
        return HARD.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("쉬워요")) return EASY.value;
        if(code.equals("적당해요")) return FINE.value;
        return HARD.value;
    }

    public static EvalParkinglevel returnEnumByValue(Integer value) {
        if (value.equals(EASY.value))
            return EASY;
        if (value.equals(FINE.value))
            return FINE;
        return HARD;
    }

    public static EvalParkinglevel returnEnumByCode(String code) {
        if(code.equals("쉬워요")) return EASY;
        if(code.equals("적당해요")) return FINE;
        return HARD;
    }
}
