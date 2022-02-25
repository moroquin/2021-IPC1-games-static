package BuscaMinasStatic;
import java.util.Random;
import java.util.Scanner;

public class BuscaMinas {

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static final int ancho = 10;
    public static final int alto = 10;
    public static final int cuadrosADescubrir = 2;

    public static int contadorCuadrosDescubiertos = 0;
    public static int maximoCuadrosDescubiertos = 0;


    public static int[][] tablero = new int[alto][ancho];
    public static int[][] posicionesDescuebiertas = new int[alto][ancho];

    public static void main(String[] args) {
        // pintarTablero();

        // inicializar
        int cantMinas = solicitarNumero("Cuantas minas desea en el juego, en el rango [5..20]", 5, 20);
        maximoCuadrosDescubiertos = alto*ancho - cantMinas;
        agregarMinasRandom(cantMinas);
        // jugar
        jugar();

    }

    // encargado de la dinamica del juego
    public static void jugar() {
        boolean finDeJuego = false;

        while (!finDeJuego) {
            // mostrar tablero
            // pido pos x
            // pido pos y
            // verifico si hay mina, sino debo de mostrar
            pintarTablero();
            int posAlto = solicitarNumero("Ingrese la coordenda de la fila: ", 0, alto);
            int posAncho = solicitarNumero("Ingrese la coordenda de la columna: ", 0, ancho);
            finDeJuego = descubrirPosicion(posAlto, posAncho);
            if (finDeJuego) {
                pintarTablero();
                System.out.println("*************perdio/ verificar");
            }
            if (contadorCuadrosDescubiertos == maximoCuadrosDescubiertos){
                pintarTablero();
                finDeJuego = true;
                System.out.println("********** Ganó es un crack");
            }
        }
    }

    // true si encuentra una bomba
    // false si no encuentra una bomba
    public static boolean descubrirPosicion(int pAlto, int pAncho) {
        boolean respuesta = false;
        if (posicionesDescuebiertas[pAlto][pAncho]!= 1)
            contadorCuadrosDescubiertos++;
        posicionesDescuebiertas[pAlto][pAncho] = 1;
        if (tablero[pAlto][pAncho] != 1) {
            int posIniAlto = componerPosicion(pAlto - cuadrosADescubrir, 0, alto);
            int posIniAncho = componerPosicion(pAncho - cuadrosADescubrir, 0, ancho);
            int posFinAlto = componerPosicion(pAlto + cuadrosADescubrir, 0, alto);
            int posFinAncho = componerPosicion(pAlto + cuadrosADescubrir, 0, ancho);
            descubrirPosicionesAutomaticas(posIniAlto, posIniAncho, posFinAlto, posFinAncho);
            // no hay mina
            // falta descubrir al rededores;
        } else {
            respuesta = true;
        }

        return respuesta;
    }

    public static void descubrirPosicionesAutomaticas(int altoIni, int anchoIni, int altoFin, int anchoFin) {
        System.out.println("["+altoIni+","+anchoIni+"] ");
        System.out.println("["+altoFin+","+anchoFin+"] ");
        for (int i = altoIni; i < altoFin; i++) {
            for (int j = anchoIni; j < anchoFin; j++) {
                if (tablero[i][j] != 1) {
                    // no tiene mina
                    // lo puedo descubrir
                    if (posicionesDescuebiertas[i][j]!= 1)
                            contadorCuadrosDescubiertos++;
                    posicionesDescuebiertas[i][j] = 1;
                }
            }
        }

    }

    public static void agregarMinasRandom(int cantMinas) {
        boolean errorNumeroGenerado = false;
        for (int i = 0; i < cantMinas; i++) {

            errorNumeroGenerado = false;
            do {
                int posAncho = generarNumeroRandom(0, ancho);
                int posAlto = generarNumeroRandom(0, alto);
                if (tablero[posAlto][posAncho] == 0) {
                    tablero[posAlto][posAncho] = 1;
                    errorNumeroGenerado = false;
                } else {
                    // System.out.println("***************** numero repetido");
                    errorNumeroGenerado = true;
                }

            } while (errorNumeroGenerado);

        }

    }

    public static void pintarTablero() {

        // cabecera
        System.out.println("               Fila  ");
        System.out.print("  ");
        for (int j = 0; j < ancho; j++) {
            System.out.print(j + " ");
        }
        System.out.print("\n");

        // imprimir el contenido
        for (int i = 0; i < alto; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < ancho; j++) {

                System.out.print(pintarPiezaTablero(i, j) + " ");
            }
            System.out.print("\n");
        }

    }

    // tipo 0 : no tiene nada
    // tipo 1 : tiene una mina
    public static String pintarPiezaTablero(int alto, int ancho) {
        int tipo = 0;
        if (posicionesDescuebiertas[alto][ancho] == 1) {
            tipo = tablero[alto][ancho];
            if (tipo == 0) {
                tipo = 3;
            }
        }

        String res = "";
        switch (tipo) {
            case 0:
                res = "U";
                break;
            case 1:
                res = "M";
                break;
            case 3:
                res = " ";
                break;
            default:
                res = " ";
                break;
        }

        return res;
    }

    // ********************** funciones de ayuda

    // revisar si es menor que el minimo, por lo tanto debe conevertirse en el
    // minimo
    // si es mayor que el máximo debe convertirse en el maximo
    public static int componerPosicion(int pos, int min, int max) {
        int res = pos;
        if (pos < min) {
            res = min;
        }

        if (pos > max) {
            res = max;
        }

        return res;
    }

    public static int generarNumeroRandom(int min, int max) {
        // int tmp = random.nextInt(max-min)+min;
        // System.out.println(tmp);

        // return tmp;
        return random.nextInt(max - min) + min;
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

    public static String solicitarString(String mensaje) {
        String respuesta = "";
        boolean hayErrorStringIngresado = false;

        do {
            System.out.println("\n" + mensaje);
            respuesta = scanner.nextLine();
            // scanner.nextLine();//probar
            respuesta = respuesta.trim();
            if (respuesta.length() > 0) {
                hayErrorStringIngresado = false;
            } else {
                hayErrorStringIngresado = true;
                System.out.println("Ingresa un caracter como minimo.");
            }

        } while (hayErrorStringIngresado);

        return respuesta;
    }

}
