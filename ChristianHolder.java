package ManualLinkedList;

/***************************************************************************************************************
 * ChristianHolder.java
 * @author Chris Simpson
 * @version 11/10/2022
 * This program is an exercise made to build our skill set in understanding Data Structures, specifically the
 * List class and its different forms. In particular, this class is made to emulate the Linked List structure.
 ***************************************************************************************************************/
public class ChristianHolder<E>
{
    //global variables
    private Node<E> head;

    private Node<E> tail;

    //constructors
    public ChristianHolder(){}

    public ChristianHolder(Node<E> head){
        this.head = head;
        this.tail = head;
    }

    //getter
    public Node<E> getHead(){
        return head;
    }

    //setter
    public void setHead(Node<E> head){
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    //method to get size of the linked list
    public int getSize() {
        //size to return
        int size = 0;
        //if head is not null,
        if(getHead() != null) {
            //make a new node temp
            Node<E> temp = getHead();
            //increment size
            size++;
            //while temp.getNext() is not the head,
            while((temp.getNext()) != getHead() && temp.getNext() != null) {
                //set temp to next
                temp = temp.getNext();
                //increment size
                size++;
            }
        }
        return size;
    }

    //method to print the values held in each node of the list as a string
    public void printHolder() {
        //if head is null your list has no nodes
        if (getHead() == null) {
            System.out.println("Your container is empty.");
        }
        else {
            //make new node
            Node<E> temp = getHead();
            int listSize = getSize();
            //move through LL one node at a time
            for(int i = 0; i < listSize; i++) {
                //add the data stored in node to string
                System.out.println(temp.getE());
                //move to the next node
                temp = temp.getNext();
            }
        }
    }
    //method to move through the list until you find a specific position
    //accepts a position variable of type int
    //returns the node object at the desired position
    public Node<E> findNodeAtPosition(int aPosition){
        //create node to return, starting at head
        Node<E> atPos = getHead();
        //call getSize to determine list size
        int listSize = getSize();
        //if size > 0,
        if(listSize > 0) {
            //if position is 0, return head
            if (aPosition == 0) {
                return atPos;
                //if position is less than size, search the list
            }else if (aPosition <= listSize) {
                //for loop iterates through list
                for (int i = 0; i < aPosition; i++) {
                    atPos = atPos.getNext();
                }
            }
        }
        return atPos;
    }

    /**
     * findAPosition is used to find the index of a specific node if it exists
     * in the list.
     * @param node the node to find the position of.
     * @return returns the index number.
     */
    public int findAPosition(Node<E> node) {
        int thePos = 0;
        int listSize = getSize();
        if (listSize != 0) {
            if(!node.equals(getHead())) {
                Node<E> temp = getHead();
                for (int i = 0; i < listSize; i++) {
                    if (!temp.equals(node)) {
                        temp = temp.getNext();
                        thePos++;
                    }
                }
            }else{
                return thePos;
            }
            return thePos;
        }else{
            System.out.println("Your list is empty.");
        }
        return 0;
    }

    //method to find a Node that contains the value passed to the method
    public Node<E> findValue(E e){
        //old code
        /*
       //make a new node to store current node
        Node<E> n;
        //if head is not null & if head.getE equals e
        if(getHead() != null) {
        	if(getHead().getE() == e) {
        		//return node
                return getHead();
        	}//else while getNext is not head
            else {
                n = getHead();
                while (n.getNext() != getHead()) {
                    //set node to next
                    n = n.getNext();
                    //compare e to node.getE
                    if (e == n.getE()) {
                        //if equal, return node
                        return n;
                    }
                }
            }
        }else{
            System.out.println("A node with this object value does not exist in this container.");
        }*/
        //new code 11/3/22
        //get first node in list
        Node<E> curr = getHead();
        //variable to check against size
        int checked = 0;
        //move through the list while elements do not match
        while(curr.getE() != e){
            curr = curr.getNext();
            //increment checked with each new node
            checked++;
        }
        //if end of list is reached print an error & return null
        if(checked == getSize()){
            System.out.println("The list does not contain this value.");
            return null;
        }
        //otherwise return the matched node
        return curr;
    }

    /**
     * findValuePosition returns the index of the list in which a node holding
     * the passed value exists at.
     * @param e the value to compare.
     * @return returns the index number.
     */
    public int findValuePosition(E e){
        int thePos = 0;
        int listSize = getSize();
        Node<E> temp = getHead();
        for(int i = 0; i < listSize; i++){
            if(temp.getE() != e){
                temp = temp.getNext();
                thePos++;
            }
        }
        return thePos;
    }

    //method to add a new node at the end of the list
    public void add(E e){
        Node<E> toAdd = new Node<>();
        toAdd.setE(e);
        //if no head, put node at head
        if(getHead() == null){
            setHead(toAdd);
            getHead().setNext(getTail());
            getHead().setPrev(getTail());
            setTail(toAdd);
            getTail().setNext(getHead());
            getTail().setPrev(getHead());

        }
        else{
            Node<E> curr = getHead();
            //move through the list until we reach the end
            while(curr.getNext() != getHead() && curr.getNext() != null){
                curr = curr.getNext();
            }
            //put the node at the end of the list
            //set the node's previous reference to the current node
            toAdd.setPrev(curr);
            //set the node's next reference to the head
            toAdd.setNext(getHead());
            //set the holder tail reference to the added node
            setTail(toAdd);
            //set the current node next reference to the added node
            curr.setNext(toAdd);
        }
    }

    //method to put a node in a specific location in the list
    public void insertAtPos(E e, int aPosition){
        //start at head
        Node<E> curr = getHead();
        //use a loop to move through the list
        for(int i = 0; i < aPosition; i++) {
            //setting curr as next until the position is reached
            curr = curr.getNext();
        }
        //create the node to insert with the correct pointers
        Node<E> insertThis = new Node<>(e, curr.getNext(), curr);
        //set the previous node's next pointer to the new node
        curr.getPrev().setNext(insertThis);
        //set the next node's prev pointer to the new node
        insertThis.getNext().setPrev(insertThis);
    }

    //method to find a node at a specific position in the list and remove it
    public void removeFromPos(int aPosition){
        //start at head
        Node<E> curr = getHead();
        //use a loop to move through the list
        for(int i = 0; i < aPosition; i++) {
            //setting curr as next until the position is reached
            curr = curr.getNext();
        }
        //set the previous node's next pointer to the node after curr
        curr.getPrev().setNext(curr.getNext());
        //set the next node's prev pointer to the node before curr
        curr.getNext().setPrev(curr.getPrev());
    }

    //method to remove the last node in the list and return it
    public Node<E> removeEnd(){
        //get the last node in the list first
        Node<E> toReturn = findNodeAtPosition(getSize() - 1);
        //then get its prev node
        Node<E> secondToLast = toReturn.getPrev();
        //set the prev node as the last position by setting its next as head
        secondToLast.setNext(getHead());
        //set head's prev as new last node
        getHead().setPrev(secondToLast);
        //set the tail as the new last node
        setTail(secondToLast);
        //return the removed node
        return toReturn;
    }

    //method to search for a node with a specific value and remove it from the list
    public void removeValue(E e){
        //get node with the value to remove
        Node<E> toRemove = findValue(e);
        //set the previous node's next pointer to the node after toRemove
        if(toRemove.getNext() != null && toRemove.getPrev() != null) {
            toRemove.getPrev().setNext(toRemove.getNext());
            //set the next node's prev pointer to the node before toRemove
            toRemove.getNext().setPrev(toRemove.getPrev());
        }
        else{
            setHead(null);
            setTail(null);
        }
    }
}