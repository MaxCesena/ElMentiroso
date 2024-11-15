package baraja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Visual extends JPanel {
    private List<Carta> cartasSeleccionadas;
    private Jugador jugadorActual;
    private JuegoMentiroso juego;
    private String rutaImagenBocaAbajo;

    public Visual(Jugador jugador, JuegoMentiroso juego, String rutaImagenBocaAbajo) {
        this.jugadorActual = jugador;
        this.juego = juego;
        this.rutaImagenBocaAbajo = rutaImagenBocaAbajo;
        this.cartasSeleccionadas = new ArrayList<>();
        setLayout(new FlowLayout());
        actualizarMano(jugador);
    }

    public void actualizarMano(Jugador jugador) {
        this.jugadorActual = jugador;
        removeAll();

        for (Carta carta : jugador.getMano()) {
            ImageIcon iconoCarta = new ImageIcon(carta.getRutaImagen());
            JButton botonCarta = new JButton(iconoCarta);
            botonCarta.setPreferredSize(new Dimension(100, 150));
            botonCarta.setIcon(new ImageIcon(iconoCarta.getImage().getScaledInstance(90, 140, Image.SCALE_SMOOTH)));
            botonCarta.addActionListener(e -> seleccionarCarta(carta, botonCarta));
            add(botonCarta);
        }

        revalidate();
        repaint();
    }

    private void seleccionarCarta(Carta carta, JButton boton) {
        if (cartasSeleccionadas.contains(carta)) {
            JOptionPane.showMessageDialog(this, "Error: No puedes seleccionar la misma carta dos veces.");
        } else if (cartasSeleccionadas.size() < 3) {
            cartasSeleccionadas.add(carta);
            boton.setEnabled(false);
            System.out.println("Carta seleccionada: " + carta.getPalo() + " " + carta.getValor());
        } else {
            JOptionPane.showMessageDialog(this, "Error: Solo puedes seleccionar hasta 3 cartas.");
        }
    }

    public boolean agregarCartasAlMonton() {
        if (cartasSeleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Debes seleccionar al menos una carta.");
            return false;
        } else {
            juego.agregarCartasAlMonton(cartasSeleccionadas);
            jugadorActual.removerCartas(cartasSeleccionadas);
            cartasSeleccionadas.clear();
            JOptionPane.showMessageDialog(this, "Cartas agregadas al montón. Cartas restantes: " + jugadorActual.getMano().size() + "\n Cierra los ojos " + jugadorActual.getNombre());
            return true;
        }

    }


    public void mostrarDecisionJuicio() {
        actualizarMano(juego.getJugadorActual());
        int decision = JOptionPane.showConfirmDialog(this, "Te toca " + jugadorActual.getNombre() + ", ¿Crees que " + juego.getJugadorAnterior().getNombre() + " mintió?", "Decisión de Juicio", JOptionPane.YES_NO_OPTION);
        boolean creeQueMiente = (decision == JOptionPane.YES_OPTION);
        juego.manejarJuicio(creeQueMiente);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Es el turno de: " + juego.getJugadorActual().getNombre(), 10, 500);
        g.drawString("Palo Activo: " + juego.getPaloActivo(), 10, 400);  // Mostrar el palo activo

        int x = 10;
        int anchoCarta = 100;
        int altoCarta = 150;
        int separacion = 10;

        // Pintar el montón de cartas boca abajo
        if (!juego.getMonton().getCartas().isEmpty()) {
            g.drawImage(new ImageIcon(rutaImagenBocaAbajo).getImage(), 10, 200, anchoCarta, altoCarta, this);
        }
    }
}
