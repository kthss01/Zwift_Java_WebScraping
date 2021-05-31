package string_practice;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    private String str;
    private Map<String, String> userInfo = new LinkedHashMap<>();

    public Map<String, String> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public static void main(String[] args) {
        String str = "test";
        Map<String, String> userInfo = new LinkedHashMap<>();

        Main main = new Main();

        main.setStr(str);
        main.setUserInfo(userInfo);

        str = "test2";
        userInfo.put("email", "test");
        userInfo.put("password", "1234");

//        System.out.println(main.getStr());
        System.out.println(main.getUserInfo());


    }
}
