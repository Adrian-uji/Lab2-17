import java.util.*;

public class RecSys{
    //Atributos
    private Algorithm algorithm;
    Map<String, Integer> recomandaciones = new HashMap<>();


    //Constructores
    public RecSys(Algorithm algorithm){
        this.algorithm = algorithm;
    }

    //Métodos
    public void train(Table trainData) throws Kmeans.IndiceFueraDeRango {
        algorithm.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames) throws Kmeans.IndiceFueraDeRango {
        for (int i = 0; i<testData.getSize(); i++){
            List<Double> list = testData.getRowAt(i).getData();
            int estimation = (int) algorithm.estimate(list);
            recomandaciones.put(testItemNames.get(i),estimation);
        }
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) throws IllegalArgumentException {
        if (!recomandaciones.containsKey(nameLikedItem) || numRecommendations < 0){
            throw new IllegalArgumentException();
        }
        if(numRecommendations == 0){return new ArrayList<>();}
        int label = recomandaciones.get(nameLikedItem);
        List<String> rec = new ArrayList<>();
        for (String clave : recomandaciones.keySet()) {
            if (label == recomandaciones.get(clave) && !clave.equals(nameLikedItem)) {
                rec.add(clave);
                if (rec.size() == numRecommendations) {
                    return rec;
                }
            }
        }
        return rec;
    }

}
