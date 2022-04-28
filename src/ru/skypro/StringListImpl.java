package ru.skypro;

import ru.skypro.exceptions.InputNullException;
import ru.skypro.exceptions.WrongIndexException;

import java.util.Arrays;

public class StringListImpl implements StringList{
    
    private String[] storage;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    
    public StringListImpl(){
        this.storage = new String[DEFAULT_CAPACITY];
    }
    
    @Override
    public String add(String item) {
        checkIfNull(item);
        if (size == storage.length){
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
        if(item == null){
            throw new InputNullException("Input parameter is null");
        }
    }

    @Override
    public String add(int index, String item) {
        checkIfNull(item);
        if (index < 0 || index > size){
            throw new WrongIndexException("Element index is wrong");
        }
        if (size == storage.length){
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
        if (index < 0 || index > size){
            throw new WrongIndexException("Element index is wrong");
        }
        String previousElement = storage[index];
        storage[index] = item;
        return previousElement;
    }

    @Override
    public String remove(String item) {
        return null;
    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public boolean contains(Integer item) {
        return false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int lastIndexOf(String item) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }
}
