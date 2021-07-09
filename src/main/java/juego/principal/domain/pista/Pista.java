/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.pista;

public class Pista {

    public Pista(Integer largoPista, Integer cantidadDeCarriles) {
        this.largoPista = largoPista;
        this.cantidadCarriles = cantidadDeCarriles;
    }

    private Integer largoPista;
    private Integer cantidadCarriles;

    public Integer getLargoPista() {
        return largoPista;
    }

    public Integer getCantidadCarriles() {
        return cantidadCarriles;
    }

}
