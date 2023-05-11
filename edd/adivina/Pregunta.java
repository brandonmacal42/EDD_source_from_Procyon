// 
// Decompiled by Procyon v0.5.36
// 

package edd.adivina;

public class Pregunta
{
    private String pregunta;
    private String[] si;
    private String[] no;
    
    public Pregunta(final String pregunta, final String si, final String no) {
        this.pregunta = pregunta.trim();
        this.si = si.trim().split(" ");
        this.no = no.trim().split(" ");
    }
    
    public String getPregunta() {
        return this.pregunta;
    }
    
    public String[] getSi() {
        return this.si;
    }
    
    public String[] getNo() {
        return this.no;
    }
    
    @Override
    public String toString() {
        return this.pregunta;
    }
}
