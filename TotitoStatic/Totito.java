import java.util.Random;
import java.util.Scanner;

public class Totito {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static int[][] matriz = new int[3][3];

    // pos cero es el boot pos 1 es el jugador
    public static int[][] matrizEstadisticas = new int[2][3];

    // *********** variables del boot
    public static int[][] matrizBoot = new int[3][3];
    public static int[][] matrizMejoresTirosYX = new int[9][2];

    // *********** Fin variables del boot

    public static void main(String[] args) {
        
        int opcionSeleccionada = 0;
        do {
            System.out.println("\n\nMenu\n");
            System.out.println("1) jugar");
            System.out.println("2) Estadisticas");
            System.out.println("3) Salir");
            opcionSeleccionada = solicitarNumero("Seleccione la opcion deseada:", 1, 3);
            switch (opcionSeleccionada) {
                case 1:
                    jugar();
                    break;
                case 2:
                    estadisticas();
                    break;
                default:
                    System.out.println("Gracias por jugar contra la computadora");
                    break;
            }
        } while (opcionSeleccionada != 3);
    }

    public static void estadisticas() {
        System.out.println("\n\nESTADISTICA");
        System.out.println("BOOT   Empatadas "+matrizEstadisticas[0][0]+" Ganadas "+matrizEstadisticas[0][1]+" Perdidas "+matrizEstadisticas[0][2]);
        System.out.println("USER   Empatadas "+matrizEstadisticas[1][0]+" Ganadas "+matrizEstadisticas[1][1]+" Perdidas "+matrizEstadisticas[1][2]);
        scanner.nextLine();
    }

    public static void asignarGanador(int ganador) {
        if (ganador == 0) {
            matrizEstadisticas[0][0]++;
            matrizEstadisticas[1][0]++;
        } else {
            if (ganador == 2) {
                matrizEstadisticas[0][1]++;
                matrizEstadisticas[1][2]++;
            } else {
                matrizEstadisticas[0][2]++;
                matrizEstadisticas[1][1]++;
            }
        }
    }

    public static void jugar() {
        int cantidadTirosPosibles = 9;
        limpiarTablero();
        boolean finDeJuego = false;
        int ganador = 0;
        System.out.println("\n\nSeleccionando al jugador inicial");
        System.out.println("De manera aleatoria");
        scanner.nextLine();
        boolean bootIniciaJugada = (generarNumeroRandom(0, 1)==1)?true:false;
        System.out.println("\n****Turno de "+(bootIniciaJugada?"BOOT X":"USUARIO Juega con las fichas \"O\""));
        scanner.nextLine();
        while (!finDeJuego) {
           // pintarTablero();
            System.out.println("\n\n\nTurno de "+(bootIniciaJugada?"BOOT con las fichas \"X\"":"USUARIO con las fichas \"O\""));
            if (bootIniciaJugada){
                turnoBoot(2);
            }
            else{
                turnoJugador(1);
            }
            bootIniciaJugada = !bootIniciaJugada;

            cantidadTirosPosibles--;
            // turnoJugador(1);
            // revisar que alguien haya ganado
            ganador = jugadorGanador();
            pintarTablero();
            if (ganador != 0) {
                pintarTablero();
                System.out.println("hay ganador, jugador " + ((ganador==2)?"BOOT X":"USUARIO O"));
                asignarGanador(ganador);
                finDeJuego = true;
            }
            else{
                if (cantidadTirosPosibles == 0) {
                    pintarTablero();
                    System.out.println("Nadie Gano ");
                    asignarGanador(0);
                    finDeJuego = true;
                }
            }
        }
    }

    public static void limpiarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j]=0;
            }
        }
    }

    /**
     * 
     * @return 0 = no hay ganador, 1 x, 2 o
     */
    public static int jugadorGanador() {
        int res = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != 0) {
                    // horizontal
                    if (revisarCasilla(i - 2, j, matriz[i][j]) && revisarCasilla(i - 1, j, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i + 2, j, matriz[i][j]) && revisarCasilla(i + 1, j, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    // vertical
                    if (revisarCasilla(i, j - 2, matriz[i][j]) && revisarCasilla(i, j - 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i, j + 2, matriz[i][j]) && revisarCasilla(i, j + 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    // diagonal
                    if (revisarCasilla(i + 2, j + 2, matriz[i][j]) && revisarCasilla(i + 1, j + 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i + 2, j - 2, matriz[i][j]) && revisarCasilla(i + 1, j - 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i - 2, j + 2, matriz[i][j]) && revisarCasilla(i - 1, j + 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i - 2, j - 2, matriz[i][j]) && revisarCasilla(i - 1, j - 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                }
            }
        }
        return res;
    }

    public static boolean revisarCasilla(int y, int x, int tipo) {
        boolean res = false;
        if ((x >= 0) && (y >= 0)) {
            if ((x < matriz[0].length) && (y < matriz.length)) {
                // la pos exista
                if (tipo == matriz[y][x]) {
                    res = true;
                }
            }
        }
        return res;
    }

    public static boolean posicionVacia(int x, int y) {
        if (matriz[y][x] != 0) {
            return false;
        }
        return true;
    }

    /*********** USUARIO JUGADOR ***********/
    public static void turnoJugador(int tipoFicha) {
        System.out.println("***** turno del usuario");
        pintarTablero();
        int posX = 0;
        int posY = 0;
        boolean seleccionoPosicionVacia = false;
        do {
            posX = solicitarNumero("Ingrese la coordenada en X", 0, 2);
            posY = solicitarNumero("Ingrese la coordenada en Y", 0, 2);
            seleccionoPosicionVacia = posicionVacia(posX, posY);
            if (!seleccionoPosicionVacia) {
                pintarTablero();
                System.out.println("\nla posicion seleccionada no esta vacía, intente de nuevo");
            }

        } while (!seleccionoPosicionVacia);
        matriz[posY][posX] = tipoFicha;
        pintarTablero();
        System.out.println("El jugador tiro. ");
        scanner.nextLine();
    }
    /*********** FIN USUARIO JUGADOR ***********/

    /*********** BOOT JUGADOR ***********/
    public static void turnoBoot(int tipoCasillaBoot) {
        bootValorarMatriz(tipoCasillaBoot);
        int cantMejoresTiros = bootMejorTiro();

        int y, x;
        if (cantMejoresTiros == 1) {
            y = matrizMejoresTirosYX[0][0];
            x = matrizMejoresTirosYX[0][1];
        } else {
            int tiroFinal = generarNumeroRandom(0, cantMejoresTiros - 1);
            y = matrizMejoresTirosYX[tiroFinal][0];
            x = matrizMejoresTirosYX[tiroFinal][1];
        }
        matriz[y][x] = tipoCasillaBoot;
        pintarTablero();
        System.out.println("El boot tira");
        System.out.println("Y " + y + " X " + x);
        scanner.nextLine();
    }

    public static int bootMejorTiro() {
        int cantidadMejoresTiros = 0;
        int maximo = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizBoot[i][j] > maximo) {
                    maximo = matrizBoot[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizBoot[i][j] == maximo) {
                    matrizMejoresTirosYX[cantidadMejoresTiros][0] = i;
                    matrizMejoresTirosYX[cantidadMejoresTiros][1] = j;
                    cantidadMejoresTiros++;
                }
            }
        }
        return cantidadMejoresTiros;
    }

    /**
     * Revisar todos los posibles tiros,
     * 
     * @param tipoCasillaBoot Este parametro recibe 1 si el boot tiene X, o 2 si el
     *                        boot es O
     */
    public static void bootValorarMatriz(int tipoCasillaBoot) {
        int valorPos = 0;
        int tmp = 0;
        for (int i = 0; i < 3; i++) {// filas
            for (int j = 0; j < 3; j++) { // columnas
                valorPos = 0;
                tmp = 0;
                if (matriz[i][j] == 0) {
                    // arriba
                    tmp = bootValorCasilla(j, i, 0, 1, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    // abajo
                    tmp = bootValorCasilla(j, i, 0, -1, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    // derecha
                    tmp = bootValorCasilla(j, i, 1, 0, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    // izquierda
                    tmp = bootValorCasilla(j, i, -1, 0, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    // arriba derecha
                    tmp = bootValorCasilla(j, i, 1, 1, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    // arriba izquierda
                    tmp = bootValorCasilla(j, i, -1, 1, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    // abajo derecha
                    tmp = bootValorCasilla(j, i, 1, -1, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    // abajo izquierda
                    tmp = bootValorCasilla(j, i, -1, -1, tipoCasillaBoot);
                    valorPos = (tmp > valorPos) ? tmp : valorPos;
                    matrizBoot[i][j] = valorPos;
                } else {
                    matrizBoot[i][j] = 0;
                }
            }
        }
    }

    public static int bootValorCasilla(int x, int y, int multX, int multY, int casillaPrincipal) {
        int res = 1;
        int x1 = multX + x;
        int y1 = multY + y;

        if (dentroRango(x1, y1)) {
            int x2 = multX + x1;
            int y2 = multY + y1;

            if (!dentroRango(x2, 1)) {
                x2 = x - 2 * multX;
            }
            if (!dentroRango(1, y2)) {
                y2 = x - 2 * multY;
            }
            if (dentroRango(x2, y2)) {
                int p1 = matriz[y1][x1];
                int p2 = matriz[y2][x2];
                if ((p1 == p2) && (p1 == 0)) {
                    // estan vacias
                    res = 2;
                } else {
                    // no estan vacias
                    if (p1 == p2) {
                        // son iguales
                        if (p1 == casillaPrincipal) {
                            // aca gano
                            res = 15;
                        } else {
                            // pierdo
                            res = 10;
                        }
                    } else {
                        if ((p1 == 0) || (p2 == 0)) {
                            if ((p1 == casillaPrincipal) || (p2 == casillaPrincipal)) {
                                // yo puedo ganar 2 turno despues
                                res = 5;
                            } else {
                                // jugador puede ganar 2 turno despues
                                res = 4;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public static boolean dentroRango(int x, int y) {
        boolean res = false;
        if ((x >= 0) && (y >= 0)) {
            if ((x < 3) && (y < 3)) {
                res = true;
            }
        }
        return res;
    }
    /*********** FIN BOOT JUGADOR ***********/

    /*********** PINTAR TABLERO ***********/
    public static void pintarTablero() {
        for (int i = 0; i < matriz.length; i++) {
            String linea0 = "";
            String linea1 = "";
            String linea2 = "";
            for (int j = 0; j < matriz[0].length; j++) {
                linea0 = linea0 + pintarFila(matriz[i][j], 0);
                linea1 = linea1 + pintarFila(matriz[i][j], 1);
                linea2 = linea2 + pintarFila(matriz[i][j], 2);
                if ((j == 0) || (j == 1)) {
                    linea0 = linea0 + " | ";
                    linea1 = linea1 + " | ";
                    linea2 = linea2 + " | ";
                }
            }
            System.out.println(linea0);
            System.out.println(linea1);
            System.out.println(linea2);
            if (i < (matriz[0].length - 1)) {
                for (int k = 0; k < linea2.length(); k++) {
                    System.out.print("-");
                }
                System.out.println();
            }

        }
    }

    /**
     * 
     * @param tipo 0 vacio, 1 x, 2 y
     * @param fila 0 fila 1, 1 fila 2, 2 fila 3
     * @return string con la cadena caracteres a pintar
     */
    public static String pintarFila(int tipo, int fila) {
        String res = "    ";
        if (tipo != 0) {
            if (tipo == 1) {
                if (fila == 1) {
                    res = " XX ";
                } else {
                    // podríamos validar
                    res = "X  X";
                }
            } else {
                if (tipo == 2) {
                    if (fila == 1) {
                        res = "O  O";
                    } else {
                        res = " OO ";
                    }
                }
            }
        }
        return res;
    }
    /*********** FIN PINTAR TABLERO ***********/

    /*********** FUNCION DE AYUYA ***********/
    /**
     * Random desde [min..max] inclusivo
     * @param min
     * @param max
     * @return
     */
    public static int generarNumeroRandom(int min, int max) {
        return random.nextInt((max+1) - min) + min;
    }

    public static int solicitarNumero(String mensaje, int limiteInferior, int limiteSuperior) {
        int numeroIngresado = 0;
        boolean hayErrorNumeroIngresado = false;
        do {
            try {

                System.out.println("\n" + mensaje);
                numeroIngresado = scanner.nextInt();
                if ((numeroIngresado >= limiteInferior) && (numeroIngresado <= limiteSuperior)) {
                    hayErrorNumeroIngresado = false;
                } else {
                    hayErrorNumeroIngresado = true;
                    System.out.println(
                            "Debe ingresar un número entre [" + limiteInferior + " . . " + limiteSuperior + "]");
                    System.out.println("Ingrese de nuevo.");
                }
            } catch (Exception e) {
                hayErrorNumeroIngresado = true;
                System.out.println(
                        "Debe ingresar un número entre [" + limiteInferior + " . . " + limiteSuperior + "]");
                System.out.println("Ingrese un numero entero.");

            }
            scanner.nextLine();
        } while (hayErrorNumeroIngresado);
        return numeroIngresado;
    }
    /*********** FIN FUNCION DE AYUYA ***********/
}