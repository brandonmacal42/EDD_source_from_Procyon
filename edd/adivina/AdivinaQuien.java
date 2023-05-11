// 
// Decompiled by Procyon v0.5.36
// 

package edd.adivina;

import edd.colors.Colors;
import edd.readerwriter.ReaderWriter;
import java.util.Random;
import java.util.Iterator;
import edd.estructuras.lineales.ArrayList;
import edd.estructuras.arboles.ArbolBinario;
import edd.estructuras.lineales.List;

public class AdivinaQuien
{
    private int turno;
    private List<Turno> turnos;
    private ArbolBinario<Turno> tree;
    private List<Persona> personas;
    private List<Caracteristica> caracteristicas;
    private List<String> xml;
    private Persona jugador;
    private List<Persona> posibles;
    private List<Persona> rechazadas;
    private List<Pregunta> genero;
    private List<Pregunta> piel;
    private List<Pregunta> ojos;
    private List<Pregunta> cabello;
    private Persona pc;
    private List<Persona> pc_posibles;
    private List<Persona> pc_rechazadas;
    private List<Pregunta> pc_genero;
    private List<Pregunta> pc_piel;
    private List<Pregunta> pc_ojos;
    private List<Pregunta> pc_cabello;
    
    public AdivinaQuien() {
        this.turnos = new ArrayList<Turno>();
        this.tree = new ArbolBinario<Turno>();
        this.turno = 1;
        this.load("personas.txt", "caracteristicas.txt", "adivina.xml");
    }
    
    @Override
    public String toString() {
        return this.turnos.toString();
    }
    
    public String fromTree() {
        return this.tree.toString();
    }
    
    public String revisarGanador() {
        if (this.posibles.size() == 1) {
            return "Gano el jugador.";
        }
        if (this.pc_posibles.size() == 1) {
            return "Gano la pc.";
        }
        return null;
    }
    
    public List<Persona> getPersonas() {
        return this.personas;
    }
    
    public List<Persona> getPosibles() {
        return this.posibles;
    }
    
    public List<Persona> getRechazados() {
        return this.rechazadas;
    }
    
    public List<Persona> getPC_Posibles() {
        return this.pc_posibles;
    }
    
    public List<Persona> getPC_Rechazados() {
        return this.pc_rechazadas;
    }
    
    public List<Pregunta> getPreguntas(final int opcion) {
        switch (opcion) {
            case 1: {
                return this.genero;
            }
            case 2: {
                return this.piel;
            }
            case 3: {
                return this.ojos;
            }
            case 4: {
                return this.cabello;
            }
            default: {
                return new ArrayList<Pregunta>();
            }
        }
    }
    
    public boolean ask(final int opcion, int pregunta) {
        Pregunta p = null;
        --pregunta;
        switch (opcion) {
            case 1: {
                p = this.genero.remove(pregunta);
                break;
            }
            case 2: {
                p = this.piel.remove(pregunta);
                break;
            }
            case 3: {
                p = this.ojos.remove(pregunta);
                break;
            }
            case 4: {
                p = this.cabello.remove(pregunta);
                break;
            }
        }
        return this.ask(true, p, this.pc, this.posibles, this.rechazadas);
    }
    
    private boolean ask(final boolean jugador, final Pregunta p, final Persona a, final List<Persona> l, final List<Persona> ll) {
        String[] array = p.getSi();
        boolean ask = false;
        for (final String s : array) {
            if (a.getNombre().equals(s)) {
                ask = true;
                break;
            }
        }
        if (ask) {
            array = p.getNo();
        }
        else {
            array = p.getSi();
        }
        final Iterator<Persona> it = l.iterator();
        while (it.hasNext()) {
            final Persona r = it.next();
            for (final String s : array) {
                if (r.getNombre().equals(s)) {
                    it.remove();
                    ll.add(ll.size(), r);
                }
            }
        }
        this.turnos.add(this.turnos.size(), new Turno(p, l, ll, ask, jugador, this.turno));
        this.tree.add(new Turno(p, l, ll, ask, jugador, this.turno));
        ++this.turno;
        return ask;
    }
    
    public String turnoPC() {
        Pregunta p = null;
        final Random rdm = new Random();
        final int opcion = rdm.nextInt(4) + 1;
        switch (opcion) {
            case 1: {
                final int pregunta = rdm.nextInt(this.pc_genero.size());
                p = this.pc_genero.remove(pregunta);
                break;
            }
            case 2: {
                final int pregunta = rdm.nextInt(this.pc_piel.size());
                p = this.pc_piel.remove(pregunta);
                break;
            }
            case 3: {
                final int pregunta = rdm.nextInt(this.pc_ojos.size());
                p = this.pc_ojos.remove(pregunta);
                break;
            }
            case 4: {
                final int pregunta = rdm.nextInt(this.pc_cabello.size());
                p = this.pc_cabello.remove(pregunta);
                break;
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("La pc pregunto: ");
        sb.append(p);
        sb.append(", y la respuesta es ");
        if (this.ask(false, p, this.jugador, this.pc_posibles, this.pc_rechazadas)) {
            sb.append("SI.");
        }
        else {
            sb.append("NO.");
        }
        return sb.toString();
    }
    
    private void load(final String p, final String c, final String a) {
        String no;
        String pregunta;
        String si = pregunta = (no = null);
        try {
            List<String> l = ReaderWriter.readLines(p);
            this.personas = new ArrayList<Persona>();
            this.posibles = new ArrayList<Persona>();
            this.rechazadas = new ArrayList<Persona>();
            this.pc_posibles = new ArrayList<Persona>();
            this.pc_rechazadas = new ArrayList<Persona>();
            for (final String s : l) {
                this.personas.add(this.personas.size(), new Persona(s));
                this.posibles.add(this.posibles.size(), new Persona(s));
                this.pc_posibles.add(this.pc_posibles.size(), new Persona(s));
            }
            l = ReaderWriter.readLines(c);
            this.caracteristicas = new ArrayList<Caracteristica>();
            for (final String s : l) {
                this.caracteristicas.add(this.caracteristicas.size(), new Caracteristica(s));
            }
            l = ReaderWriter.readLines(a);
            this.genero = new ArrayList<Pregunta>();
            this.piel = new ArrayList<Pregunta>();
            this.ojos = new ArrayList<Pregunta>();
            this.cabello = new ArrayList<Pregunta>();
            this.pc_genero = new ArrayList<Pregunta>();
            this.pc_piel = new ArrayList<Pregunta>();
            this.pc_ojos = new ArrayList<Pregunta>();
            this.pc_cabello = new ArrayList<Pregunta>();
            int i = 0;
            int clock = 0;
            for (final String s : l) {
                if (s.indexOf("<caracteristica>") != -1) {
                    ++i;
                }
                else if (s.indexOf("<pregunta>") != -1) {
                    clock = 1;
                }
                else {
                    if (s.indexOf("<") != -1) {
                        continue;
                    }
                    if (clock == 0) {
                        ++clock;
                    }
                    else {
                        if (clock == 1) {
                            pregunta = s;
                        }
                        else if (clock == 2) {
                            si = s;
                        }
                        else if (clock == 3) {
                            no = s;
                            switch (i) {
                                case 1: {
                                    this.genero.add(this.genero.size(), new Pregunta(pregunta, si, no));
                                    this.pc_genero.add(this.pc_genero.size(), new Pregunta(pregunta, si, no));
                                    break;
                                }
                                case 2: {
                                    this.piel.add(this.piel.size(), new Pregunta(pregunta, si, no));
                                    this.pc_piel.add(this.pc_piel.size(), new Pregunta(pregunta, si, no));
                                    break;
                                }
                                case 3: {
                                    this.ojos.add(this.ojos.size(), new Pregunta(pregunta, si, no));
                                    this.pc_ojos.add(this.pc_ojos.size(), new Pregunta(pregunta, si, no));
                                    break;
                                }
                                case 4: {
                                    this.cabello.add(this.cabello.size(), new Pregunta(pregunta, si, no));
                                    this.pc_cabello.add(this.pc_cabello.size(), new Pregunta(pregunta, si, no));
                                    break;
                                }
                            }
                        }
                        ++clock;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, e.getMessage()));
            System.out.println(e);
        }
    }
    
    public void setPersonaje(int opcion) {
        --opcion;
        this.jugador = this.personas.get(opcion);
        final Random rdm = new Random();
        opcion = rdm.nextInt(this.personas.size());
        this.pc = this.personas.get(opcion);
        Colors.println(invokedynamic(makeConcatWithConstants:(Ledd/adivina/Persona;)Ljava/lang/String;, this.jugador), "\u001b[0;31m\u001b[1m");
        Colors.println(invokedynamic(makeConcatWithConstants:(Ledd/adivina/Persona;)Ljava/lang/String;, this.pc), "\u001b[0;31m\u001b[1m");
    }
    
    public void preguntas() {
        (this.xml = new ArrayList<String>()).add(this.xml.size(), "<preguntas>");
        for (final Caracteristica c : this.caracteristicas) {
            final String[] opciones = c.getOpciones();
            final String pregunta = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, c.getNombre());
            this.xml.add(this.xml.size(), "    <caracteristica>");
            this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, c.getNombre()));
            for (final String o : opciones) {
                final List<String> si = new ArrayList<String>();
                final List<String> no = new ArrayList<String>();
                for (final Persona p : this.personas) {
                    if (p.tiene(o)) {
                        si.add(si.size(), p.getNombre());
                    }
                    else {
                        no.add(no.size(), p.getNombre());
                    }
                }
                this.save(si, invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, pregunta, o), no);
            }
            this.xml.add(this.xml.size(), "    </caracteristica>");
        }
        this.write();
    }
    
    private void save(final List<String> si, final String pregunta, final List<String> no) {
        final String s = "    ";
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s));
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s, pregunta));
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        String aux = si.toString();
        aux = aux.replace("[", "");
        aux = aux.replace("]", "");
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s, s, aux));
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        aux = no.toString();
        aux = aux.replace("[", "");
        aux = aux.replace("]", "");
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s, s, aux));
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        this.xml.add(this.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s));
    }
    
    private void write() {
        this.xml.add(this.xml.size(), "</preguntas>");
        try {
            ReaderWriter.writeLines("adivina.xml", this.xml);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
