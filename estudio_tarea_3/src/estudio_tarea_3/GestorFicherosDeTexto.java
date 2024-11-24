package estudio_tarea_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

			while (((linea1 = br1.readLine()) != null) || ((linea2 = br2.readLine()) != null)) {
				bw.write(linea1);
				bw.write(linea2);
			}

		} catch (Exception e) {

		}

		return ficheroDeTextoResultante;

	}
}