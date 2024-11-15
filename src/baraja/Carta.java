package baraja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Carta {
    private String valor;
    private String palo;
    private String rutaImagen;

    // Constructor para usar el valor numérico, palo y ruta de la imagen
    public Carta(String palo, int valorNumerico, String rutaImagen) {
        this.palo = palo;
        this.valor = String.valueOf(valorNumerico);  // El valor numérico se guarda como cadena
        this.rutaImagen = rutaImagen;
    }

    // Métodos de acceso y pintura de cartas
    public void pintarCarta(Graphics g, int x, int y, int ancho, int alto, JPanel panel) {
        // Usar la ruta de la imagen
        ImageIcon iconoCarta = new ImageIcon(rutaImagen);
        g.drawImage(iconoCarta.getImage(), x, y, ancho, alto, panel);
    }

    // Getters y otros métodos
    public String getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}

