import java.util.*;

public class Kmeans extends Kmethods implements Algorithm<Table,Integer,List<Double>> {
    //Atributos
    int numClusters;
    int numIterations;
    long seed;
    List<List<Double>> listaCluster; //Esta es una lista que contiene cada uno de los representantes de cada grupo

    //Constructores
    public Kmeans() {
        numClusters = 3;
        numIterations = 10;
        seed = 1000;
    }

    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
    }

    //Métodos
    @Override
    public void train(Table datos) throws IndiceFueraDeRango {
        if (datos.getSize()<numClusters){
            throw new IndiceFueraDeRango("Error, la tabla es más pequeña que el número de representantes escogido " + numClusters);
        }
        //Creamos una lista de tres representantes aleatorios
        listaCluster = getRandomCluster(datos, this.seed);

        //Creamos un vector que cada dato corresponda a una iteración y que se le asigna el grupo al que corresponde
        int[] vectorGrupos = new int[datos.getSize()];
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < datos.getSize(); j++) {
                vectorGrupos[j] = estimate(datos.getRowAt(j).getData());
            }
            //Termina una iteración
            for (int j = 0; j < listaCluster.size(); j++) {
                listaCluster.set(j, actualizarCluster(vectorGrupos, datos, j));
            }
        }
    }

    //Métodos auxiliares de train
    private List<List<Double>> getRandomCluster(Table datos, long seed) {
        List<Integer> listaRepresentante = new ArrayList<>();
        Random random = new Random(seed); //Ponemos una seed para que siempre sea el mismo dato
        for (int i = 0; i < numClusters; i++) {
            int posibleRepresentante = random.nextInt(datos.getSize());
            while (listaRepresentante.contains(posibleRepresentante)) {
                posibleRepresentante = random.nextInt(datos.getSize());
            }
            listaRepresentante.add(posibleRepresentante);
        }
        List<List<Double>> listaCluster = new ArrayList<>();
        for (int i = 0; i < listaRepresentante.size(); i++) {
            listaCluster.add(datos.getRowAt(listaRepresentante.get(i)).getData());
        }
        return listaCluster;
    }

    private List<Double> actualizarCluster(int[] vectorGrupos, Table datos, int grupo) {
        int contador = 0;
        double[] nuevoCluster = new double[datos.getColumnSize()];
        for (int i = 0; i < vectorGrupos.length; i++) {
            if (vectorGrupos[i] == grupo) {
                List<Double> aux = datos.getRowAt(i).getData();
                for (int j = 0; j < nuevoCluster.length; j++) {
                    nuevoCluster[j] += aux.get(j);
                }
                contador++;
            }
        }
        List<Double> listaCluster = new ArrayList<>();
        for (int i = 0; i < nuevoCluster.length; i++) {
            listaCluster.add(nuevoCluster[i] / contador);
        }
        return listaCluster;
    }

    @Override
    public Integer estimate(List<Double> dato) throws IndiceFueraDeRango {
        if (dato.size() != listaCluster.get(0).size()){
            throw new IndiceFueraDeRango("Error, la lista pasada y la lista de nuestra base de datos tienen tamaños diferentes");
        }
        double minData = euclidea(dato, listaCluster.get(0));
        int min = 0;
        for (int i = 1; i < listaCluster.size(); i++) {
            double posibleMin = euclidea(dato, listaCluster.get(i));
            if (posibleMin < minData) {
                minData = posibleMin;
                min = i;
            }
        }
        return min;
    }

    public class IndiceFueraDeRango extends Exception{
        public IndiceFueraDeRango(String mensaje){
            super(mensaje);
        }
    }
}