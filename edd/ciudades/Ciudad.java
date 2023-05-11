// 
// Decompiled by Procyon v0.5.36
// 

package edd.ciudades;

public class Ciudad implements Comparable<Ciudad>
{
    private String nombre;
    private String estado;
    private int x;
    private int y;
    
    public Ciudad(final String ciudad) {
        final String[] a = ciudad.split("\\s+");
        this.nombre = a[0];
        this.estado = a[1];
        this.x = Integer.valueOf(a[2]);
        this.y = Integer.valueOf(a[3]);
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getEstado() {
        return this.estado;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    @Override
    public int compareTo(final Ciudad c) {
        return invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, this.estado, this.nombre).compareTo(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, c.estado, c.nombre));
    }
    
    public boolean sameEstado(final String estado) {
        return this.estado.equals(estado);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Ciudad)) {
            return false;
        }
        final Ciudad c = (Ciudad)obj;
        return this.nombre.equals(c.nombre) && this.estado.equals(c.estado) && this.x == c.x && this.y == c.y;
    }
    
    @Override
    public String toString() {
        return invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;II)Ljava/lang/String;, this.nombre, this.estado, this.x, this.y);
    }
}
