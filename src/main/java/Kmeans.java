import java.util.*;

public class Kmeans extends oneNearestNeighbor{
    //Atributos
    int numClusters;
    int numIterations;
    long seed;

    //Constructor
    public Kmeans (int numClusters, int numIterations, long seed){
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
    }

    //Métodos
    //@Override
    public void train(Table datos){
        //Creamos una lista de tres representantes aleatorios
        List<List<Double>> listaCluster = getRandomCluster(datos);

        //Creamos un vector que cada dato corresponda a una iteración y que se le asigna el grupo al que corresponde
        int[] vectorGrupos = new int[datos.getSize()];
        for (int i = 0; i < numIterations; i++){
            for (int j = 0; j<datos.getSize(); j++){
                vectorGrupos[j] = estimate(datos.getRowAt(j).getData(),listaCluster);
            }
            //Termina una iteración
            for (int j = 0; j<listaCluster.size(); j++){
                listaCluster.set(i,actualizarCluster(vectorGrupos,datos,j));
            }
        }
    }
    //Métodos auxiliares de train
    public List<List<Double>> getRandomCluster(Table datos){
        List<Integer> listaRepresentante = new ArrayList<>();
        Random random = new Random(2004);
        for (int i = 0; i<numClusters; i++) {
            int posibleRepresentante = random.nextInt(datos.getSize());
            while (listaRepresentante.contains(posibleRepresentante)) {
                posibleRepresentante = random.nextInt(datos.getSize());
            }
            listaRepresentante.add(posibleRepresentante);
        }
        List<List<Double>> listaCluster = new ArrayList<>();
        for (int i = 0; i<listaRepresentante.size(); i++){
            listaCluster.add(datos.getRowAt(listaRepresentante.get(i)).getData());
        }
        return listaCluster;
    }
    public List<Double> actualizarCluster(int[] vectorGrupos, Table datos, int grupo){
        int contador = 0;
        double[] nuevoCluster = new double[datos.getColumnSize()];
        for (int i = 0; i<vectorGrupos.length; i++){
            if (vectorGrupos[i] == grupo){
                List<Double> aux = datos.getRowAt(i).getData();
                for (int j = 0; j<nuevoCluster.length; j++){
                    nuevoCluster[j] += aux.get(j);
                }
                contador++;
            }
        }
        List<Double> listaCluster = new ArrayList<>();
        for (int i = 0; i<nuevoCluster.length;i++){
            listaCluster.add(nuevoCluster[i]/contador);
        }
        return listaCluster;
    }
    //@Override
    public Integer estimate (List<Double> dato, List<List<Double>> listaCluster){
        double minData = euclidea(dato,listaCluster.get(0));
        int min = 0;
        for (int i = 1; i<listaCluster.size(); i++){
            double posibleMin = euclidea(dato, listaCluster.get(i));
            if (posibleMin<minData){
                minData = posibleMin;
                min = i;
            }
        }
        return min;
    }

}