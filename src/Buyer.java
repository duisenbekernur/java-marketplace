import java.util.Scanner;

public class Buyer {
    static Scanner in = new Scanner(System.in);
    public static void buyProducts(){
        System.out.println("Write id products: ");
        String str = in.next();
        int[] arr = parse(str);

    }
    public static int[] parse(String str) {
        String[] num = str.split(", ");
        int[] arr = null;
        for (int i=0; i<num.length;i++){
            int a = Integer.parseInt(num[i]);
            arr[i] = a;
        }
        return arr;
    }
}