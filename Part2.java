import java.util.Scanner;

public class Part2 {
    public int howMany (String stringa, String stringb) {
        int counter = 0;
        int index = stringb.indexOf(stringa);
        if (index == -1) {
            return 0;
        }
        while (index != -1) {
            index = stringb.indexOf(stringa, index + stringa.length());
            counter += 1;
        }
        return counter;
    }
    public void testHowMany () {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Full String: ");
        String text1 = keyboard.nextLine();
        System.out.println("Enter Searched String: ");
        String text2 = keyboard.nextLine();
        System.out.println("Appearences of " + text2 + " in " + text1 + " is " + howMany(text2, text1));
    }
}
