/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.pista;

import juego.principal.domain.carro.CarroId;
import juego.principal.domain.carro.Ubicacion;
import juego.principal.domain.juego.IdentificadorJuego;

public class Carril {

    protected CarroId carroId;
    protected IdentificadorJuego identificadorJuego;
    protected Ubicacion ubicacion;
    protected Integer metros;
    protected Boolean metaAlcanzada;

    public Carril(CarroId carroId, IdentificadorJuego identificadorJuego, Ubicacion ubicacion, Integer metros, Boolean metaAlcanzada) {
        this.carroId = carroId;
        this.identificadorJuego = identificadorJuego;
        this.ubicacion = ubicacion;
        this.metros = metros;
        this.metaAlcanzada = metaAlcanzada;
    }

    public void terminarRecorrido() {
        if (getPosicionActual() >= getPosicionDeseada()) {
            metaAlcanzada = true;
        }
    }

    public void avanzarCarro(Ubicacion ubicacion, Integer cantidad) {
        this.ubicacion = ubicacion;
        ubicacion.setActual(ubicacion.getActual() + cantidad);
        terminarRecorrido();
    }

    public Integer getDistanciaMetros() {
        return metros;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public Integer getPosicionActual() {
        return ubicacion.getActual();
    }

    public Integer getPosicionDeseada() {
        return ubicacion.getLlegada();
    }

    public Boolean getMetaAlcanzada() {
        return metaAlcanzada;
    }
}
