/*
 * The Line class prints calculation of length between the points (A -> B),
 * and simply displays to the standard output.
 *                 
 * @author  Ron F.
 * @version 1.0
 * @date 2017-11-11
 */
import java.util.Scanner;
public class Line
{ 
    public static void main(String [] args)
    {         
        Scanner scan = new Scanner (System.in);
        // Gets values of A + B from user
        System.out.println("Please enter 4 integers."); 
        System.out.println("Please enter x1: ");
        int x1 = scan.nextInt();
        System.out.println("Please enter y1: ");
        int y1 = scan.nextInt();
        System.out.println("Please enter x2: ");
        int x2 = scan.nextInt();
        System.out.println("Please enter y2: ");
        int y2 = scan.nextInt();

        // length calculation        
        // sqrt = √(x1-x2)^2-(y1-y2)^2
        double sqrt = Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2));   

        // output
        System.out.println ("The length of the line between the point (" + 
            x1 + "," + y1 + ") and (" + x2 + "," + y2 + ") is " + sqrt);                                    
    } // End of method main
} // End of class Line
/*
 * Output of calculate length between the points would be
 * Please enter 4 integers.
 * 2 2 6 4
 * The length of the line between the point (2,2) and (6,4) is 4.472
 */
 