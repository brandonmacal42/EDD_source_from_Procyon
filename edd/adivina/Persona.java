// 
// Decompiled by Procyon v0.5.36
// 

package edd.adivina;

public class Persona
{
    private String nombre;
    private String genero;
    private String piel;
    private String ojos;
    private String cabello;
    
    public Persona(final String persona) {
        final String[] a = persona.split("\\s+");
        this.nombre = a[0];
        this.genero = a[1];
        this.piel = a[2];
        this.ojos = a[3];
        this.cabello = a[4];
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public boolean tiene(final String opcion) {
        return this.genero.equals(opcion) || this.piel.equals(opcion) || this.ojos.equals(opcion) || this.cabello.equals(opcion);
    }
    
    @Override
    public String toString() {
        return invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, this.nombre, this.genero, this.piel, this.ojos, this.cabello);
    }
}
