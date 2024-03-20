import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class KmeansTest {
    //Atributos
    Kmeans k;
    @BeforeEach
    void before(){
        k = new Kmeans(3,15,2004);
    }
}