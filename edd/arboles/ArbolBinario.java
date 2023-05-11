// 
// Decompiled by Procyon v0.5.36
// 

package edd.arboles;

import java.util.Iterator;

public class ArbolBinario<C extends Comparable>
{
    public Nodo<C> getRaiz() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    public Iterator<C> getIteradorPreorden() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    public Iterator<C> getIteradorPostorden() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    public Iterator<C> getIteradorInorden() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    public int getAltura() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    public Iterator<C> iterator() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    class Nodo<C>
    {
        Nodo<C> padre;
        Nodo<C> izquierdo;
        Nodo<C> derecho;
        C elem;
        int altura;
        
        Nodo<C> getPadre() {
            return this.padre;
        }
        
        C getElemento() {
            return this.elem;
        }
        
        Nodo<C> getIzquierdo() {
            return this.izquierdo;
        }
        
        Nodo<C> getDerecho() {
            return this.derecho;
        }
        
        void setPadre(final Nodo<C> padre) {
            this.padre = padre;
        }
        
        void setElemento(final C elem) {
            this.elem = elem;
        }
        
        void setIzquierdo(final Nodo<C> izquierdo) {
            this.izquierdo = izquierdo;
        }
        
        void setDerecho(final Nodo<C> derecho) {
            this.derecho = derecho;
        }
        
        int getAltura() {
            return this.altura;
        }
        
        boolean esHoja() {
            return this.altura == 0;
        }
        
        int getGrado() {
            int i = 0;
            if (this.izquierdo != null) {
                ++i;
            }
            if (this.derecho != null) {
                ++i;
            }
            return i;
        }
        
        boolean removeChild(final Nodo<C> hijo) {
            return false;
        }
    }
}
