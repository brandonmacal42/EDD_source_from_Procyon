// 
// Decompiled by Procyon v0.5.36
// 

package edd.adivina;

import java.util.Iterator;
import edd.estructuras.lineales.ArrayList;
import edd.estructuras.lineales.List;

public class Turno implements Comparable<Turno>
{
    private Pregunta pregunta;
    private List<Persona> si;
    private List<Persona> no;
    private boolean respuesta;
    private boolean jugador;
    private int orden;
    
    public Turno(final Pregunta pregunta, final List<Persona> si, final List<Persona> no, final boolean respuesta, final boolean jugador, final int orden) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.jugador = jugador;
        this.orden = orden;
        this.si = new ArrayList<Persona>();
        for (final Persona p : si) {
            this.si.add(this.si.size(), p);
        }
        this.no = new ArrayList<Persona>();
        for (final Persona p : no) {
            this.no.add(this.no.size(), p);
        }
    }
    
    @Override
    public int compareTo(final Turno t) {
        return this.orden - t.orden;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.jugador) {
            sb.append("@@Turno de jugador:@1 ");
        }
        else {
            sb.append("@@Turno de PC:@1 ");
        }
        sb.append(this.pregunta);
        sb.append(" ");
        if (this.respuesta) {
            sb.append("@2");
            sb.append(this.pregunta);
            sb.append(" ");
            sb.append("SI");
        }
        else {
            sb.append("@3");
            sb.append(this.pregunta);
            sb.append(" ");
            sb.append("NO");
        }
        sb.append(".@1\n@@Posibles:@1 ");
        sb.append(this.si);
        sb.append(".\n@@Rechazadas:@1 ");
        sb.append(this.no);
        sb.append(".\n");
        return sb.toString();
    }
}
