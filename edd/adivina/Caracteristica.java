// 
// Decompiled by Procyon v0.5.36
// 

package edd.adivina;

public class Caracteristica
{
    private String nombre;
    private String[] opciones;
    
    public Caracteristica(final String caracteristica) {
        final String[] a = caracteristica.split(",");
        this.nombre = a[0];
        this.opciones = new String[a.length - 1];
        for (int i = 1; i < a.length; ++i) {
            this.opciones[i - 1] = a[i];
        }
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String[] getOpciones() {
        return this.opciones;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.nombre);
        sb.append(": ");
        for (int i = 0; i < this.opciones.length; ++i) {
            sb.append(this.opciones[i]);
            if (i != this.opciones.length - 1) {
                sb.append(" ");
            }
        }
        sb.append(">");
        return sb.toString();
    }
}
