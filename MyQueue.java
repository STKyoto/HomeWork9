package CustomCollections;

public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;


    public void add(T value){
        Node<T> newElement = new Node<>(value);
        if(head == null){
            head = tail = newElement;
        }else{
            tail.next = newElement;
            newElement.previous = tail;
            tail = newElement;
        }
        size++;
    }

    public void clear(){
        Node<T> current = head;
        while (current != null){
            Node<T> nextNode = current.next;
            current.next = null;
            current.previous = null;
            current = nextNode;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public T peek(){
        if (head == null){
            return null;
        }else {
            return head.element;
        }
    }

    public T poll(){
        if (head == null) {
            return null;
        }
        Node<T> elementToReturn = head;
        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null;
        }
        size--;
        return elementToReturn.element;
    }

    static private class Node<T> {
        private T element;
        private Node<T> previous;
        private Node<T> next;

        public Node(T element){
            this.element = element;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[ ");
        Node<T> current = head;
        for(int i = 0; i < size; i++){
            T element = current.element;
            result.append(element).append(" ");
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }
}
