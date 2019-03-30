
import java.util.Scanner;

public class Test {

  public static void main(String[] args) {

      Board b = new Board(7,7);

      Scanner sc = new Scanner(System.in);
      boolean one = true;
      boolean two = true;
      boolean first = false;
      int num;

      while(one) {
        System.out.println("Who plays first?");
        System.out.println("1. Human 2. AI");
        try {
          num = Integer.parseInt(sc.nextLine());

          if(num == 1 || num == 2) {
            if(num == 1) {
              first = true;
            }
            one = false;

          } else {
            System.out.println("Choose the number shown.");
          }
        } catch(Exception e) {
          System.out.println("Improper entry, try again.");
        }
      }

      while(two) {

        if(first) {
          // Human goes first
          System.out.println("Choose a row");
          num = Integer.parseInt(sc.nextLine());
          two = !b.add(num,'P');

          // AI has a turn

          if(two){
            System.out.println("Choose a row (Computer)");
            num = Integer.parseInt(sc.nextLine());
            two = !b.add(num,'C');
          }

        } else {
          // AI has a turn

          // Human goes

        }

        b.printBoard();

      }


  }







}
