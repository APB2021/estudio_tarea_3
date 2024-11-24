package estudio_tarea_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestorFicherosDeTexto {

	public static Scanner sc = new Scanner(System.in);

	public File solicitarFicheroDeTexto() {
		System.out.print("Ruta: ");
		String ruta = sc.nextLine();
		System.out.print("Nombre: ");
		String nombreFichero = sc.nextLine();

		File fichero = new File(ruta, nombreFichero);

		return fichero;
	}

	public void escribirLineasPares(File ficheroDeTexto) {
		if (ficheroDeTexto.exists() && ficheroDeTexto.canRead()) {

			File ficheroDeTextoDestino = solicitarFicheroDeTexto();

			try (BufferedReader br = new BufferedReader(new FileReader(ficheroDeTexto));
					BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroDeTextoDestino))) {
				String linea;
				int contador = 1;
				while ((linea = br.readLine()) != null) {

					if (contador % 2 == 0) {
						bw.write(linea);
						bw.newLine();
					}
					contador++;
				}
			} catch (Exception e) {
			}
		} else {
			System.out.println("El fichero " + ficheroDeTexto.getName() + " no existe o no es accesible.");
		}
	}

	public File escribeFicheroConLineasAlternativas() {

		File ficheroDeTexto1 = solicitarFicheroDeTexto();
		File ficheroDeTexto2 = solicitarFicheroDeTexto();
		File ficheroDeTextoResultante = solicitarFicheroDeTexto();

		try (BufferedReader br1 = new BufferedReader(new FileReader(ficheroDeTexto1));
				BufferedReader br2 = new BufferedReader(new FileReader(ficheroDeTexto2));
				BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroDeTextoResultante))) {

			String linea1, linea2 = null;

			// Alternar líneas entre ambos ficheros mientras haya contenido
			while (true) {
				// Leer una línea de cada fichero, si está disponible
				if ((linea1 = br1.readLine()) != null) {
					bw.write(linea1);
					bw.newLine();
				}
				if ((linea2 = br2.readLine()) != null) {
					bw.write(linea2);
					bw.newLine();
				}
				// Salir del bucle cuando ambos ficheros estén agotados
				if (linea1 == null && linea2 == null) {
					break;
				}
			}

			System.out.println("Creado  fichero resultante: " + ficheroDeTextoResultante.getName());

		} catch (IOException e) {
			System.err.println("Error al procesar los ficheros " + e.getMessage());
		}

		return ficheroDeTextoResultante;

	}

	/**
	 * Añade texto a un fichero existente. El texto se escribe al final del fichero.
	 * Si el usuario escribe "salir", el proceso termina.
	 */
	public void aniadeTextoaFicheroExistente() {
		File ficheroTextoAniadido = solicitarFicheroDeTexto();

		// Validar existencia y contenido del fichero
		if (!ficheroTextoAniadido.exists()) {
			System.out.println("El fichero " + ficheroTextoAniadido.getName() + " no existe.");
			return; // Salir del método
		}
		if (ficheroTextoAniadido.length() <= 0) {
			System.out.println("El fichero " + ficheroTextoAniadido.getName() + " no tiene contenido.");
			return; // Salir del método
		}

		System.out.println("Escriba el texto que desee añadir a " + ficheroTextoAniadido.getName()
				+ " o escriba \"salir\" para terminar.");

		// Proceso de añadir texto al fichero
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroTextoAniadido, true))) {
			while (true) {
				String textoAniadido = sc.nextLine();
				if (textoAniadido.contains("salir")) {
					System.out
							.println("Proceso terminado. Texto añadido al fichero: " + ficheroTextoAniadido.getName());
					break; // Salir del bucle
				}

				// Añadir texto al fichero con un salto de línea
				bw.write(textoAniadido);
				bw.newLine();
				System.out.println("Texto añadido. Continúe escribiendo o escriba \"salir\" para finalizar.");
			}
		} catch (IOException e) {
			System.err.println("Error al escribir en el fichero: " + e.getMessage());
		}
	}

}