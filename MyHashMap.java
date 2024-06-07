package CustomCollections;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class MyHashMap<K, V> {
        private int size;
        private final int DEFAULT_SIZE = 8;
        private Entry<K, V>[] array = new Entry[DEFAULT_SIZE];

        public void put(K key, V value){
            if (size >= DEFAULT_SIZE){
                resize();
            }
                int code = calculateIndex(key);
                Entry<K, V> newEntry = new Entry<>(key, value);
                if (array[code] == null){
                    array[code] = newEntry;
                }else{
                    Entry<K, V> current = array[code];
                    while (current != null){
                        if (Objects.equals(current.key, key)){
                            current.value = value;
                            break;
                        }
                        if (current.next == null) {
                            break;
                        }
                        current = current.next;
                    }
                    current.next = newEntry;
                }
                size++;
        }
        public void resize(){
            int newSize = DEFAULT_SIZE * 2;
            Entry<K, V>[] newArray = new Entry[newSize];
            for (Entry<K, V> entry : array){
                while(entry != null){
                    Entry<K, V> next = entry.next;
                    int code = calculateIndex(entry.key);
                    entry.next = newArray[code];
                    newArray[code] = entry;
                    entry = next;
                }
            }
            array = newArray;
        }

        public V get(K key){
            int code = calculateIndex(key);
            Entry<K, V> current = array[code];
            while (current != null){
                if (Objects.equals(current.key, key)){
                    return current.value;
                }
                current = current.next;
            }
            return null;
        }

        private int calculateIndex(K key) {
            int hashCode;
            if (key == null){
                hashCode = "null".hashCode();
            }else{
                hashCode = key.hashCode();
            }
            return Math.abs(hashCode % array.length);
        }

        public int size(){
            return size;
        }

        public void clear(){
            for (int i = 0; i < array.length; i++){
                array[i] = null;
            }
            size = 0;
        }

        public void remove(K key){
            int code = calculateIndex(key);
            Entry<K, V> current = array[code];
            Entry<K, V> previous = null;
            while (current != null){
                if (Objects.equals(current.key, key)){
                    if (previous == null){
                        array[code] = current.next;
                    }else {
                        previous.next = current.next;
                    }
                    size--;
                    break;
                }
                previous = current;
                current = current.next;
            }
        }

    static class Entry<K, V> {
            private K key;
            private V value;
            private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

