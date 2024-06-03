package CustomCollections;

import java.util.Arrays;

public class MyArrayList<T> {
        private final int DEFAULT_SIZE = 10;
        private T[] array = (T[]) new Object[DEFAULT_SIZE];
        private int size;

        public void add(T value){
                if (array.length == size){
                        int newSize = size * 2;
                        T[] newArray = Arrays.copyOf(array, newSize);
                        array = newArray;
                }
                array[size] = value;
                size++;
        }

        public void remove(int index){
                if (index >= size || index < 0) {
                        throw new IndexOutOfBoundsException("Index: " + index + ". Size: " + size);
                }
                for (int i = index; i < size - 1; i++){
                        array[i] = array[i + 1];
                }
                array[size - 1] = null;
                size--;
        }

        public void clear(){
                for(int i = 0; i < size; i++){
                        array[i] = null;
                }
                size = 0;
        }

        public int size(){
                return size;
        }

        public T get(int index){
                if (index >= size){
                        throw new IndexOutOfBoundsException("Index: " + index + ". Size: " + size);
                }
                return array[index];
        }

        @Override
        public String toString() {
                StringBuilder result = new StringBuilder("[ ");
                for(int i = 0; i < size; i++){
                        result.append(array[i]).append(" ");
                }
                result.append("]");
                return result.toString();
        }
}
