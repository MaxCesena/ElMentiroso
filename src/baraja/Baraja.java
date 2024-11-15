package baraja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;





    public void barajar() {
        Collections.shuffle(cartas);
    }

    public List<Carta> repartir(int numCartas) {
        List<Carta> mano = new ArrayList<>(cartas.subList(0, numCartas));
        cartas.subList(0, numCartas).clear();
        return mano;
    }
}
