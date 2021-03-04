import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        stringTo2CharString("Hello");

        stringTo2CharString("Hello there");
        stringTo2CharString("There");

        String result = stringTo2CharString2("Hello");
        System.out.println(result);
        result = stringTo2CharString2("Hello there");
        System.out.println(result);
        result = stringTo2CharString2("There");
        System.out.println(result);
    }

    public static void stringTo2CharString(String string) {
        char[] stringArray = string.toCharArray();
        for (int i = 0; i < stringArray.length; i++) {
            System.out.print(stringArray[i]);
            System.out.print(stringArray[i]);
        }
    }

    public static String stringTo2CharString2(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

        }
        return newString;
    }
}
