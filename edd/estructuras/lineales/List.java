// 
// Decompiled by Procyon v0.5.36
// 

package edd.estructuras.lineales;

public interface List<E> extends Iterable<E>
{
    int size();
    
    boolean isEmpty();
    
    E get(final int p0) throws IndexOutOfBoundsException;
    
    E set(final int p0, final E p1) throws IndexOutOfBoundsException;
    
    void add(final int p0, final E p1) throws IndexOutOfBoundsException;
    
    E remove(final int p0) throws IndexOutOfBoundsException;
}
