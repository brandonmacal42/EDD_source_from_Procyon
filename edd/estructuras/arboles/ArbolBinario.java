// 
// Decompiled by Procyon v0.5.36
// 

package edd.estructuras.arboles;

import java.util.NoSuchElementException;
import edd.estructuras.lineales.ArrayList;
import java.util.Iterator;

public class ArbolBinario<C extends Comparable> implements Iterable<C>
{
    protected Node<C> root;
    protected int size;
    
    public ArbolBinario() {
        this.root = null;
        this.size = 0;
    }
    
    public Node<C> getRoot() {
        return this.root;
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public Iterator<C> getIteratorPreOrder() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    public Iterator<C> getIteratorPostOrder() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    public Iterator<C> getIteratorInOrder() {
        throw new UnsupportedOperationException("Metodo sin implementar.");
    }
    
    @Override
    public Iterator<C> iterator() {
        return new InOrder();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<C> it = this.iterator();
        sb.append("[");
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public void add(final C c) {
        if (this.root == null) {
            this.root = new Node<C>();
            this.root.elem = c;
        }
        else {
            this.root.add(c);
        }
        ++this.size;
    }
    
    public boolean remove(final C c) {
        Node<C> node = this.root;
        while (node != null) {
            if (node.elem.compareTo(c) == 0) {
                if (node.left != null) {
                    final Node<C> aux = node.left.max();
                    node.elem = aux.elem;
                    this.remove(aux);
                }
                else if (node.right != null) {
                    final Node<C> aux = node.right.min();
                    node.elem = aux.elem;
                    this.remove(aux);
                }
                else {
                    this.remove(node);
                }
                --this.size;
                return true;
            }
            if (node.elem.compareTo(c) > 0) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return false;
    }
    
    private void remove(final Node<C> node) {
        final Node<C> f = node.padre;
        if (node.isLeaf()) {
            if (f != null) {
                if (f.getChild(node) == 0) {
                    f.left = null;
                }
                else {
                    f.right = null;
                }
            }
        }
        else {
            Node<C> aux;
            if (node.left != null) {
                aux = node.left;
                node.left.padre = f;
            }
            else {
                aux = node.right;
                node.right.padre = f;
            }
            if (f != null) {
                if (f.getChild(node) == 0) {
                    f.left = aux;
                }
                else {
                    f.right = aux;
                }
            }
        }
        node.padre = null;
        node.left = null;
        node.right = null;
    }
    
    public boolean contains(final C c) {
        Node<C> node = this.root;
        while (node != null) {
            if (node.elem.compareTo(c) == 0) {
                return true;
            }
            if (node.elem.compareTo(c) > 0) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return false;
    }
    
    public C min() {
        if (this.root != null) {
            return this.root.min().elem;
        }
        return null;
    }
    
    public C max() {
        if (this.root != null) {
            return this.root.max().elem;
        }
        return null;
    }
    
    class Preorder implements Iterator<C>
    {
        ArrayList<Node<C>> queue;
        
        public Preorder() {
            this.queue = new ArrayList<Node<C>>();
            if (ArbolBinario.this.root != null) {
                this.queue.add(0, ArbolBinario.this.root);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }
        
        @Override
        public C next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Node<C> node = this.queue.remove(0);
            if (node.izquierdo != null) {
                this.queue.add(this.queue.size(), node.izquierdo);
            }
            if (node.derecho != null) {
                this.queue.add(this.queue.size(), node.derecho);
            }
            return node.elem;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Metodo sin implementar.");
        }
    }
    
    class PreOrder implements Iterator<C>
    {
        ArrayList<Node<C>> stack;
        
        public PreOrder() {
            this.stack = new ArrayList<Node<C>>();
            if (ArbolBinario.this.root != null) {
                this.stack.add(0, ArbolBinario.this.root);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }
        
        @Override
        public C next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Node<C> node = this.stack.remove(0);
            if (node.right != null) {
                this.stack.add(0, node.right);
            }
            if (node.left != null) {
                this.stack.add(0, node.left);
            }
            return node.elem;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Metodo sin implementar.");
        }
    }
    
    class InOrder implements Iterator<C>
    {
        ArrayList<Node<C>> stack;
        
        public InOrder() {
            this.stack = new ArrayList<Node<C>>();
            if (ArbolBinario.this.root != null) {
                this.stack.add(0, ArbolBinario.this.root);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }
        
        @Override
        public C next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Node<C> node = this.stack.remove(0);
            if (node.isLeaf()) {
                return node.elem;
            }
            if (node.right != null) {
                this.stack.add(0, node.right);
            }
            final Node<C> aux = new Node<C>();
            aux.elem = node.elem;
            this.stack.add(0, aux);
            if (node.left != null) {
                this.stack.add(0, node.left);
            }
            return this.next();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Metodo sin implementar.");
        }
    }
    
    class Nodo<C extends Comparable>
    {
        Nodo<C> padre;
        Nodo<C> izquierdo;
        Nodo<C> derecho;
        C elem;
        int altura;
        
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
        
        void add(final C c) {
            if (this.elem.compareTo(c) > 0) {
                if (this.izquierdo == null) {
                    this.izquierdo = new Nodo<C>();
                    this.izquierdo.elem = c;
                }
                else {
                    this.izquierdo.add(c);
                }
            }
            else if (this.derecho == null) {
                this.derecho = new Nodo<C>();
                this.derecho.elem = c;
            }
            else {
                this.derecho.add(c);
            }
            ++this.altura;
        }
        
        boolean removeChild(final Nodo<C> hijo) {
            return false;
        }
    }
    
    class PostOrder implements Iterator<C>
    {
        ArrayList<Node<C>> stack;
        
        public PostOrder() {
            this.stack = new ArrayList<Node<C>>();
            if (ArbolBinario.this.root != null) {
                this.stack.add(0, ArbolBinario.this.root);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }
        
        @Override
        public C next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Node<C> node = this.stack.remove(0);
            if (node.isLeaf()) {
                return node.elem;
            }
            final Node<C> aux = new Node<C>();
            aux.elem = node.elem;
            this.stack.add(0, aux);
            if (node.right != null) {
                this.stack.add(0, node.right);
            }
            if (node.left != null) {
                this.stack.add(0, node.left);
            }
            return this.next();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Metodo sin implementar.");
        }
    }
    
    class Node<C extends Comparable>
    {
        Node<C> padre;
        Node<C> left;
        Node<C> right;
        C elem;
        
        int getHeight() {
            int l;
            int r = l = -1;
            if (this.left != null) {
                l = this.getHeight();
            }
            if (this.right != null) {
                r = this.getHeight();
            }
            int h = (l < r) ? r : l;
            return ++h;
        }
        
        boolean isLeaf() {
            return this.getGrade() == 0;
        }
        
        int getGrade() {
            int i = 0;
            if (this.left != null) {
                ++i;
            }
            if (this.right != null) {
                ++i;
            }
            return i;
        }
        
        void add(final C c) {
            if (this.elem.compareTo(c) > 0) {
                if (this.left == null) {
                    this.left = new Node<C>();
                    this.left.elem = c;
                    this.left.padre = this;
                }
                else {
                    this.left.add(c);
                }
            }
            else if (this.right == null) {
                this.right = new Node<C>();
                this.right.elem = c;
                this.right.padre = this;
            }
            else {
                this.right.add(c);
            }
        }
        
        int getChild(final Node<C> hijo) {
            if (this.left == hijo) {
                return 0;
            }
            if (this.right == hijo) {
                return 1;
            }
            return -1;
        }
        
        Node<C> min() {
            Node<C> node;
            for (node = this; node.left != null; node = node.left) {}
            return node;
        }
        
        Node<C> max() {
            Node<C> node;
            for (node = this; node.right != null; node = node.right) {}
            return node;
        }
        
        @Override
        public String toString() {
            return invokedynamic(makeConcatWithConstants:(Ljava/lang/Comparable;)Ljava/lang/String;, this.elem);
        }
    }
}
