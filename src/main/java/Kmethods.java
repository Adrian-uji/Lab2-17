import java.util.List;

public class Kmethods {
    public Kmethods(){

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
