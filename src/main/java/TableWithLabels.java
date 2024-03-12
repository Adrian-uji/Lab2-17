import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableWithLabels extends Table{
    //ATRIBUTOS
    Map<String, Integer> labelsToIndex;

    //CONSTRUCTORES
    public TableWithLabels(){
        super(null,null);
        labelsToIndex = null;
    }

    public TableWithLabels(List<String> h, List<Row> r, Map<String,Integer> l){
        super(h,r);
        labelsToIndex = l;
    }

    //MÉTODOS
    public Set<String> getLabels(){
        if (labelsToIndex == null)
            throw new NullPointerException();
        else
            return labelsToIndex.keySet();
    }
}
