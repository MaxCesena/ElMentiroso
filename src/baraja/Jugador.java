package baraja;

import java.util.ArrayList;
import java.util.List;

class Jugador {
    private String nombre;
    private List<Carta> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }




    public List<Carta> getMano() {
        return mano;
    }

    public void agregarCarta(Carta carta) {
        mano.add(carta);
    }

    // Método para agregar varias cartas a la mano del jugador
    public void agregarCartas(List<Carta> cartas) {
        mano.addAll(cartas);  // Agrega todas las cartas de la lista a la mano
    }

    public void removerCartas(List<Carta> cartas) {
        mano.removeAll(cartas);
    }

    // Método que permite remover cartas de la mano del jugador
    public List<Carta> removerCartasDos(List<Carta> cartas) {
        List<Carta> cartasRemovidas = new ArrayList<>();
        for (Carta carta : cartas) {
            if (this.mano.contains(carta)) {
                this.mano.remove(carta);  // Elimina la carta de la mano del jugador
                cartasRemovidas.add(carta);  // Agrega la carta a la lista de cartas removidas
            }
        }
        return cartasRemovidas;
    }

    public boolean tieneCartas() {
        return !mano.isEmpty();
    }
}
