/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.juego;


public class Jugador {

    private Nombre nombre;
    //prueba veces primero
    private Integer vecesPrimero=0;

    public Jugador(Nombre nombre, Integer vecesPrimero) {
        this.nombre = nombre;
        this.vecesPrimero = vecesPrimero;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Integer vecesPrimero() {
        return vecesPrimero;
    }

    public void gano(){
        vecesPrimero++;
    }
}
