package baraja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

class JuegoMentiroso {
    private List<Jugador> jugadores;
    private int turnoActual;
    private Monton monton;
    private String rutaCartas;
    private int turnosEnRonda;
    private String paloActivo;
    private Jugador jugadorAnterior;
    private boolean primerTurno;

    public JuegoMentiroso(List<Jugador> jugadores, String rutaCartas) {
        this.jugadores = jugadores;
        this.turnoActual = 0;
        this.monton = new Monton();
        this.rutaCartas = rutaCartas;
        this.turnosEnRonda = 0;
        this.jugadorAnterior = null;
        this.primerTurno = true;
        repartirCartas();
        elegirPaloAleatorio();
    }

    public void repartirCartas() {
        List<Carta> baraja = crearBaraja();
        Collections.shuffle(baraja);
        int numJugadores = jugadores.size();
        for (int i = 0; i < baraja.size(); i++) {
            jugadores.get(i % numJugadores).agregarCarta(baraja.get(i));
        }
    }

    private void elegirPaloAleatorio() {
        String[] palos = {"BASTO", "COPA", "ESPADA", "ORO"};
        paloActivo = palos[(int) (Math.random() * palos.length)];
    }

    public boolean esPrimerTurno() {
        return primerTurno;
    }


    private List<Carta> crearBaraja() {
        List<Carta> baraja = new ArrayList<>();
        String[] palos = {"BASTO", "COPA", "ESPADA", "ORO"};
        String rutaBase = "C:\\Users\\maxhu\\IdeaProjects\\P4\\cartas";  // Suponiendo que `rutaCartas` contiene la ruta base de las imágenes

        for (String palo : palos) {
            for (int i = 1; i <= 12; i++) {
                // Se omite la carta 8 y 9 en la baraja española
                if (i != 8 && i != 9) {
                    String rutaImagen = rutaBase + "\\" + i + " de " + palo + ".png";  // Suponiendo un formato de nombre de imagen
                    baraja.add(new Carta(palo, i, rutaImagen));
                }
            }
        }
        return baraja;
    }

    public boolean verificarMentira(List<Carta> cartasJugadas) {
        for (Carta carta : cartasJugadas) {
            if (!carta.getPalo().equals(paloActivo)) {
                return true;  // El jugador mintió
            }
        }
        return false;  // El jugador dijo la verdad
    }

    public void manejarJuicio(boolean creeQueMiente) {
        boolean mentia = verificarMentira(monton.getCartas());

        if (creeQueMiente && mentia) {
            // El jugador anterior mintió y el juicio fue correcto
            jugadorAnterior.agregarCartas(monton.getCartas());
            monton.vaciar();
            JOptionPane.showMessageDialog(null, jugadorAnterior.getNombre() + " mentía. Se lleva el montón.");
        } else if (creeQueMiente && !mentia) {
            // El jugador anterior no mentía y el juicio fue incorrecto
            getJugadorActual().agregarCartas(monton.getCartas());
            monton.vaciar();
            JOptionPane.showMessageDialog(null, getJugadorActual().getNombre() + " se equivocó. Se lleva el montón.");
        }
        pasarTurno();
    }

    public void pasarTurno() {
        jugadorAnterior = jugadores.get(turnoActual);
        turnoActual = (turnoActual + 1) % jugadores.size();
        System.out.println("Turno actual: " + turnoActual + ", Jugador: " + getJugadorActual().getNombre());

        if (primerTurno) {
            primerTurno = false;
        }

        turnosEnRonda++;
        if (turnosEnRonda == jugadores.size()) {
            turnosEnRonda = 0;
            elegirPaloAleatorio();
        }



        // Verifica si el juego ha terminado
        if (verificarFinDelJuego()) {
            return;  // Si el juego ha terminado, no continúa con el turno

        }
    }



    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public Jugador getJugadorAnterior() {
        return jugadorAnterior;
    }

    public String getPaloActivo() {
        return paloActivo;
    }

    public Monton getMonton() {
        return monton;
    }

    public void agregarCartasAlMonton(List<Carta> cartas) {
        monton.agregarCartas(cartas);
    }

    private void mostrarPosiciones(List<Jugador> jugadoresSinCartas) {
        StringBuilder mensaje = new StringBuilder("El juego ha terminado. Posiciones finales:\n");

        // Ordena los jugadores sin cartas en orden de eliminación
        for (int i = 0; i < jugadoresSinCartas.size(); i++) {
            mensaje.append((i + 1)).append("º lugar: ").append(jugadoresSinCartas.get(i).getNombre()).append("\n");
        }

        // El jugador que quedó con cartas está en la última posición
        for (Jugador jugador : jugadores) {
            if (jugador.tieneCartas()) {
                mensaje.append(jugadores.size()).append("º lugar: ").append(jugador.getNombre()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
        System.exit(0);  // Finaliza el juego
    }


    public boolean verificarFinDelJuego() {
        List<Jugador> jugadoresSinCartas = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            if (!jugador.tieneCartas()) {
                jugadoresSinCartas.add(jugador);
            }
        }

        // Si solo queda un jugador con cartas o si todos menos uno se quedan sin cartas, el juego termina
        if (jugadoresSinCartas.size() == jugadores.size() - 1 || jugadoresSinCartas.size() == jugadores.size()) {
            mostrarPosiciones(jugadoresSinCartas);
            return true;  // Indica que el juego ha terminado
        }

        return false;  // El juego continúa
    }
}
