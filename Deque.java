/* *****************************************************************************
 *  Name:              Maksim Tikhonov
 *  Coursera User ID:  d951be14d5a09d1ae01e539156f6cd0c
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    public Deque(){

    }

    // is the deque empty?
    public boolean isEmpty()

    // return the number of items on the deque
    public int size()

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException();
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException();
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (this.isEmpty()) throw new NoSuchElementException();
        return ;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (this.isEmpty()) throw new NoSuchElementException();
        return ;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args){

    }
}
