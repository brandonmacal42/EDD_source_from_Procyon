// 
// Decompiled by Procyon v0.5.36
// 

package edd;

import edd.estructuras.arboles.ArbolBinario;
import edd.ciudades.Buscador;
import edd.ciudades.Ciudad;
import java.util.Iterator;
import edd.colors.Colors;
import java.util.Scanner;

public class Prueba
{
    public static int getInt(final String mensaje, final String error, final int min, final int max) {
        final Scanner scn = new Scanner(System.in);
        int val;
        while (true) {
            Colors.println(mensaje, "\u001b[1m");
            if (scn.hasNextInt()) {
                val = scn.nextInt();
                if (val >= min && max >= val) {
                    break;
                }
                Colors.println(error, "\u001b[0;31m\u001b[1m");
            }
            else {
                scn.next();
                Colors.println(error, "\u001b[0;31m\u001b[1m");
            }
        }
        return val;
    }
    
    public static String getLine(final String msg) {
        final Scanner scn = new Scanner(System.in);
        Colors.println(msg, "\u001b[1m");
        return scn.next();
    }
    
    public static void print(final String msg, final Iterable it) {
        int i = 1;
        final StringBuilder sb = new StringBuilder();
        Colors.println(msg, "\u001b[1m");
        for (final Object obj : it) {
            sb.append(i++);
            sb.append(". ");
            sb.append(obj.toString());
            sb.append(".\n");
        }
        Colors.println(sb.toString(), "\u001b[0;36m\u001b[1m");
    }
    
    public static String itemize(final Iterable it) {
        int i = 1;
        final StringBuilder sb = new StringBuilder();
        sb.append("Selecciona una opcion.\n");
        for (final Object obj : it) {
            sb.append(i++);
            sb.append(". ");
            sb.append(obj.toString());
            sb.append(".\n");
        }
        sb.append("0. Salir\n");
        return sb.toString();
    }
    
    public static Ciudad getCiudad() {
        String s = getLine("¿Cual es el nombre de la ciudad?");
        s = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, getLine("¿Cual es el estado donde se encuentra la ciudad?"));
        s = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;I)Ljava/lang/String;, s, getInt("¿Cual es su coordenada X?", "Por favor ingresa una opcion valida.", Integer.MIN_VALUE, Integer.MAX_VALUE));
        s = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;I)Ljava/lang/String;, s, getInt("¿Cual es su coordenada Y?", "Por favor ingresa una opcion valida.", Integer.MIN_VALUE, Integer.MAX_VALUE));
        return new Ciudad(s);
    }
    
    public static void main(final String[] args) {
        Colors.println("Este programa es un buscador sobre caracteristicas de ciudades.", "\u001b[0;34m\u001b[1m");
        final Buscador b = new Buscador();
        final StringBuilder sb = new StringBuilder();
        sb.append("Selecciona una opcion.\n");
        sb.append("1. Agregar una ciudad al directorio.\n");
        sb.append("2. Eliminar una ciudad del directorio.\n");
        sb.append("3. Determinar todas las ciudades dentro de un estado.\n");
        sb.append("4. Determinar todas las ciudades dentro de un rango de coordenadas.\n");
        sb.append("5. Imprimir todas las ciudades.\n");
        sb.append("0. Salir.\n");
        final String mensaje = sb.toString();
        final String error = "Por favor ingresa una opcion valida.";
        int opcion;
        do {
            opcion = getInt(mensaje, error, 0, 5);
            switch (opcion) {
                case 2: {
                    final ArbolBinario<?> tree = b.getCiudades();
                    opcion = getInt(itemize(tree), "Por favor ingresa una opcion valida.", 0, tree.size());
                    if (opcion != 0) {
                        b.borrar(opcion - 1);
                        continue;
                    }
                    opcion = -1;
                    continue;
                }
                case 3: {
                    ArbolBinario<?> tree = b.getEstados();
                    opcion = getInt(itemize(tree), error, 0, tree.size());
                    if (opcion != 0) {
                        tree = b.searchEstados(opcion - 1);
                        print("Las ciudades encontradas son: ", tree);
                        continue;
                    }
                    opcion = -1;
                    continue;
                }
                default: {
                    continue;
                }
                case 1: {
                    b.add(getCiudad());
                    continue;
                }
                case 4: {
                    final int minX = b.minX();
                    final int minY = b.minY();
                    final int maxX = b.maxX();
                    final int maxY = b.maxY();
                    final int x1 = getInt(invokedynamic(makeConcatWithConstants:(II)Ljava/lang/String;, minX, maxX - 1), error, minX, maxX - 1);
                    final int x2 = getInt(invokedynamic(makeConcatWithConstants:(II)Ljava/lang/String;, x1, maxX), error, x1, maxX);
                    final int y1 = getInt(invokedynamic(makeConcatWithConstants:(II)Ljava/lang/String;, minY, maxY - 1), error, minY, maxY - 1);
                    final int y2 = getInt(invokedynamic(makeConcatWithConstants:(II)Ljava/lang/String;, y1, maxY), error, y1, maxY);
                    final ArbolBinario<?> tree = b.searchCoordenadas(x1, x2, y1, y2);
                    print("Las ciudades encontradas son: ", tree);
                    continue;
                }
                case 5: {
                    print("Las ciudades dentro del directorio son: ", b.getCiudades());
                    continue;
                }
            }
        } while (opcion != 0);
        Colors.println("Vuelve pronto.", "\u001b[0;34m\u001b[1m");
    }
}
