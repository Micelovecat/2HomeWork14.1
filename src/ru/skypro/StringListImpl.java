package ru.skypro;

import ru.skypro.exceptions.ElementNotFoundException;
import ru.skypro.exceptions.InputNullException;
import ru.skypro.exceptions.WrongIndexException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] storage;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public StringListImpl() {
        this.storage = new String[DEFAULT_CAPACITY];
    }

    @Override
    public String add(String item) {
        checkIfNull(item);
        if (size == storage.length) {
            growStorage();
        }
        storage[size++] = item;
        return item;
    }

    private void growStorage() {
        int newCapacity = storage.length * 2;
        storage = Arrays.copyOf(storage, newCapacity);
    }

    private void checkIfNull(String item) {
        if (item == null) {
            throw new InputNullException("Input parameter is null");
        }
    }

    @Override
    public String add(int index, String item) {
        checkIfNull(item);
        if (index < 0 || index > size) {
            throw new WrongIndexException("Element index is wrong");
        }
        if (size == storage.length) {
            growStorage();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIfNull(item);
        if (index < 0 || index > size) {
            throw new WrongIndexException("Element index is wrong");
        }
        String previousElement = storage[index];
        storage[index] = item;
        return previousElement;
    }

    @Override
    public String remove(String item) {
        checkIfNull(item);
        int removingElementIndex = indexOf(item);

        if (removingElementIndex == -1) {
            throw new ElementNotFoundException("Element isn~t found!");
        }
        System.arraycopy(storage, removingElementIndex + 1, storage, removingElementIndex, size - removingElementIndex);
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException("Element index is wrong");
        }
        String removingElement = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        return removingElement;
    }

    @Override
    public boolean contains(String item) {
        checkIfNull(item);
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i <= size - 1; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkIfNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException("Element index is wrong");
        }
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null || size != otherList.size()){
            return false;
        }
        for(int i = 0; i <= size -1; i++){
            if (!get(i).equals(otherList.get(i))) {
            return false;
            }
        }
        return true;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        storage = new String[DEFAULT_CAPACITY];
        size = 0;

    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}
