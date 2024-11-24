package estudio_tarea_3;

import java.io.File;

public class Ejercicio2 {

	public static void main(String[] args) {

		System.out.print("Primer fichero de texto de lectura: ");
		File primerFicheroDeTexto = new GestorFicherosDeTexto().solicitarFicheroDeTexto();

		System.out.print("Segundo fichero de texto de lectura: ");
		File segundoFicheroDeTexto = new GestorFicherosDeTexto().solicitarFicheroDeTexto();

		System.out.print("Último fichero de texto para escritura: ");
		File ficheroDeTextoEscritura = new GestorFicherosDeTexto().solicitarFicheroDeTexto();

	}
}