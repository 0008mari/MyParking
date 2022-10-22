//package project.myparking.domain;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import project.myparking.enumtype.EvalStaff;
//
//@Getter
//@RequiredArgsConstructor
//public enum Role {
//
//    GUEST("GUEST", 1),
//    USER("USER", 2),
//
//    ADMIN("ADMIN",3);
//
//    private final String code;
//    private final Integer value;
//
//    public static String returnCodeByValue(Integer value) {
//        if (value.equals(GUEST.value)) return GUEST.code;
//        if (value.equals(USER.value)) return USER.code;
//        return ADMIN.code;
//    }
//
//    public static Integer returnValueByCode(String code) {
//        if(code.equals("FRIENDLY")) return GUEST.value;
//        if(code.equals("USER")) return USER.value;
//        return ADMIN.value;
//    }
//
//    public static Role returnEnumByValue(Integer value) {
//        if (value.equals(GUEST.value))
//            return GUEST;
//        if (value.equals(USER.value))
//            return USER;
//        return ADMIN;
//    }
//
//    public static Role returnEnumByCode(String code) {
//        if(code.equals("FRIENDLY")) return GUEST;
//        if(code.equals("USER")) return USER;
//        return ADMIN;
//    }
//
//}