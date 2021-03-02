/**
 * 
 */
package presentacion.main;

public class ValidarDNI {

	public static boolean validar(String dni) {
		if (dni.length() != 9 || !Character.isLetter(dni.charAt(8)))
			return false;

		return soloNumeros(dni) && letraDNI(dni).equalsIgnoreCase(dni.substring(8));
	}

	private static boolean soloNumeros(String dni) {
		try {
			Integer.valueOf(dni.substring(0, dni.length() - 1));
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	private static String letraDNI(String dni) {
		int miDNI = Integer.parseInt(dni.substring(0, 8));
		int resto = 0;
		String miLetra = "";
		String[] asignacionLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S",
				"Q", "V", "H", "L", "C", "K", "E" };

		resto = miDNI % 23;
		miLetra = asignacionLetra[resto];

		return miLetra;
	}

}