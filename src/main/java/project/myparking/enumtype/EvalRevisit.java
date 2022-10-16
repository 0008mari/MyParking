package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalRevisit {

    YES("YES", 1), NO("NO", 2);

    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(YES.value)) return YES.code;
        return NO.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("YES")) return YES.value;
        return NO.value;
    }

    public static EvalRevisit returnEnumByValue(Integer value) {
        if (value.equals(YES.value))
            return YES;
        return NO;
    }

    public static EvalRevisit returnEnumByCode(String code) {
        if(code.equals("YES")) return YES;
        return NO;
    }
}
