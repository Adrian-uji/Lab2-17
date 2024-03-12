import java.io.*;
import java.util.*;

public class CSV {
    //readTable
    public Table readTable(String document) throws IOException {
        File archivo = new File(document);
        if (archivo.exists()){
            //Declaramos las variables que necesitamos
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr); //Esto lee de una en una las líneas
            List<String> headers;
            List<Row> rows = new ArrayList();

            //Si hay una primera línea encuentra una cabecera y luego va una a una a por las row
            String linea = br.readLine();
            if (linea != null){
                headers = readHeaders(linea);
            }
            else{
                throw new IOException(); //Hay que cambiar la excepción
            }
            while((linea = br.readLine()) != null){
                rows.add(readRows(linea));
            }
            if (rows.size() == 0){
                throw new IOException(); //Hay que cambiar la excepción
            }
            Table table = new Table(headers,rows);
            return table;
        }
        else{
            throw new FileNotFoundException();
        }
    }
    //Auxiliares de readTable
    private List<String> readHeaders(String linea){
        String[] words = linea.split(",");
        List<String> headers = new LinkedList<>();
        for (String word:words){
            if (!word.equals("class"))
                headers.add(word);
        }
        return headers;
    }
    private Row readRows(String linea){
        String[] words = linea.split(",");
        List<Double> row = new LinkedList<>();
        for (String word:words){
            row.add(Double.parseDouble(word));
        }
        Row r = new Row(row);
        return r;
    }

    //readTableWhithLabels
    public TableWithLabels readTableWithLabel(String file) throws IOException {
        File archivo = new File(file);
        if (archivo.exists()){
            //Declaramos las variables que necesitamos
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr); //Esto lee de una en una las líneas
            List<String> headers;
            List<Row> rows = new ArrayList();
            Map<String, Integer> labelsToIndex = new HashMap<>();

            //Si hay una primera línea encuentra una cabecera y luego va una a una a por las row
            String linea = br.readLine();
            if (linea != null){
                headers = readHeaders(linea);
            }
            else{
                throw new IOException(); //Hay que cambiar la excepción
            }
            while((linea = br.readLine()) != null){
                rows.add(readRowsWithLabel(linea, labelsToIndex));
            }
            if (rows.size() == 0){
                throw new IOException(); //Hay que cambiar la excepción
            }
            TableWithLabels table = new TableWithLabels(headers,rows,labelsToIndex);
            return table;
        }
        else{
            throw new FileNotFoundException();
        }
    }
    //Auxiliares de readTableWithLabel
    private RowWhithLabel readRowsWithLabel (String linea, Map<String,Integer> labelsToIndex){
        String[] words = linea.split(",");
        List<Double> row = new LinkedList<>();
        int numberClass;
        for (int i = 0; i<words.length-1;i++){
            row.add(Double.parseDouble(words[i]));
        }
        String clase = words[words.length-1];
        if (labelsToIndex.containsKey(clase)){
            numberClass = labelsToIndex.get(clase);
        }
        else{
            numberClass = labelsToIndex.size();
            labelsToIndex.put(clase,numberClass);
        }
        RowWhithLabel r = new RowWhithLabel(row,numberClass);
        return r;
    }
}
