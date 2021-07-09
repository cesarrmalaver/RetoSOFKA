/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.juego;

public class Podio {

    private Jugador primerLugar;
    private Jugador segundoLugar;
    private Jugador tercerLugar;
    private Integer acumuladoGanador;

    public Podio() {
    }

    public void asignarPrimerLugar(Jugador jugador) {
        primerLugar = jugador;
    }

    public void asignarSegundoLugar(Jugador jugador) {
        segundoLugar = jugador;
    }

    public void asignarTercerLugar(Jugador jugador) {
        tercerLugar = jugador;
    }

    public void asignarAcumuladoGanador(Integer acumuladoGanador){
        this.acumuladoGanador = acumuladoGanador;
    }

    public Jugador primerLugar() {
        return primerLugar;
    }

    public Jugador segundoLugar() {
        return segundoLugar;
    }

    public Jugador tercerLugar() {
        return tercerLugar;
    }

    public Boolean podioCompleto() {
        Boolean podioCompleto = false;
        if (primerLugar != null && segundoLugar != null && tercerLugar != null) {
            podioCompleto = true;
        }
        return podioCompleto;
    }

}
