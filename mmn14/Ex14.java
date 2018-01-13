
/**
 * This class represents a Ex14 for Open university.
 * exercise about binary search, complexity and recursion.
 *
 * @author Ron F.
 * @version 1.0
 * @date 2018-01-13
 */

public class Ex14
{
    private static int n; // counter of time complexity

    /**
     * Check how many times "x" found in array of sort number with ascending order;
     * 
     * Time complexity: log 2(n) = log(n) = O(log n);
     * Space complexity: O(1);
     * 
     * @param a[] array of sort number with ascending order
     * @param x number to serach in the array
     * @return y times "x" found in the array
     * 
     */

    public static int count (int[] a, int x){
        // binary search method, return the number of x in the array
        return binarySearch(a, x);
    } // end of count

    /**
     * Check how many switch need to create alternating String of 0 and 1;
     * 
     * Time complexity: n/2 = O(n);
     * Space complexity: O(1);
     * 
     * @param s String of 0 and 1 to check alternating.
     * @return x switch need to be alternating String.
     * 
     */

    public static int alternating (String s){
        int counterZero = 0;
        int counterOne = 0;

        for (int i = 0; i <= s.length() -1; i +=2 ){
            n++;
            if (s.charAt(i) != '0'){ // check how many zero need to switch to be 010101 ect
                counterZero++;
            } else { // check how many one need to switch to be 101010 ect
                counterOne++;
            }
        }

        if (counterZero <= counterOne) // if zero less or equal return zero
            return counterZero;
        else return counterOne;
    } // end of alternating

    /**
     * Check if there way from index 0 to the last cell in the array.
     * 
     * @param a Array of numbers to check the way through.
     * @return true if found way to last cell in the array.
     * 
     */

    public static boolean isWay (int[] a) {
        // if the number at index 0 equal to array length return true
        if (a[0] == a.length-1)
            return true;

        // if the number at index 0 biger of array length or negative num return false
        if (a[0] > a.length-1 || a[0] <= 0)
            return false;

        /* array b for exit moveRight with answer:
        after method finish is change the array
        (b [0] = 1, return true;) */
        int [] b = new int [1];
        
        // start to check
        boolean answer = moveRight(a, b, 0, 0, 0);
        
        if (b[0] == 1) // if array b 
        return true; 
        else
        return false;
        
        /* 
        another method to check way that take more time complexity (worst case: O(n!)) but don't damage array cells
        boolean answer = isWay(a, 0, 0);
        return answer;
        */
        
    } // end of isWay
    
    // this method take more time complexity from moveRight method but don't damage array cells
    private static boolean isWay (int[] a, int i, int loop) {
        loop++; // counter loop of recursion
        
        // limted recursion to never stack on loop more then the length of the array
        if (loop > a.length){ 
            return false;
        }

        if (i == a.length-1)
            return true;
        if (i < 0 || i > a.length-1)
            return false;

        return (isWay(a, i+a[i], loop) || isWay(a, i-a[i], loop));
    }

    /**
     * Check way from index 0,0 to move to one hill.
     * 
     * @param mat Array two dimensional with numbers to check the way to one hill. 
     */

    public static void printPath (int[][] mat) {
        System.out.print("(0,0)"); // print the current hill
        path (mat, 0, 0);
    } // end of printPath

    // help method for count(): Check how many times "x" found in array of sort number with ascending order.
    private static int binarySearch (int [] array, int num){
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
        if (num == array[lower] && num == array[upper]){ // check array with same numbers
            System.out.println("all the array numbers is the same number!");
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

        return upperCheck-lowerCheck; // calc the sum of how many num show up in the array
    } // end of binarySearch

    // help method for isWay(): Check if there way from index 0 to the last cell in the array.
    private static boolean moveRight (int [] a, int [] b, int indCell, int indTemp, int loop) {
        n++; // counter of time complexity
        loop++; // counter loop of recursion

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
    
    // help method for isWay(): Check if there way from index 0 to the last cell in the array.
    private static boolean tryLeft (int [] a, int [] b, int indCell, int indTemp, int loop) {
        if (a[indCell] <= indCell && (indCell - a[indCell] > 0)){
            indTemp = indCell;
            indCell = indCell - a[indCell];
            a[indTemp] = 0;  // del temp cell to never step back

            moveRight(a, b, indCell,indTemp, loop); // continuation move right
            return false;
        } else {
            loop--;
            StepBack(a, b, indCell,indTemp, loop);
            return false;
        }
    } // end of tryLeft
    
    // help method for isWay(): Check if there way from index 0 to the last cell in the array.
    private static boolean StepBack (int [] a, int [] b, int indCell, int indTemp, int loop) {
        if (indTemp == 0) // if try to step back to index 0 return false
            return false;

        if (indTemp - a[indTemp] != 0)  // step back only if the cell not equal 0
            if(indTemp - a[indTemp] > 0) 
                indCell = indTemp - a[indTemp];
            else 
                return false;

        a[indTemp] = 0; // del temp cell to never step back
        moveRight(a, b, indCell,indTemp, loop); // continuation move right
        return false;
    } // end of StepBack
    
    // help method for path(): Check way from index 0,0 to move to one hill.
    private static void path (int[][] mat, int column, int row){
        boolean finshR = false;
        boolean finshD = false;
        boolean finshU = false;
        boolean finshL = false;

        if (row+1 < mat.length){ // if not out from array column length try dwon
            if (mat[row][column] < mat[row+1][column]){ // dwon is bigger ?
                row++;
                System.out.print("(" + row + "," + column + ")"); // print the hill
            } else {
                finshD = true;
            } 
        } else {
            finshD = true;
        }

        if (column+1 < mat[row].length){ // if not out from array row length try right
            if (mat[row][column] < mat[row][column+1]){ // right is bigger ?
                column++;
                System.out.print("(" + row + "," + column + ")"); // print the hill
            } else {
                finshR = true;
            }
        } else {
            finshR = true;
        }

        if (row-1 < mat.length && row-1 >= 0){ // if not out from array column length try up
            if (mat[row][column] < mat[row-1][column] ){ // up is bigger ?
                row--;
                System.out.print("(" + row + "," + column + ")"); // print the hill
            } else {
                finshU = true;
            }
        } else {
            finshU = true;
        }

        if (column-1 < mat.length && column-1 >= 0){ // if not out from array row length try left
            if (mat[row][column] < mat[row][column-1]){ // left is bigger ?
                column--;
                System.out.print("(" + row + "," + column + ")"); // print the hill
            } else {
                finshL = true;
            }
        } else {
            finshL = true;
        }
        
        // if not finsh all sides check try again 
        if (finshR != true || finshD != true || finshU !=true || finshL !=true)
            path (mat, column, row);
    } // end of path
} // End of the class
