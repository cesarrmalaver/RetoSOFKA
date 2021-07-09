/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.principal.domain.juego;

import juego.principal.domain.carro.Carro;
import juego.principal.domain.carro.CarroId;
import juego.principal.domain.carro.Conductor;
import juego.principal.domain.carro.Ubicacion;
import juego.principal.domain.pista.Carril;
import juego.principal.domain.pista.Pista;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Juego {

    public Juego() {
    }

    protected Pista pista;
    protected Map<JugadorId, Jugador> jugadores = new HashMap<>();
    protected ArrayList<Pista> pistas = new ArrayList<>();
    protected ArrayList<Carro> carrosActivos = new ArrayList<>();
    protected ArrayList<Carril> carrilesActivos = new ArrayList<>();
    protected Boolean juegoActivo;
    protected Boolean primeraCarrera = true;

    protected Podio podio = new Podio();
    private final Carro carro = new Carro();

    public void crearJugador(JugadorId jugadorId, Nombre nombre) {
        Jugador jugador = new Jugador(nombre,0);
        jugadores.put(jugadorId, jugador);
        iniciarConductor(nombre);
    }

    public void iniciarConductor(Nombre nombre) {
        UUID id;
        Conductor conductor = new Conductor(nombre.getNombre());
        id = UUID.randomUUID();
        CarroId carroId = new CarroId(id);
        carro.asignarConductor(carroId, conductor);
    }

    public void iniciarPistas() {
        int largoPistaAleatorio;
        int cantidadDeCarriles = carro.getCantidadCarros();

        Scanner in = new Scanner(System.in);
        System.out.println("Cuantas opciones de pista desea ver? 1 - 9");
        while(!in.hasNextInt()) in.next();
        int cantidadPistas = in.nextInt();

        for (int i = 0; i < cantidadPistas; i++) {
            largoPistaAleatorio = (int) (Math.random() * 100 + 1);
            Pista pista = new Pista(largoPistaAleatorio, cantidadDeCarriles);
            pistas.add(pista);
        }
    }

    public void asignarPrimerLugar(JugadorId jugadorId) {
        jugadores.get(jugadorId).gano();
        podio.asignarPrimerLugar(jugadores.get(jugadorId));
        System.out.println("El primer lugar es para " + jugadores.get(jugadorId).nombre().getNombre());

        System.out.println(jugadores.get(jugadorId).vecesPrimero());
        podio.asignarAcumuladoGanador(jugadores.get(jugadorId).vecesPrimero());

    }

    public void asignarSegundoLugar(JugadorId jugadorId) {
        podio.asignarSegundoLugar(jugadores.get(jugadorId));
        System.out.println("El segundo lugar es para " + jugadores.get(jugadorId).nombre().getNombre());
    }

    public void asignarTercerLugar(JugadorId jugadorId) {
        podio.asignarTercerLugar(jugadores.get(jugadorId));
        System.out.println("El tercer lugar es para " + jugadores.get(jugadorId).nombre().getNombre());
    }

    public void iniciarCarrera() {
        UUID id;
        id = UUID.randomUUID();
        IdentificadorJuego identificadorJuego = new IdentificadorJuego(id);

        Scanner in = new Scanner(System.in);
        System.out.println("Pistas: ");
        int contadorPista = 1;
        for (Pista p : pistas) {
            System.out.println(contadorPista + "." + "Esta pista tiene " + p.getLargoPista()+ " kilometros y " + p.getCantidadCarriles()+ " carriles");
            contadorPista++;
        }
        System.out.println("Elija el numero de la pista que desea usar");
        while(!in.hasNextInt()) in.next();
        int pistaSeleccionada = in.nextInt();

        carro.carros().forEach((key, value) -> {
            Carro carrosJuego = new Carro(value, 0, identificadorJuego);
            carrosActivos.add(carrosJuego);

            int distanciaEnMetros = pistas.get(pistaSeleccionada - 1).getLargoPista() * 1000;
            Ubicacion ubicacion = new Ubicacion(0, distanciaEnMetros);
            Carril carriles = new Carril(key, identificadorJuego, ubicacion, distanciaEnMetros, false);
            carrilesActivos.add(carriles);
        });

        juegoActivo = true;
        Conductor conductor = new Conductor();

        while (juegoActivo) {
            int contadorTurnos = 0;
            System.out.println("--------ACTUALIZACIÒN----- " );
            System.out.println("--------- Meta: " + carrilesActivos.get(contadorTurnos).getDistanciaMetros() + " metros");
            for (Carro carros : carrosActivos) {

                if (!hayGanador(carros.getConductor().nombre())) {
                    int mover = conductor.tirarDado() * 100;
                    carros.getAvance(carros.getAvance() + mover);
                    carrilesActivos.get(contadorTurnos).avanzarCarro(carrilesActivos.get(contadorTurnos).getUbicacion(), mover);
                    System.out.println(carros.getConductor().nombre() + ":" + " corre : " + mover + " metros, nueva ubicacion: " + carros.getAvance());


                    if (carrilesActivos.get(contadorTurnos).getMetaAlcanzada()) {
                        if (podio.primerLugar() == null) {
                            asignarPrimerLugar(jugadorID(carros.getConductor().nombre()));
                        } else if (podio.segundoLugar() == null) {
                            asignarSegundoLugar(jugadorID(carros.getConductor().nombre()));
                        } else if (podio.tercerLugar() == null) {
                            asignarTercerLugar(jugadorID(carros.getConductor().nombre()));
                        }
                    }
                }
                contadorTurnos++;
            }
            if (podio.podioCompleto()) {
                break;
            }
        }

        imprimirPodio();
        persistirDatos();
        repetirCarrera();
    }

    public JugadorId jugadorID(String nombre) {
        JugadorId lookId = null;
        for (JugadorId keys : jugadores.keySet()) {
            if (jugadores.get(keys).nombre().getNombre().equals(nombre)) {
                lookId = keys;
            }
        }
        return lookId;
    }

    public Boolean hayGanador(String nombre) {
        boolean hayGanador = podio.tercerLugar() == jugadores.get(jugadorID(nombre))
                || podio.primerLugar() == jugadores.get(jugadorID(nombre))
                || podio.segundoLugar() == jugadores.get(jugadorID(nombre));
        return hayGanador;
    }

    public void repetirCarrera() {
        Scanner in = new Scanner(System.in);
        System.out.println("¿Jugar nuevamente?  Y/N");
        while (!in.hasNext("[yYnN]")) {
            System.out.println("Solo se puede poner una de las siguientes opciones y, Y, n, N");
            in.next();
        }
        String jugarDeNuevo = in.next();
        if (jugarDeNuevo.equals("Y") || jugarDeNuevo.equals("y")) {
            carrosActivos.clear();
            carrilesActivos.clear();
            Podio podioNuevo = new Podio();
            podio = podioNuevo;
            iniciarCarrera();
        }else if(jugarDeNuevo.equals("N") || jugarDeNuevo.equals("n")){
            System.out.println("Los datos persisten en el archivo datos.txt");
            System.out.println("GRACIAS POR JUGAR! REGRESA PRONTO");
        }
    }

    public void imprimirPodio() {
        System.out.println("++++++++++INICIO PODIO++++++++++");
        System.out.println("Primer lugar:  " + podio.primerLugar().nombre().getNombre());
        System.out.println("Segundo lugar:  " + podio.segundoLugar().nombre().getNombre());
        System.out.println("Tercer lugar:  " + podio.tercerLugar().nombre().getNombre());
        System.out.println("El "+podio.primerLugar().nombre().getNombre() + " lleva " + podio.primerLugar().vecesPrimero() + " victorias");
        System.out.println("++++++++++FIN PODIO++++++++++");
        System.out.println();
        }

    public void persistirDatos(){
       // System.out.println("ENtro a persistir los datos");
        FileWriter fichero = null;
        PrintWriter pw;
        try
        {
            fichero = new FileWriter("src/main/java/juego/datos.txt",true);
            pw = new PrintWriter(fichero);
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            if(primeraCarrera){
                pw.println("############INICIANDO PARTIDA############");
                pw.println(timeStamp);
            }
            pw.println("********Podio********");
            pw.println("Primer Lugar:  " + podio.primerLugar().nombre().getNombre());
            pw.println("Segundo Lugar:  " + podio.segundoLugar().nombre().getNombre());
            pw.println("Tercer Lugar:  " + podio.tercerLugar().nombre().getNombre());
            pw.println("El "+podio.primerLugar().nombre().getNombre() + " lleva " + podio.primerLugar().vecesPrimero() + " victorias");
            pw.println("*********************");
            primeraCarrera=false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
