/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.juego;

import java.util.UUID;


public class IdentificadorJuego {

    private UUID id;

    public IdentificadorJuego(UUID id) {
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }

}
