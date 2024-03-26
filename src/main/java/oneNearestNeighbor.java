import java.util.List;

public class oneNearestNeighbor extends Kmethods implements Algorithm<TableWithLabels,Integer,List<Double>> {
    private TableWithLabels database;

    @Override
    public void train(TableWithLabels data){
        database = data;
    }

    @Override
    public Integer estimate (List<Double> data){
        RowWhithLabel nearestNeighbor = (RowWhithLabel) database.getRowAt(0);
        double nearestComparation = euclidea(database.getRowAt(0).getData(),data);
        List<Double> list;
        double aux;
        for (int i = 1; i<database.getSize();i++){
            list = database.getRowAt(i).getData();
            aux = euclidea(list,data);
            if (aux<nearestComparation){
                nearestComparation = aux;
                nearestNeighbor = (RowWhithLabel) database.getRowAt(i);
            }
        }
        return nearestNeighbor.getNumberClass();
    }

}
