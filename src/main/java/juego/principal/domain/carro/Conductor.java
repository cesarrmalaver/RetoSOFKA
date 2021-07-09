/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.carro;


public class Conductor {

    private String nombre;

    public Conductor() {}

    public Conductor(String nombre) {
        this.nombre = nombre;
    }

    public String nombre() {
        return nombre;
    }

    public Integer tirarDado() {
        int resultadoAleatorio = (int) (Math.random() * 6 + 1);
        return resultadoAleatorio;
    }
}
