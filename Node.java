public class Node<E>{

    //object held by the node
    private E e;

    //holds references to next and previous nodes
    private Node<E> next;

    private Node<E> prev;
    
    //constructors
    public Node(){}
    
    public Node(E e, Node<E> next, Node<E> prev){
        this.e = e;
        this.next = next;
        this.prev = prev;
    }
    
    //getters

    public E getE() {
        return e;
    }

    public Node<E> getNext(){
        return next;
    }

    public Node<E> getPrev(){
        return prev;
    }

    //setters
    
    public void setE(E e) {
        this.e = e;
    }

    public void setNext(Node<E> next){
        this.next = next;
    }
    
    public void setPrev(Node<E> prev){
        this.prev = prev;
    }
}
