
/**
 * This class represents a Ex14 for Open university.
 * exercise about complexity and recursion.
 *
 * @author Ron F.
 * @version 1.0
 * @date 2018-01-13
 */

public class Ex14
{
    private static int n; // counter of n complexity
    private static int arrLength; // counter of array
    private static int metNum; // method number for print method

    /**
     * Check how many x found in the array
     * 
     * @param a[] array of number
     * @param x the number to serach in the array
     * @return How many x found in the array
     * 
     */

    public static int count (int[] a, int x){
        metNum = 1; // num of method
        arrLength = a.length; // array length
        int counter = binarySearch(a, x); // binary search with counter return the number of x in the array

        // System.out.print(printStr());  // print out complexity
        return counter;
    } // end of count

    /**
     * Check how many switch need to create alternating String of 0 and 1
     * 
     * @param s String of 0 and 1 to check alternating.
     * @return The number of switch need to be alternating String.
     * 
     */

    public static int alternating (String s){
        metNum = 2; // num of method for print method
        arrLength = s.length(); // array length for print method
        // char zero = '0';  
        int counterZero = 0;
        int counterOne = 0;

        for (int i = 0; i <= s.length() -1; i +=2 ){
            n++;
            if (s.charAt(i) != '0'){ // check how many zero need to switch
                counterZero++;
            } else { // check how many one need to switch
                counterOne++;
            }
        }

        // System.out.print(printStr()); // print out complexity

        if (counterZero <= counterOne)
            return counterZero;
        else return counterOne;
    } // end of alternating

    /**
     * Check if there is way to go from index 0 to the last cell in the array.
     * 
     * @param a Array of numbers to check the way through.
     * @return true if found way to go to the last cell in the array.
     * 
     */

    public static boolean isWay (int[] a) {
        // first check
        if (a[0] == a.length-1)
            return true;

        // check if index 0 biger of array length or negative num and return false
        if (a[0] > a.length-1 || a[0] <= 0)
            return false;

        /* array b for exit isWayRecursion:
        if (b [0] = 1 return true; b [1] = 1 return false;) */
        int [] b = new int [1];

        // start to check
        // boolean answer = moveRight(a, b, 0, 0, 0);
        // boolean answer = isWay(a, 0);

        return isWay(a, 0, 0);

        /* if (b[0] == 1) // exit recursion
        return true; 
        else
        return false; */
    } // end of isWay

    private static boolean isWay (int[] a, int i, int loop) {
        // start count loop of recursion
        loop++;

        // limted recursion to never stack on loop more then the length of the array
        if (loop > a.length){ 
            // System.out.println(loop + " trys, out of recursion limit!");
            return false;
        }

        if (i == a.length-1)
            return true;
        if (i < 0 || i > a.length-1)
            return false;

        return (isWay(a, i+a[i], loop) || isWay(a, i-a[i], loop));
    }

    /**
     * Check way from index 0,0 to move to one top.
     * 
     * @param mat Array two dimensional with numbers to check the way to one top. 
     */

    public static void printPath (int[][] mat) {
        System.out.print("(0,0)"); // first print no matter what
        path (mat, 0, 0, 0);
    } // end of printPath

    private static int binarySearch (int [] array, int num){
        int counter = 0;
        int middle;
        int lower = 0;
        int upper = array.length -1;

        if (num > array[upper]){ // check upper exception return 0
            System.out.println("upper exception!");
            return 0;
        }
        if (num < array[lower]){ // check lower exception return 0
            System.out.println("lower exception!");
            return 0;
        }
        if (array.length == 1) // check array with one number
            if (num == array[0])
                return 1;
            else 
                return 0;
        if (num == array[upper] && num != array[upper-1]){ // check upper only return 1
            return 1;
        }
        if (num == array[lower] && num != array[lower+1]){ // check lower only return 1
            return 1;
        }
        if (num == array[lower] && num == array[upper]){ // check array with one num
            System.out.println("all the array number is the same num!");
            return array.length;
        }

        do { // find the num in the low border
            n++;
            middle = (lower+upper)/2;
            if (num == array[middle])
                upper = middle;
            if (num < array[middle])
                upper = middle -1;
            if (num > array[middle]){
                lower = middle +1;
            }
        } while ((array[lower]!=num) && lower<=upper);

        int lowerCheck = lower; // keep the lower num
        int upperCheck = upper; // keep the upper num

        if (lowerCheck > upperCheck && array[lowerCheck]!=num){ // the num don't found in the array
            return 0;
        }
        if (lowerCheck == array.length -1 || array[lowerCheck+1]!=num){// the num found only one time
            return 1;
        }

        upper = array.length -1; // reset the upper
        do { // find the num in the up border
            n++;
            middle = (lower+upper)/2;
            if (num == array[middle])
                lower = middle+1;
            if (num < array[middle])
                upper = middle -1;
            if (num > array[middle])
                lower = middle +1;
        } while ((array[upper]!=num) && upper>=lower);

        upperCheck = upper+1; // upperCheck get the upper num + 1

        if (upperCheck == 0 || lowerCheck == upperCheck){ // the num found only one time
            return 1;
        }

        int sum = upperCheck-lowerCheck; // calc the sum of how many num show up in the array
        return sum;

    } // end of binarySearch

    private static boolean moveRight (int [] a, int [] b, int indCell, int indTemp, int loop) {
        // start count loop of recursion
        loop++;

        // limted recursion to never stack on loop more then the length of the array
        if (loop > a.length){ 
            System.out.println(loop + " trys, out of recursion limit!");
            return false;
        }

        // if indCell == indTemp exit with false (skip: when indCell == 0 )
        if (indCell != 0 && indCell == indTemp){
            return false;
        }

        if (a[indCell] <= 0){ // neg check!
            loop--;
            StepBack(a, b, indCell,indTemp, loop);
            return false;
        }

        /* try right if the next index smaller or equals to array length
         * and the num in the cell is bigger than 0 */
        if ((a[indCell] + indCell) <= a.length-1 && a[indCell] > 0){
            indTemp = indCell;
            indCell = a[indCell] + indCell;

            // check if index move to the last cell
            if (indCell == a.length-1){ 
                b[0] = 1; // chenge array "b" to out from recursion with true
                return true;
            } else {
                moveRight(a, b, indCell,indTemp, loop); // try again move right
                return false;
            } 
        } else {
            tryLeft(a, b, indCell,indTemp, loop);
            return false;
        }
    } // end of moveRight

    private static boolean tryLeft (int [] a, int [] b, int indCell, int indTemp, int loop) {
        if (a[indCell] <= indCell && (indCell - a[indCell] > 0)){
            indTemp = indCell;
            indCell = indCell - a[indCell];
            a[indTemp] = 0;  // del temp cell to never step back

            moveRight(a, b, indCell,indTemp, loop);
            return false;
        } else {
            loop--;
            StepBack(a, b, indCell,indTemp, loop);
            return false;
        }
    } // end of tryLeft

    private static boolean StepBack (int [] a, int [] b, int indCell, int indTemp, int loop) {
        if (indTemp == 0) // if try to step back to index 0 return false
            return false;

        if (indTemp - a[indTemp] != 0)  // step back only if the cell not equal 0
            if(indTemp - a[indTemp] > 0) 
                indCell = indTemp - a[indTemp];
            else 
                return false;

        a[indTemp] = 0; // del temp cell to never step back
        moveRight(a, b, indCell,indTemp, loop);
        return false;
    } // end of StepBack

    private static String path (int[][] mat, int column, int line, int loop){
        // start count loop of recursion
        loop++;

        // limted recursion to never stack on loop more then the length of the array
        if (loop > mat.length * mat.length){ 
            System.out.println(loop + " trys, out of recursion limit!");
            return ""; // important! if found recursion loop it will make it stop
        }

        boolean finshR = false;
        boolean finshD = false;
        boolean finshU = false;
        boolean finshL = false;

        if (line+1 < mat.length){ // try dwon
            if (mat[line][column] < mat[line+1][column]){ // dwon is bigger ?
                line++;
                System.out.print("(" + line + "," + column + ")");
            } else {
                finshD = true;
            } 
        } else {
            finshD = true;
        }

        if (column+1 < mat.length){ // try right
            if (mat[line][column] < mat[line][column+1]){ // right is bigger ?
                column++;
                System.out.print("(" + line + "," + column + ")");
            } else {
                finshR = true;
            }
        } else {
            finshR = true;
        }

        if (line-1 < mat.length && line-1 >= 0){ // try up
            if (mat[line][column] < mat[line-1][column] ){ // up is bigger ?
                line--;
                System.out.print("(" + line + "," + column + ")");
            } else {
                finshU = true;
            }
        } else {
            finshU = true;
        }

        if (column-1 < mat.length && column-1 >= 0){ // try left
            if (mat[line][column] < mat[line][column-1]){ // left is bigger ?
                column--;
                System.out.print("(" + line + "," + column + ")");
            } else {
                finshL = true;
            }
        } else {
            finshL = true;
        }

        if (finshR != true || finshD != true || finshU !=true || finshL !=true)
            path (mat, column, line, loop);

        if (finshR == true && finshD == true && finshU == true && finshL == true)
            return "";
        else 
            return "";
    } // end of path

    private static String printStr (){
        String s1 = "";

        if (n < arrLength/2 && n > 0 && metNum == 1){
            System.out.println("WORST CASE: O(log 2(n)*2) = log 2(" + arrLength + ")*2; \n"
                + "n = " + Ex14.n + " times to find \"num\", from: " + arrLength + " array length;");
        }

        if (n <= arrLength/2 && n > 0 && metNum == 2){
            System.out.println("O(n/2); "
                + "n = " + Ex14.n + " times check string to alternating, from: " + arrLength + " charts.");
        }
        arrLength = 0; // reset 
        n = 0; // reset n counter
        return s1;
    } // end of printStr
} // end of the class
