/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.carro;

import juego.principal.domain.juego.IdentificadorJuego;

import java.util.HashMap;
import java.util.Map;

public class Carro {
    protected Conductor conductor;
    protected Integer avance;

    protected IdentificadorJuego identificadorJuego;
    private final Map<CarroId, Conductor> carros = new HashMap<>();

    public Carro() {
    }

    public Carro(Conductor conductor, Integer avance, IdentificadorJuego identificadorJuego) {
        this.conductor = conductor;
        this.avance = avance;
        this.identificadorJuego = identificadorJuego;
    }

    public void asignarConductor(CarroId carroId, Conductor conductor) {
        carros.put(carroId, conductor);
    }

    public Map<CarroId, Conductor> carros() {
        return carros;
    }

    public void getAvance(Integer avance) {
        this.avance = avance;
    }

    public Integer getCantidadCarros() {
        return carros.size();
    }

    public Conductor getConductor() {
        return conductor;
    }

    public Integer getAvance() {
        return avance;
    }
}
