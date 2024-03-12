import java.util.List;

public class RowWhithLabel extends Row{
    //ATRIBUTOS
    private int numberClass;

    //CONSTRUCTORES
    public RowWhithLabel(){
        super(null);
        numberClass = -1;
    }

    public RowWhithLabel(List<Double> d, int n){
        super(d);
        numberClass = n;
    }

    //MÉTODOS
    public int getNumberClass() {
        return numberClass;
    }
}
