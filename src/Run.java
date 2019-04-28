import java.io.File;
import java.util.*;

public class Run {
	/*
	    - Falta Mirar bien la ordenad de interpretar el comando de carga y guardar.

		- Falta el algoritmo de guardar
	*/

	public static void main(String[] args) {
			Scanner r = new Scanner(System.in);
			Map<String, String> agenda= new HashMap <String,String>();
			String teclado ="";
			String[] partes = new String [1];
			
			File fichero = new File("./fichero_Agenda.txt");
			Scanner s = null;
			int pregunta = 0;
			
		do {
			System.out.println("Introduce");
			System.out.print("> ");
			teclado = r.next();
			
			if (teclado.contains("-")) {
				partes = teclado.split("-");
				String nombre = partes[0];
				String numero = partes[1];

				if (!agenda.containsKey(nombre)) {
				agenda.put(nombre, numero);
				System.out.println("El numero se guardó en la agenda correctamente");
				System.out.println("");
				nombre = null;
				numero = null;
				}
				else {
					agenda.put(nombre, numero);
					System.out.println("El numero fue actualizado");
					System.out.println("");
					nombre = null;
					numero = null;
					
				}

			}
			
			else if(teclado.contains("buscar")) {
				partes = teclado.split(":");
				String numero = partes[0];
				String nombre = partes[1];
				

				if (agenda.containsKey(nombre)) {
					System.out.println(nombre + " -> " + agenda.get(nombre));
					System.out.println("");
					nombre = null;
					numero = null;
					
					} 
				
				
				
				else {
					System.out.println("El nombre no esta ni se le espera ;) ");
					System.out.println("");
					nombre = null;
					numero = null;
				}
				
				
			}
			else if(teclado.contains("borrar")) {
				partes = teclado.split(":");
				String numero = partes[0];
				String nombre = partes[1];
				
				if (agenda.containsKey(nombre)) {
					agenda.remove(nombre);
					System.out.println("contacto eliminado:" + nombre + "->" + numero );
					numero= null;
					nombre=null ;
				}
				else {
					System.out.println("El contacto no se encuentra en la Agenda");
					numero = null;
					nombre = null;
				}
				
			}
			else if(teclado.contains("guardar")) {
				
				
				
				
				
				
			}
			else if(teclado.contains("cargar")) {
			

				try {
					// Leemos el contenido del fichero
					s = new Scanner(fichero);

					// Leemos linea a linea el fichero
					while (s.hasNextLine()) {
						String linea = s.nextLine(); 	// Guardamos la linea en un String
						
						partes =linea.split("-");
						String nombre = partes[0];
						String numero = partes[1];
						
						if (!agenda.containsKey(nombre)) {
							agenda.put(nombre, numero);
							nombre=null;
							numero=null;
							System.out.println("El contacto se ha cargado correctamente en la agenda desde el fichero");
						}
						else {
							System.out.println("El número ya está en la Agenda");
							System.out.println("¿Qué desea hacer? Escriba el numero de la orden");
							System.out.println("1. Desea guardar " + agenda.get(nombre));
							System.out.println("2. Desea guardar " + nombre + "-" + numero);
							System.out.print("> ");
							pregunta = r.nextInt();
							do {
								if (pregunta ==1) {
									System.out.println("El teléfono que estaba en la Agenda ha sido guardado con exito");
									nombre= null;
									numero=null;
								}
								else if(pregunta == 2) {
									agenda.put(nombre, numero);
									nombre=null;
									numero= null;
									System.out.println("Se ha sobrescribido el número que estaba en la Agenda por el del fichero");
								}
								else {
									System.out.println("Se esperaba un 1 o un 2");
								}
								
							}while(pregunta !=1 || pregunta != 2);
						}
						
					}

				} catch (Exception ex) {
					System.out.println("Mensaje: " + ex.getMessage());
				} finally {
					// Cerramos el fichero tanto si la lectura ha sido correcta o no
					try {
						if (s != null)
							s.close();
					} catch (Exception ex2) {
						System.out.println("Mensaje 2: " + ex2.getMessage());
					}
				}
			}
				
			
			
			else if(!teclado.contains("fin")) {
				System.out.println("No has introducido correctamente el patron de la Agenda");
				System.out.println("");
			}
 
	
	}while (!teclado.contains("fin"));
			
	}
	
}
