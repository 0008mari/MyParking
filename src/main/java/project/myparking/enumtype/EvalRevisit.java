package project.myparking.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvalRevisit {

    YES("또 올래요", 1), NO("재방문x", 2);

    private final String code;
    private final Integer value;

    public static String returnCodeByValue(Integer value) {
        if (value.equals(YES.value)) return YES.code;
        return NO.code;
    }

    public static Integer returnValueByCode(String code) {
        if(code.equals("또 올래요")) return YES.value;
        return NO.value;
    }

    public static EvalRevisit returnEnumByValue(Integer value) {
        if (value.equals(YES.value))
            return YES;
        return NO;
    }

    public static EvalRevisit returnEnumByCode(String code) {
        if(code.equals("또 올래요")) return YES;
        return NO;
    }
}
