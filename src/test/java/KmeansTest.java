import org.junit.Assert;
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
        System.out.println("Comprobamos (con la misma semilla siempre) varias opciones para el estimate:\n");


        List<Double> l1 = new ArrayList<>();
        l1.add(900.0);l1.add(900.0);l1.add(900.0);
        System.out.println("Valor[900,900,900]\nEsperado -> 1\nObtenido -> " + k.estimate(l1)+ "\n");
        assertEquals(1,k.estimate(l1),1);


        List<Double> l2 = new ArrayList<>();
        l2.add(700.0);l2.add(700.0);l2.add(700.0);
        System.out.println("Valor[700,700,700]\nEsperado -> 1\nObtenido -> " + k.estimate(l2)+ "\n");
        assertEquals(1,k.estimate(l2),1);


        List<Double> l3 = new ArrayList<>();
        l3.add(800.0);l3.add(800.0);l3.add(800.0);
        System.out.println("Valor[800,800,800]\nEsperado -> 1\nObtenido -> " + k.estimate(l3) + "\n");
        assertEquals(1,k.estimate(l3),1);


        List<Double> l4 = new ArrayList<>();
        l4.add(150.0);l4.add(130.0);l4.add(220.0);
        System.out.println("Valor[150,130,220]\nEsperado -> 0\nObtenido -> " + k.estimate(l4)+ "\n");
        assertEquals(0,k.estimate(l4),1);


        List<Double> l5 = new ArrayList<>();
        l5.add(15.0);l5.add(100.0);l5.add(200.0);
        System.out.println("Valor[15,100,200]\nEsperado -> 0\nObtenido -> " + k.estimate(l5)+ "\n");
        assertEquals(0,k.estimate(l5),1);


        List<Double> l6 = new ArrayList<>();
        l6.add(10.0);l6.add(20.0);l6.add(30.0);
        System.out.println("Valor[10,20,30]\nEsperado -> 0\nObtenido -> " + k.estimate(l6) + "\n");
        assertEquals(0,k.estimate(l6),1);


        List<Double> l7 = new ArrayList<>();
        l7.add(750.0);l7.add(888.0);l7.add(777.0);
        System.out.println("Valor[750,888,777]\nEsperado -> 1\nObtenido -> " + k.estimate(l7)+ "\n");
        assertEquals(1,k.estimate(l7),1);


        List<Double> l8 = new ArrayList<>();
        l8.add(1.0);l8.add(100.0);l8.add(50.0);
        System.out.println("Valor[1,100,50]\nEsperado -> 1\nObtenido -> " + k.estimate(l8) + "\n");
        assertEquals(0,k.estimate(l8),1);


        List<Double> l9 = new ArrayList<>();
        l9.add(730.0);l9.add(789.0);l9.add(803.0);
        System.out.println("Valor[730,789,803]\nEsperado -> 1\nObtenido -> " + k.estimate(l9) + "\n");
        assertEquals(1,k.estimate(l9),1);


        List<Double> l10 = new ArrayList<>();
        l10.add(42.0);l10.add(33.0);l10.add(14.0);
        System.out.println("Valor[42,33,14]\nEsperado -> 0\nObtenido -> " + k.estimate(l10)+ "\n");
        assertEquals(0,k.estimate(l10),1);


        List<Double> l11 = new ArrayList<>();
        l11.add(999.0);l11.add(999.0);l11.add(999.0);
        System.out.println("Valor[999,999,999]\nEsperado -> 1\nObtenido -> " + k.estimate(l11)+ "\n");
        assertEquals(1,k.estimate(l11),1);


        List<Double> l12 = new ArrayList<>();
        l12.add(143.0);l12.add(21.0);l12.add(230.0);
        System.out.println("Valor[143,21,230]\nEsperado -> 0\nObtenido -> " + k.estimate(l12) + "\n");
        assertEquals(0,k.estimate(l12),1);

    }
    @Test
    void ExcepcionesTest() throws Kmeans.IndiceFueraDeRango, IOException {

        CSV csv = new CSV();
        Table t1 = csv.readTable("src/main/resources/miles_dollars.csv");
        List<Double> l1 = new ArrayList<>();
        Kmeans k1 = new Kmeans(50000,5000,2000);
        Kmeans k2 = new Kmeans(5, 5, 2000);
        try {
            k1.train(t1);
            System.out.println("Debería hacer un error IndiceFueraDeRango");
            Assert.fail();
        }
        catch (Kmeans.IndiceFueraDeRango e){
            System.out.println(e);
        }

        try{
            k2.train(t1);
            k2.estimate(l1);
            System.out.println("Debería hacer un error IndiceFueraDeRango");
            Assert.fail();
        }catch(Kmeans.IndiceFueraDeRango a){
            System.out.println(a);
        }

    }
}