package CustomCollections;

public class MyStack<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void push(T value){
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

    public void remove(int index){
        if ((index < 0)||(index >= size)){
            throw new IndexOutOfBoundsException("index: " + index + ". Size: " + size);
        }else {
            if (index == 0){
                head = head.next;
                if (head != null){
                    head.previous = null;
                }else{
                    tail = null;
                }
            }else if (index == size - 1) {
                tail = tail.previous;
                tail.next = null;
            }else {
                Node<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                Node<T> previousNode = current.previous;
                Node<T> nextNode = current.next;

                previousNode.next = nextNode;
                nextNode.previous = previousNode;
            }
        }
        size--;
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
            return tail.element;
        }
    }

    public T pop(){
        if (head == null) {
            return null;
        }
        Node<T> elementToReturn = tail;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
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
