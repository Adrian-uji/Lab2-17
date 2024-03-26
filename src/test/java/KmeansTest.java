import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


class KmeansTest {
    //Atributos
    Kmeans k;
    @BeforeEach
    void before() throws IOException, Kmeans.IndiceFueraDeRango {
        CSV csv = new CSV();
        Table T = csv.readTable("src/main/java/Prueba.txt");
        k = new Kmeans(2,15,2004);
        k.train(T);
    }

    @Test
    void ComprobarGrupos() throws Kmeans.IndiceFueraDeRango {
        List<Double> l1 = new ArrayList<>();
        l1.add(900.0);l1.add(900.0);l1.add(900.0);
        System.out.println("Comprobamos (con la misma semilla siempre) varias opciones para el estimate:\nValor[900,900,900]\nEsperado -> 1\nObtenido -> " + k.estimate(l1));
        assertEquals(1,k.estimate(l1),1);

    }
}