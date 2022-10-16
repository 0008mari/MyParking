package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalCostefficient {

    CHEAP("CHEAP", 1),
    FAIR("FAIR", 2),
    OVERPRICED("OVERPRICED", 3);


    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(CHEAP.value)) return CHEAP.code;
        if (value.equals(FAIR.value)) return FAIR.code;
        return OVERPRICED.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("CHEAP")) return CHEAP.value;
        if(code.equals("FAIR")) return FAIR.value;
        return OVERPRICED.value;
    }

    public static EvalCostefficient returnEnumByValue(Integer value) {
        if (value.equals(CHEAP.value))
            return CHEAP;
        if (value.equals(FAIR.value))
            return FAIR;
        return OVERPRICED;
    }

    public static EvalCostefficient returnEnumByCode(String code) {
        if(code.equals("CHEAP")) return CHEAP;
        if(code.equals("FAIR")) return FAIR;
        return OVERPRICED;
    }
}
