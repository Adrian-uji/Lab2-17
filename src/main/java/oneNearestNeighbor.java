import java.util.List;

public class oneNearestNeighbor {
    private TableWithLabels database;

    public void train(TableWithLabels data){
        database = data;
    }
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
    public double euclidea(List<Double> z, List<Double> x){
        double devolver = 0.0;
        int pos = 0;

        while(pos < z.size()){
            devolver += Math.pow(z.get(pos)-x.get(pos), 2);
            pos++;
        }
        devolver = Math.sqrt(devolver);
        return devolver;
    }
}
