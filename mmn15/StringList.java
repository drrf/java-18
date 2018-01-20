
/**
 * This class represents linked list of CharNode.
 * A CharNode object is represented by char data,
 * the numbers of times of char value appears continuous,
 * and the next CharNode on the linked list.
 *
 * @author Ron F.
 * @version 1.0
 * @date 2018-01-20
 */
public class StringList
{
    // all the attributes
    private CharNode _head;

    /**
     * Empty constructs of StringList object.
     * 
     * Time complexity: O(1);
     * Space complexity: O(1);
     */
    public StringList () {
        // _head = null;
        _head = new CharNode('-',-1,null); // dummy head aviod NullPointerException
    } // end of constructs StringList()

    /**
     * Copy constructs for StringList.
     * Constructs a list with the same list as another list.
     * 
     * Time complexity: O(n);
     * Space complexity: O(1);
     * 
     * @param other The StringList object from which to construct the new list.
     */
    public StringList(StringList other) {
        other = new StringList (other._head); // send to copy CharNode to avoid aliasing
        this._head = other._head; 
    } // end of copy constructs StringList (StringList other)

    /**
     * Copy constructs a StringList object from CharNode.
     * if node == null, the new StringList be null,
     * else create new CharNode, and run with for loop (untill the next != null),
     * to copy all the linked list to new StringList instance.
     * 
     * Time complexity: O(n);
     * Space complexity: O(1);
     * 
     * @param node The CharNode to copy the new StringList.
     */
    public StringList (CharNode node) {
        if (node == null)
            _head = null;
        else {
            _head = new CharNode (node.getData(),node.getValue(),null);
            for (CharNode ptr = node.getNext(),last=_head; ptr!=null; ptr = ptr.getNext())                
            {
                last.setNext(new CharNode(ptr.getData(),ptr.getValue(),ptr.getNext())); 
                last = last.getNext();
            }
        }
    } // end of copy constructs StringList (CharNode node)

    /**
     * Constructor for a StringList object.
     * If the String length is 0 (empty String) enter only the dummy head.
     * For loop from the last data to the first data.
     * 
     * Time complexity: O(n);
     * Space complexity: O(1);
     * 
     * @param s a String object to create a linked list.
     */
    public StringList(String s) {
        CharNode temp = new CharNode('-',-1,null); // dummy head
        int l = s.length(); // represents the length of the String
        if (l == 0){ // check if the String is empty
            System.out.println(empty()); // empty String err
        } else {
            char d = s.charAt(l - 1); // get the last data
            int v = 0; // value counter
            for (int i = l - 1; i >= 0; i--){
                if (d == s.charAt(i)){
                    v++; // counter value
                } else {
                    temp.setNext(new CharNode(d,v,temp.getNext())); // set new CharNode
                    d = s.charAt(i); // switch to current data
                    v = 1; // reset value counter
                }   
            } 
            temp.setNext(new CharNode(d,v,temp.getNext())); // last char adding
        }
        this._head = temp; // link to this linked list
    } // end of StringList(String s)

    private String empty () {
        return "Found a empty String, some methods may not works as expected!";
    }

    /**
     * Returns the char that appear on "i" number in the String.
     * Example: s = "abcae", s.charAt(1) = b.
     * 
     * Time complexity: O(n); // worst case: if i == is the last!
     * Space complexity: O(1);
     * 
     * @param i The number of char appear in the string to returns.
     * @return a char that appear in the string on the number of i.
     */
    public char charAt(int i) {
        CharNode node = _head.getNext(); // skip on dummy head
        if (node != null){
            int v = 0; // counter of vaule
            char answer; // temp char
            while (i > 0 && node.getNext() != null){ // run untill i = 0 or node == null
                i--;
                v++; // vaule counter
                if (v >= node.getValue()){ // check the value for move the next node
                    node = node.getNext(); // go the the next node
                    v = 0;// reset vaule counter
                }
            }
            return answer = node.getData();
        } else {
            System.out.println(empty()); // empty String err
            return ' ';
        }
    } // end of charAt

    /**
     * Returns a new StringList of combined of 2 StringList obejct.
     * Example: s1 = "aaa", s2 = "bbb", s1.concat(s2) = "aaabbb".
     * 
     * 
     * Time complexity: O(n+m); n = the first StringList to copy, m = the second StringList to adding.
     * Space complexity: O(1);
     * 
     * @param str The new String list to combined to the last of the another.
     * @return new StringList of combined of 2 StringList.
     */
    public StringList concat (StringList str) {
        StringList s = new StringList (_head); // copy the first StringList to avoid aliasing
        CharNode temp = new CharNode (s._head.getData(),s._head.getValue(), s._head.getNext()); // save the head!
        int l = str.length();
        if (l != 0){
            while (s._head.getNext() !=null){ // go to the last in the first StringList
                s._head = s._head.getNext();
            }
            char data = str.charAt(l - 1);// get the last data from str
            int v = 0; // value counter
            for (int i = l - 1; i >= 0; i--){
                if (data == str.charAt(i)){
                    v++; // counter value
                } else { // start concat str to s StringList
                    s._head.setNext(new CharNode(data,v,s._head.getNext()));
                    data = str.charAt(i);
                    v = 1; // reset counter value
                }   
            } 
            s._head.setNext(new CharNode(data,v,s._head.getNext())); // last char of s2 adding to s StringList
        }
        return new StringList (temp);
    } // end of concat

    /**
     * Return the first index that char appear in the StringList.
     * Example: s = "abcae", s.indexOf('a') = 0.
     * 
     * Time complexity: O(n);
     * Space complexity: O(1);
     * 
     * @param ch the char to search in the StringList.
     * @return the first index that char appear in the StringList.
     */
    public int indexOf (int ch) {
        CharNode temp1 = _head.getNext(); // skip on dummy head
        if (temp1 != null){
            int v = 0; // counter of vaule represents the index
            while (temp1.getNext() != null && ch != temp1.getData()){
                v++;
                if (v >= temp1.getValue()){
                    temp1 = temp1.getNext();
                }
            }

            if (temp1.getNext() == null && ch != temp1.getData())
                return -1; // return -1 if the char don't appear
            else 
                return v;
        }
        System.out.println(empty()); // empty String err
        return -999;
    } // end of indexOf

    /**
     * Return the first index that char appear in the StringList.
     * Example: s = "abcae", s.indexOf('a'2) = 3.
     * 
     * return -1 if get to last CharNode and don't found.
     * return -1 if get to last StringList length and don't found.
     * 
     * Time complexity: O(n);
     * Space complexity: O(1);
     * 
     * @param ch the char to search in the StringList.
     * @return the first index that char appear in the StringList from the fromIndex.
     */
    public int indexOf (int ch, int fromIndex) {
        CharNode temp = _head.getNext(); // skip on dummy head
        if (temp != null){
            int v = 0; // counter of vaule also represents the index
            while (fromIndex > 0){ // limit the fromIndex length
                v++;
                fromIndex--;
                // if the next != null && the value counter is bigger then value, move to next CharNode
                if (temp.getNext() != null && v >= temp.getValue())
                    temp = temp.getNext();

                // if the next == null && the data not found return -1
                if (temp.getNext() == null && temp.getData() != ch) 
                    return -1;
            }

            while (temp != null && ch != temp.getData() && temp.getNext() != null){
                v++;
                // if the next != null && the value counter is bigger then value, move to next CharNode
                if (v >= temp.getValue()){
                    temp = temp.getNext();
                }
            }

            if (temp == null || temp.getNext() == null && ch != temp.getData())
                return -1; // return -1 if get to last CharNode and don't found
            else 
                return v;
        }
        System.out.println(empty()); // empty String err
        return -999; // empty String
    } // end of indexOf

    /**
     * return true or false if the StringList are equals
     * Time complexity: O(n);
     * Space complexity: O(n);
     * 
     * @param str the second StringList to check if equals.
     * @return true if the StringList are equals.
     */
    public boolean equals (StringList str) {
        if (this._head == null || str._head == null){
            empty(); // empty String err
            return false;
        }
        CharNode t1 = _head.getNext(); // first StringList skip on dummy head
        CharNode t2 = str._head.getNext(); // second StringList skip on dummy head
        if (t1 != null && t2 != null){
            char d1 = t1.getData(); // first StringList data
            int v1 = t1.getValue(); // first StringList value
            char d2 = t2.getData(); // second StringList data
            int v2 = t2.getValue(); // second StringList value

            if ((d1 != d2 || v1 != v2))
                return false; // return false if data or value not equals

            if ((d1 == d2 && v1 == v2) && t1.getNext() == null && t2.getNext() == null)
                return true; // return true if data && value equals and the two StringList in the last CharNode
            else
                return equals (t1.getNext(), t2.getNext()); // Recursion help method
        } else { 
            if (t1 == null && t2 == null){
                empty(); // empty String err
                return true;
            } else {
                return false;
            }
        }
    } // end of equals

    // help method for equals
    private boolean equals (CharNode temp1, CharNode temp2) {
        // return false if one of the CharNode are null and the other don't
        if (temp1 == null && temp2 != null || temp2 == null && temp1 != null)
            return false;

        // return true if data && value equals and the two StringList in the last CharNode
        if ((temp1.getNext() == null && temp2.getNext() == null) // end of the two string
        && (temp1.getData() == temp2.getData())
        && (temp1.getValue() == temp2.getValue()))
            return true;

        char d1 = temp1.getData(); // first StringList data
        int v1 = temp1.getValue(); // first StringList value
        char d2 = temp2.getData(); // second StringList data
        int v2 = temp2.getValue(); // second StringList value

        if ((d1 != d2 || v1 != v2)) // return false if data or value not equal
            return false;
        else
            return equals (temp1.getNext(), temp2.getNext()); // Recursion start
    } // end of equals

    /**
     * Check lexicography between two StringList.
     * return 0 if equals.
     * return -1 if the other StringList big lexicography from the first.
     * return 1 if the other StringList small lexicography from the first.
     * 
     * Time complexity: O(n);
     * Space complexity: O(1); 
     * 
     * @return -1 if the other StringList big lexicography from the first.
     */
    public int compareTo (StringList str) {
        // Recursion method may used more space complexity
        /* if (equals (str))
        return 0; */

        CharNode t1 = _head.getNext(); // first StringList skip on dummy head
        CharNode t2 = str._head.getNext(); // second StringList skip on dummy head
        if (t1 != null && t2 != null){
            char d1 = t1.getData(); // first StringList data
            int v1 = t1.getValue(); // first StringList value
            char d2 = t2.getData(); // second StringList data
            int v2 = t2.getValue(); // second StringList value

            while (t1.getNext() != null && t2.getNext() != null) {
                // found big lexicography return -1
                if ((d1 > d2) || (d1 == d2 && v1 < v2) || (d1 == d2) && (t1.getNext().getData() == t2.getNext().getData()) && v1 < v2)
                    return 1;

                // found small lexicography return -1
                if ((d1 < d2)|| (d1 == d2 && v1 > v2) || (d1 == d2) && (t1.getNext().getData() == t2.getNext().getData()) && v1 > v2)
                    return -1;

                // update all to the next check!    
                t1 = t1.getNext();
                t2 = t2.getNext();
                d1 = t1.getData();
                d2 = t2.getData();
                v1 = t1.getValue();
                v2 = t2.getValue();
            }

            // found equals String return 0
            if (d1 == d2 && v1 == v2 && t1.getNext() == null && t2.getNext() == null)
                return 0;
            // found big lexicography return -1
            if ((d1 < d2) || (d1 == d2 && v1 < v2) || (d1 == d2) && (t1.getNext() == null && t2.getNext() != null))
                return -1;
            // found small lexicography return -1
            if ((d1 > d2) || (d1 == d2 && v1 > v2) || (d1 == d2) && (t2.getNext() == null && t1.getNext() != null))
                return 1;    
        } else { 
            System.out.println(empty()); // empty String err
            return -999;
        }
        return -998; // new bug
    } // end of compareTo

    /**
     * Returns sub-string from index i.
     * Example: s = "abc", s.substring(1) = "bc".
     * 
     * Time complexity: O(n);
     * Space complexity: O(1); 
     * 
     * @param i the index of sub-string to start from.
     * @return new sub-string from index i.
     */
    public StringList substring (int i) {
        StringList s = new StringList (_head); // convert CharNode to StringList
        return new StringList (s,i);
    } // end of substring (int i)

    // help method for substring (int i)
    private StringList(StringList s, int j) {
        CharNode temp = new CharNode('-',-1,null); // dummy head
        int l = s.length(); // represents the length of the String

        if (l == 0){ // check if the String is empty
            System.out.println(empty()); // empty String err
        } else {
            char data = s.charAt(l - 1); // get the last data
            int v = 0; // value counter

            for (int i = l - 1; i >= j; i--){
                if (data == s.charAt(i)){
                    v++; // counter value
                } else {
                    temp.setNext(new CharNode(data,v,temp.getNext())); // set new CharNode
                    data = s.charAt(i); // switch to current data
                    v = 1; // reset value counter
                }   
            } 
            temp.setNext(new CharNode(data,v, temp.getNext())); // last char adding
        }
        this._head = temp; // link to this linked list
    } // end of help method for substring (int i)

    /**
     * Returns sub-string from index i to index j.
     * Example: s = "abcdefg", s.substring(2,5) = "cde".
     * 
     * Time complexity: O(n);
     * Space complexity: O(1); 
     * 
     * @param i the index of sub-string to start from.
     * @param j the index of sub-string to finish.
     * @return new sub-string from index i to index j.
     */
    public StringList substring (int i, int j) {
        StringList s = new StringList (_head); // convert CharNode to StringList
        return new StringList (s,i,j);
    } // end of substring (int i, int j)

    // help method for substring (int i, int j)
    private StringList(StringList s, int j, int k) {
        CharNode temp = new CharNode('-',-1,null); // dummy head
        int l = s.length(); // represents the length of the String
        if (l == 0){ // check if the String is empty
            System.out.println(empty()); // empty String err
        }

        if (k > l) // if enter over number switch to length
            k = l;

        if (l != 0 && l > j){
            char data = s.charAt(l - 1); // get the last data
            int v = 0;

            for (int i = k-1; i >= j; i--){
                if (data == s.charAt(i)){
                    v++; // counter letters
                } else {
                    temp.setNext(new CharNode(data,v,temp.getNext()));
                    data = s.charAt(i);
                    v = 1; // reset counter
                }   
            } 
            temp.setNext(new CharNode(data,v,temp.getNext()));
        }
        this._head = temp;
    } // end of help method for substring (int i, int j)

    /**
     * Return the length of the StringList.
     * Example: s = "abcae", s.length() = 5.
     * 
     * Time complexity: O(n);
     * Space complexity: O(1);
     * 
     * @return the length of the StringList.
     */
    public int length () {
        CharNode temp = _head.getNext(); // skip on dummy head
        if (temp != null){
            int v = 0; // counter of value
            while (temp.getNext() != null){
                v++;
                if (v >= temp.getValue()){
                    temp = temp.getNext();
                }
            }
            return v + temp.getValue(); // adding last value to the counter value
        } else {
            return 0; // empty String
        }
    } // end of length

    /**
     * Return a String representation of StringList
     * (for example: "abcdefg").
     * 
     * Time complexity: O(n);
     * Space complexity: O(1);
     * 
     * @return a String representation of StringList (for example: "abcdefg").
     */
    public String toString() {
        CharNode temp = _head.getNext(); // skip on dummy head
        String str = "";

        if (temp != null){
            char data = temp.getData();
            int value = temp.getValue();

            while (temp.getNext() != null){
                while (value > 0){
                    str += temp.getData(); // add letters to print
                    value--;
                }
                temp = temp.getNext();
                data = temp.getData();
                value = temp.getValue();
            }

            while (value > 0){
                str += temp.getData(); // add the last CharNode
                value--;
            }
        }

        return "\"" + str + "\""; // print output
    } // end of toString
} // end of class
