public interface Algorithm<T extends Table,U,V> {
    public void train(T tabla) throws Kmeans.IndiceFueraDeRango;
    public U estimate(V dato) throws Kmeans.IndiceFueraDeRango;
}
