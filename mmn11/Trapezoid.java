/**
 * The Trapezoid class prints calculation of the area & perimeter of Trapezoid,
 * and simply displays to the standard output.
 *                 
 * @author Ron F.
 * @version 1.0
 * @date 2017-11-11
 */
import java.util.Scanner;
public class Trapezoid
{
    public static void main(String [] args)
    { 
        Scanner scan = new Scanner (System.in);
        // Gets values of A + B from user
        System.out.println("Please enter the left point coordinates " + 
            "of the base followed by its length: ");
        int x1 = scan.nextInt();
        int y1 = scan.nextInt();
        int base1 = scan.nextInt();

        // Gets values of C + D from user
        System.out.println("Please enter the left point coordinates " + 
            "of the other base followed by its length: ");
        int x2 = scan.nextInt();
        int y2 = scan.nextInt();
        int base2 = scan.nextInt();

        // area calculation        
        // area = (height * (base1 + base2))/2        
        int height = y1-y2;        
        double area = (height * (base1+base2))/2.0; // 2.0 convert to double

        // length1 A -> D calculation  
        // length1 = √(x1-x2)^2+(y1-y2)^2          
        double length1 = Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2));                               

        // length2 B -> C calculation
        // length2 = √(x1-x2)^2+(y1-y2)^2               
        x1 = x1 + base1;  // reassignment of x1 to point B
        x2 = x2 + base2; // reassignment of x2 to point C
        double length2 = Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2));

        // perimeter calculation        
        double perimeter = base1 + base2 + length1 + length2;

        // output
        System.out.println("------------------------------");
        System.out.println("The area of the trapezoid is " + Math.abs(area));               
        System.out.println("The perimeter of the trapezoid is " + perimeter);
        System.out.println("------------------------------");
    } // End of method main
} // End of class Trapezoid
/*
 * Output of calculate Trapezoid area & perimeter would be
 * Please enter the left point coordinates of the base followed by its length: 
 * 1 1 5
 * Please enter the left point coordinates of the other base followed by its length: 
 * 2 3 2
 * The area of the trapezoid is 7.0
 * The perimeter of the trapezoid is 12.0644
 */
 