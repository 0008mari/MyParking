package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalParkinglevel {

    EASY("EASY", 1),
    FINE("FINE", 2),
    HARD("HARD", 3);

    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(EASY.value)) return EASY.code;
        if (value.equals(FINE.value)) return FINE.code;
        return HARD.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("EASY")) return EASY.value;
        if(code.equals("FINE")) return FINE.value;
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
        if(code.equals("EASY")) return EASY;
        if(code.equals("FINE")) return FINE;
        return HARD;
    }
}
