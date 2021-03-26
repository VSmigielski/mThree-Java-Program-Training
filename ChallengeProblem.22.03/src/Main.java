public class Main {
    public static void main(String[] args) {
        int i = 10 + + 11 - - 12 + + 13 - - 14 + + 15;
        System.out.println(i);

        Integer i1 = 127;
        Integer i2 = 127;

        System.out.println(i1 == i2);

        Integer i3 = 128;
        Integer i4 = 128;

        System.out.println(i3 == i4);
    }
}
