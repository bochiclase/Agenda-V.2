import java.io.BufferedWriter;
import java.io.File;
import java.io.*;
import java.util.*;

public class Run {
	/*
	    - Falta salto de linea al guardar el fichero

		- No funciona cargar
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
			
			else if(teclado.contains("buscar:")) {
				partes = teclado.split(":");
				String bus = partes[0];
				String nombre = partes[1];
				

				if (agenda.containsKey(nombre)) {
					System.out.println(nombre + " -> " + agenda.get(nombre));
					System.out.println("");
					bus = null;
					nombre = null;
					
					} 
				
				
				
				else {
					System.out.println("El nombre no esta ni se le espera ;) ");
					System.out.println("");
					nombre = null;
					bus = null;
				}
				
				
			}
			else if(teclado.contains("borrar:")) {
				partes = teclado.split(":");
				String borrar = partes[0];
				String nombre = partes[1];
				String contactos;
				if (agenda.containsKey(nombre)) {
					contactos = agenda.get(nombre);
					agenda.remove(nombre);
					System.out.println("contacto eliminado:" + nombre + "->" + contactos );
					borrar= null;
					nombre=null ;
				}
				else {
					System.out.println("El contacto no se encuentra en la Agenda");
					borrar = null;
					nombre = null;
				}
				
			}
			else if(teclado.contains("guardar:")) {
				partes = teclado.split(":");
				String guardar = partes[0];
				String ruta = partes[1];
				
				
				/** FORMA 2 DE ESCRITURA. Con el fichero codificado en UTF-8 **/
				Writer out = null;
				try {
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta), "UTF-8"));
					
					// Escribimos linea a linea en el fichero
					for (Map.Entry<String, String> entry : agenda.entrySet()) {
						 
						try {
							
							out.write(entry.getKey()+ " - " + entry.getValue());
					
							
							
							
						 System.out.println("EL contacto " + entry.getKey()+ " - " + entry.getValue() + " fué guardado con exito");
						 guardar=null;
						 ruta=null;
						} catch (IOException ex) {
							System.out.println("Mensaje excepcion escritura: " + ex.getMessage());
						}
					}

				} catch (UnsupportedEncodingException | FileNotFoundException ex2) {
					System.out.println("Mensaje error 2: " + ex2.getMessage());
				} finally {
					try {
						out.close();
					} catch (IOException ex3) {
						System.out.println("Mensaje error cierre fichero: " + ex3.getMessage());
					}
				}
				
				
				
			}
			else if(teclado.contains("cargar:")) {
			
				partes =teclado.split(":");
				String nombre = partes[0];
				String numero = partes[1];
				fichero = new File(numero);
				nombre = null;
				numero = null;
				try {
					// Leemos el contenido del fichero
					s = new Scanner(fichero);

					// Leemos linea a linea el fichero
					while (s.hasNextLine()) {
						String linea = s.nextLine(); 	// Guardamos la linea en un String
						
						partes =linea.split("-");
						String nombre2 = partes[0];
						String numero2 = partes[1];
						fichero = new File(numero);
						
						if (!agenda.containsKey(nombre2)) {
							agenda.put(nombre2, numero2);
							nombre2=null;
							numero2=null;
							System.out.println("El contacto se ha cargado correctamente en la agenda desde el fichero");
						}
						else {
							System.out.println("El número ya está en la Agenda");
							System.out.println("¿Qué desea hacer? Escriba el numero de la orden");
							System.out.println("1. Desea guardar " + agenda.get(nombre2));
							System.out.println("2. Desea guardar " + nombre2 + "-" + numero2);
							System.out.print("> ");
							pregunta = r.nextInt();
							do {
								if (pregunta ==1) {
									System.out.println("El teléfono que estaba en la Agenda ha sido guardado con exito");
									nombre2= null;
									numero2=null;
								}
								else if(pregunta == 2) {
									agenda.put(nombre2, numero2);
									nombre2=null;
									numero2= null;
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
		r.close();	
	}
	
}
