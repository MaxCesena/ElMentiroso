package baraja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Monton {
    private List<Carta> cartas;

    public Monton() {
        this.cartas = new ArrayList<>();
    }

    public void agregarCartas(List<Carta> nuevasCartas) {
        cartas.addAll(nuevasCartas);
    }

    public List<Carta> getCartas() {
        return new ArrayList<>(cartas);
    }

    // Método para vaciar el montón (limpiar las cartas)
    public void vaciar() {
        cartas.clear();
    }

    // Método para eliminar cartas específicas del montón
    public void removerCartas(List<Carta> cartasARemover) {
        cartas.removeAll(cartasARemover);
    }

    public int contarCartas() {
        return cartas.size();
    }

    public void mostrarCartasBocaAbajo(String rutaImagen) {
        ImageIcon icono = new ImageIcon(rutaImagen);
        JOptionPane.showMessageDialog(null, new JLabel(icono), "Montón", JOptionPane.PLAIN_MESSAGE);
    }
}