package pronostico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Archivo {

    public Archivo() {}

    public Archivo(String archivo, String nuevoTexto) {
        try{
            File fichero = new File(archivo);
            if(fichero.exists()){
                BufferedWriter escribir = new BufferedWriter(new FileWriter(archivo, true));
                escribir.write(nuevoTexto);
                escribir.newLine();
                escribir.close();
            }
        }catch (Exception e){
            System.out.println("No se pudo escribir el Archivo...");
        }

    }
    public List<String[]> archivo(String archivo) {
        List<String[]> lista = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();

            while(lectura != null){
                lista.add(lectura.split(";"));
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println("No se pudo leer el Archivo...");
        }
        return lista;

    }
}
