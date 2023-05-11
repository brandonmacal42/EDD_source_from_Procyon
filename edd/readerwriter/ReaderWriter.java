// 
// Decompiled by Procyon v0.5.36
// 

package edd.readerwriter;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import edd.estructuras.lineales.ArrayList;
import edd.estructuras.lineales.List;
import java.io.IOException;
import java.util.Iterator;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;

public class ReaderWriter
{
    public static void writeLines(final String fileName, final Iterable<?> lines) throws IOException {
        int i = 0;
        final Iterator<?> it = lines.iterator();
        final BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(new File(fileName)));
        while (it.hasNext()) {
            bufferedWriter.write(it.next().toString());
            if (it.hasNext()) {
                bufferedWriter.write("\n");
            }
            ++i;
        }
        System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;I)Ljava/lang/String;, fileName, i));
        bufferedWriter.close();
    }
    
    public static List<String> readLines(final String fileName) throws IOException {
        final List<String> list = new ArrayList<String>();
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
        int i = 0;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            list.add(list.size(), line);
            ++i;
        }
        bufferedReader.close();
        System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;I)Ljava/lang/String;, fileName, i));
        return list;
    }
}
