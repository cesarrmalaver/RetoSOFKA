/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.carro;

public class Ubicacion {

    private Integer actual;
    private Integer llegada;

    public Integer getActual() {
        return actual;
    }

    public Ubicacion(Integer actual, Integer llegada) {
        this.actual = actual;
        this.llegada = llegada;
    }

    public Integer getLlegada() {
        return llegada;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }
}

