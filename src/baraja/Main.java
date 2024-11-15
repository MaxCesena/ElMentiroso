package baraja;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] opciones = {"2", "3", "4"};
        String seleccion = (String) JOptionPane.showInputDialog(
                null, "Selecciona el número de jugadores:",
                "Número de Jugadores", JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

        if (seleccion == null) {
            System.exit(0);
        }

        int numJugadores = Integer.parseInt(seleccion);
        if (numJugadores < 2 || numJugadores > 4) {
            JOptionPane.showMessageDialog(null, "El número de jugadores debe ser entre 2 y 4.");
            System.exit(0);
        }

        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }

        String rutaCartas = "C:\\Users\\maxhu\\IdeaProjects\\P4\\cartas";
        JuegoMentiroso juego = new JuegoMentiroso(jugadores, rutaCartas);

        JFrame frame = new JFrame("Juego de Mentiroso");
        Visual visualPanel = new Visual(jugadores.get(0), juego, rutaCartas + "\\BACK (1).png");
        frame.add(visualPanel);

        JButton botonAgregarAlMonton = new JButton("Agregar al Montón");
        juego.pasarTurno();
        botonAgregarAlMonton.addActionListener(e -> {
            if (visualPanel.agregarCartasAlMonton()) {
                if (!juego.esPrimerTurno()) {

                    visualPanel.mostrarDecisionJuicio();
                }
            }
        });

        frame.add(botonAgregarAlMonton, BorderLayout.SOUTH);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
