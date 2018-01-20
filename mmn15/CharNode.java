
/**
 * This class represents CharNode for linked list.
 * A CharNode object is represented by char data,
 * the numbers of times of char value appears continuous,
 * and the next CharNode on the linked list.
 *
 * @author Ron F.
 * @version 1.0
 * @date 2018-01-20
 */
public class CharNode
{
    // all the attributes
    private char _data;
    private int _value;
    private CharNode _next;
    
    /**
     * Constructor for a CharNode object.
     * 
     * @param c the char to enter to CharNode.
     * @param val the numbers of times of char value appears continuous.
     * @param n the next CharNode on the linked list.
     * 
     */
    public CharNode(char c, int val, CharNode n) {
        _data = c;
        _value = val;
        _next = n;
    }
    
    /**
     * Returns the next CharNode on the linked list.
     * 
     * @return the next CharNode on the linked list.
     */
    public CharNode getNext () { return _next; }
    
    /**
     * Change the next CharNode on the linked list.
     * 
     * @param node The new CharNode linked.
     */
    public void setNext (CharNode node) { _next = node; }
    
    /**
     * Returns the value of the CharNode.
     * 
     * @return the value of the CharNode.
     */
    public int getValue () { return _value; }
    
    /**
     * Change the value of the CharNode
     * 
     * @param v The new value of the CharNode.
     */
    public void setValue (int v) { _value = v; }
    
    /**
     * Returns the data of the CharNode.
     * 
     * @return the data of the CharNode.
     */
    public char getData () { return _data; }
    
    /**
     * Change the data of the CharNode
     * 
     * @param c The new data of the CharNode.
     */
    public void setData (char c) { _data = c; }
}
