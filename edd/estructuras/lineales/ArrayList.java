// 
// Decompiled by Procyon v0.5.36
// 

package edd.estructuras.lineales;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class ArrayList<E> implements List<E>
{
    protected E[] array;
    public static final int CAPACITY = 16;
    protected int size;
    
    public ArrayList() {
        this(16);
    }
    
    public ArrayList(final int capacity) {
        this.size = 0;
        this.array = (E[])new Object[capacity];
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    protected void checkIndex(final int index, final int max) throws IndexOutOfBoundsException {
        if (index < 0 || index >= max) {
            throw new IndexOutOfBoundsException(invokedynamic(makeConcatWithConstants:(I)Ljava/lang/String;, index));
        }
    }
    
    @Override
    public E get(final int index) throws IndexOutOfBoundsException {
        this.checkIndex(index, this.size);
        return this.array[index];
    }
    
    @Override
    public E set(final int index, final E e) throws IndexOutOfBoundsException {
        this.checkIndex(index, this.size);
        final E previous = this.array[index];
        this.array[index] = e;
        return previous;
    }
    
    @Override
    public void add(final int index, final E e) throws IndexOutOfBoundsException {
        this.checkIndex(index, this.size + 1);
        if (this.size == this.array.length) {
            final E[] aux = (E[])new Object[this.size * 2];
            for (int j = 0; j < this.size; ++j) {
                aux[j] = this.array[j];
            }
            this.array = aux;
        }
        for (int j = this.size - 1; j >= index; --j) {
            this.array[j + 1] = this.array[j];
        }
        this.array[index] = e;
        ++this.size;
    }
    
    @Override
    public E remove(final int index) throws IndexOutOfBoundsException {
        this.checkIndex(index, this.size);
        final E e = this.array[index];
        for (int j = index; j < this.size - 1; ++j) {
            this.array[j] = this.array[j + 1];
        }
        this.array[this.size - 1] = null;
        --this.size;
        return e;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }
    
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; ++i) {
            sb.append(this.array[i]);
            if (i < this.size - 1) {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    protected class ArrayListIterator implements Iterator<E>
    {
        protected int next;
        protected boolean canRemove;
        
        public ArrayListIterator() {
            this.next = 0;
            this.canRemove = false;
        }
        
        @Override
        public boolean hasNext() {
            return this.next < ArrayList.this.size;
        }
        
        @Override
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final E e = ArrayList.this.array[this.next++];
            this.canRemove = true;
            return e;
        }
        
        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException();
            }
            --this.next;
            for (int index = this.next; index < ArrayList.this.size - 1; ++index) {
                ArrayList.this.array[index] = ArrayList.this.array[index + 1];
            }
            final ArrayList this$0 = ArrayList.this;
            --this$0.size;
            this.canRemove = false;
        }
    }
}
