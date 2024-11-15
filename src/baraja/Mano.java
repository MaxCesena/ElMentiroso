package baraja;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private List<Carta> montonCentral;

    public Mano() {
        montonCentral = new ArrayList<>();
    }

    public void agregarCartasAlMonton(List<Carta> cartas) {
        montonCentral.addAll(cartas);
    }

    public List<Carta> obtenerCartasDelMonton() {
        List<Carta> cartas = new ArrayList<>(montonCentral);
        montonCentral.clear();
        return cartas;
    }

    public void limpiarMonton() {
        montonCentral.clear();
    }
}
