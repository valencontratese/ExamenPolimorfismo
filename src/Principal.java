import java.util.Scanner;

import personajes.Koi;
import personajes.Mecca;
import personajes.Nimmo;
import personajes.Personaje;
import utiles.CaraSeca;
import utiles.Utiles;

public class Principal {
	static Personaje personaje; 
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Biemvenido al juego de MASCOTAS VIRTUALES");
		System.out.println("\nEmpezá por seleccionar tu mascota deseada");
		System.out.println("\n 1. NIMMO - Tipo fuego"
				+ "\n 2. MECCA - Tipo aire"
				+ "\n 3. KOI - Tipo agua");
		System.out.println("\nRespuesta:");
		int opc = Utiles.validarEntero(1, 3);
		crearMascota(opc);
		mostrarEstadisticas();
		mostrarMenu();
	}
	
	private static void crearMascota(int opc) {
		 switch(opc) {
		 case 1: 
			 personaje = new Personaje(new Nimmo());
			 break;
		 case 2: 
			 personaje = new Personaje(new Mecca());
			 break;
		 case 3: 
			 personaje = new Personaje(new Koi());
			 break;
		 }
		 
		 System.out.println("¡" + personaje.getMascota().getNombre() + " está listo para empezar!");
	}

	private static void mostrarEstadisticas() {
		System.out.println("----------------");
		System.out.println("Estadísticas:"
				+ "\n\n-Suciedad: " + personaje.getMascota().getSuciedad() + "/100"
				+ "\n\n-Hambre: " + personaje.getMascota().getHambre() + "/100"
				+ "\n\n-Sueño: " + personaje.getMascota().getSueño() + "/100"
				+ "\n\n-Felicidad: " + personaje.getMascota().getFelicidad() + "/100"
				+ "\n\n-Monedas: " + personaje.getMonedas());
	}

	private static void mostrarMenu() {
		int opc;
		do {
			System.out.println("\n------------ MENU DE JUEGO ------------");
			System.out.println("¿Cómo querés seguir?");
			System.out.println("\n 1. Bañar"
					+ "\n 2. Alimentar"
					+ "\n 3. Dormir"
					+ "\n 4. Jugar"
					+ "\n 5. Comprar"
					+ "\n 6. Salir");
			System.out.println("\nRespuesta:");
			opc = Utiles.validarEntero(1, 6);
			actividades(opc);
			if (opc!=6) {
				mostrarEstadisticas();
			}
			else {
				System.out.println("¡Que tengas buen día!");
			}
		}while(opc!=6);
		
	}

	private static void actividades(int opc) {
		switch(opc) {
		case 1: 
			personaje.getMascota().bañarMascota(personaje.getMascota());
			break;
		case 2: 
			personaje.getMascota().comer(personaje);
			break;
		case 3: 
			personaje.getMascota().dormir(personaje);
			break;
		case 4:
			jugar(personaje);
			break;
		case 5: 
			comprar(personaje);
			break;
		}
		
	}
	
	private static void jugar (Personaje personaje2) {
		boolean fin = false;
		
		do {
			System.out.println("¡Jueguemos a Cara o Seca!");
			System.out.println("\n¿Qué elegís?"
					+ "\n 1. Cara"
					+ "\n 2. Seca");
			System.out.println("\n\nRespuesta: ");
			
			int opc = Utiles.validarEntero(1, 2);
			CaraSeca moneda = CaraSeca.tirarMoneda();
			
			if(moneda == CaraSeca.CARA && opc == 1) {
				System.out.println("¡Felicidades, salió Cara y ganaste 3 monedas!");
				personaje.aumentarMonedas(3);
			} else if(moneda == CaraSeca.SECA && opc == 2) {
				System.out.println("¡Felicidades, salió Seca y ganaste 3 monedas!");
				personaje.aumentarMonedas(3);
			} else {
				System.out.println("¡Lástima! Salió " + moneda + " ¡Pero siempre podés volver a competir por más monedas!");
			}
			
			System.out.println("¿Querés volver a jugar?"
					+ "\n 1. Si"
					+ "\n 2. No");
			System.out.println("\nRespuesta: ");
			int res = Utiles.validarEntero(1, 2);
			if(res == 2) {
				fin = true;
			} else fin = false;
			
			personaje.getMascota().aumentarFelicidad(15);
			personaje.getMascota().aumentarSuciedad(5);
			personaje.getMascota().aumentarSueño(5);
			personaje.getMascota().aumentarHambre(7);
			
		}while(!fin);
	}

	private static void comprar(Personaje personaje) {
		boolean fin = false;
		
		do {
			if(personaje.getMonedas() > 0) {
				System.out.println("------------ TIENDA ------------");
				System.out.println("\n1. Manzana - 1 moneda"
						+ "\n2. Fideos - 5 monedas"
						+ "\n3. Sushi - 10 monedas"
						+ "\nRespuesta: ");
				int opc = Utiles.validarEntero(1, 3);
				
				if(opc == 1) {
					personaje.reducirMonedas(1);
					personaje.getComidas()[0].aumentarCantidad();
				} else if(opc == 2) {
					personaje.reducirMonedas(5);
					personaje.getComidas()[1].aumentarCantidad();
				} else if(opc == 3) {
					personaje.reducirMonedas(10);
					personaje.getComidas()[2].aumentarCantidad();
				}
				
				System.out.println("Compra exitosa");
				fin = true;
			} else {
				System.out.println("No hay suficiente dinero, pero podés ganar monedas jugando");
				fin = true;
			}
			
		}while(!fin);
	}

}