package juego;

import juego.principal.domain.juego.Juego;
import juego.principal.domain.juego.JugadorId;
import juego.principal.domain.juego.Nombre;

import java.util.Scanner;
import java.util.UUID;

public class CarrosAleatorios {

    public static void main(String[] args) {
        UUID id;
        int cantidadJugadores;
        String nombreJugador;

        Juego nuevoJuego = new Juego();

        Scanner in = new Scanner(System.in);
        System.out.println("Cada jugador se convertirá en conductor y se le asignarà un carro.");
        System.out.println("Deben ser minimo 3 jugadores para llenar el podio");
        System.out.println("Ingrese la cantidad de jugadores: ");

         while(!in.hasNextInt()) in.next();         
        cantidadJugadores = in.nextInt();

        for (int i = 0; i < cantidadJugadores; i++) {
            id = UUID.randomUUID();
            JugadorId jugadorId = new JugadorId(id);
            nombreJugador = "Jugador" + (i + 1);
            Nombre nombre = new Nombre(nombreJugador);
            nuevoJuego.crearJugador(jugadorId, nombre);
        }

        nuevoJuego.iniciarPistas();
        nuevoJuego.iniciarCarrera();
    }
}
