import java.util.List;

public class Row {
    //ATRIBUTOS
    private List<Double> data;

    //CONSTRUCTORES
    public Row(){
        data = null;
    }
    public Row(List<Double> d){
        data = d;
    }

    //MÉTODOS
    public List<Double> getData(){
        return this.data;
    }
}
