// 
// Decompiled by Procyon v0.5.36
// 

package edd.adivina;

import java.util.Iterator;
import edd.estructuras.lineales.ArrayList;
import edd.readerwriter.ReaderWriter;
import edd.estructuras.lineales.List;

public class ToXML
{
    private static List<Persona> personas;
    private static List<Caracteristica> caracteristicas;
    private static List<String> xml;
    
    private static void load(final String p, final String c) {
        try {
            List<String> l = ReaderWriter.readLines(p);
            ToXML.personas = new ArrayList<Persona>();
            for (final String s : l) {
                ToXML.personas.add(ToXML.personas.size(), new Persona(s));
            }
            l = ReaderWriter.readLines(c);
            ToXML.caracteristicas = new ArrayList<Caracteristica>();
            for (final String s : l) {
                ToXML.caracteristicas.add(ToXML.caracteristicas.size(), new Caracteristica(s));
            }
        }
        catch (Exception e) {
            System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, e.getMessage()));
            System.out.println(e);
        }
    }
    
    public static void preguntas() {
        load("personas.txt", "caracteristicas.txt");
        (ToXML.xml = new ArrayList<String>()).add(ToXML.xml.size(), "<preguntas>");
        for (final Caracteristica c : ToXML.caracteristicas) {
            final String[] opciones = c.getOpciones();
            final String pregunta = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, c.getNombre());
            ToXML.xml.add(ToXML.xml.size(), "    <caracteristica>");
            ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, c.getNombre()));
            for (final String o : opciones) {
                final List<String> si = new ArrayList<String>();
                final List<String> no = new ArrayList<String>();
                for (final Persona p : ToXML.personas) {
                    if (p.tiene(o)) {
                        si.add(si.size(), p.getNombre());
                    }
                    else {
                        no.add(no.size(), p.getNombre());
                    }
                }
                save(si, invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, pregunta, o), no);
            }
            ToXML.xml.add(ToXML.xml.size(), "    </caracteristica>");
        }
        write();
    }
    
    private static void save(final List<String> si, final String pregunta, final List<String> no) {
        final String s = "    ";
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s));
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s, pregunta));
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        String aux = si.toString();
        aux = aux.replace("[", "");
        aux = aux.replace("]", "");
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s, s, aux));
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        aux = no.toString();
        aux = aux.replace("[", "");
        aux = aux.replace("]", "");
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s, s, aux));
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s, s));
        ToXML.xml.add(ToXML.xml.size(), invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s));
    }
    
    private static void write() {
        ToXML.xml.add(ToXML.xml.size(), "</preguntas>");
        try {
            ReaderWriter.writeLines("adivina.xml", ToXML.xml);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
