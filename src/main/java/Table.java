import java.util.List;

public class Table {
    //ATRIBUTOS
    private List<String> headers;
    private List<Row> rows;


    //CONSTRUCTORES
    public Table(){headers = null; rows = null;}
    public Table (List<String> h, List<Row> r){
        headers = h;
        rows = r;
    }

    //MÉTODOS
    public Row getRowAt(int pos){
        if (rows.size() < pos){
            throw new IndexOutOfBoundsException();
        }
        else{
            return rows.get(pos);
        }
    }
    public int getSize(){
        return rows.size();
    }

    public int getColumnSize(){
        return headers.size();
    }
}