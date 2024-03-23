import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        String nombreArchivo = "Progra.txt";
        Scanner scanner = new Scanner(System.in);
        
        do {
            // LinkedList para almacenar la lista de estudiantes
            LinkedList<String> listaEstudiantes = cargarListaEstudiantes(nombreArchivo);

            // Realizar operaciones de gestión de estudiantes
            System.out.println("\nOperaciones de gestión de estudiantes:");
            System.out.println("1. Remover estudiante");
            System.out.println("2. Editar estudiante");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Mostrar estado actual del archivo");
            System.out.println("5. Agregar nuevo estudiante al final");
            System.out.println("6. Agregar nuevo estudiante al inicio");
            System.out.println("7. Salir");
            System.out.print("Ingrese el número de la operación que desea realizar: ");
            int operacion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del nextInt()

            switch (operacion) {
                case 1:
                    removerEstudiante(listaEstudiantes, nombreArchivo);
                    break;
                case 2:
                    editarEstudiante(listaEstudiantes, nombreArchivo);
                    break;
                case 3:
                    buscarEstudiante(listaEstudiantes);
                    break;
                case 4:
                    mostrarEstadoArchivo(nombreArchivo);
                    break;
                case 5:
                    agregarNuevoEstudiante(listaEstudiantes, nombreArchivo, false); // Agregar al final
                    break;
                case 6:
                    agregarNuevoEstudiante(listaEstudiantes, nombreArchivo, true); // Agregar al inicio
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    return; // Salir del método main y del programa
                default:
                    System.out.println("Operación no válida. Intente de nuevo.");
            }

        } while (true);
    }

    // Función para cargar la lista de estudiantes desde el archivo
    private static LinkedList<String> cargarListaEstudiantes(String nombreArchivo) {
        LinkedList<String> listaEstudiantes = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                listaEstudiantes.add(linea);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la lista de estudiantes: " + e.getMessage());
        }
        return listaEstudiantes;
    }

    // Función para guardar la lista de estudiantes en el archivo
    private static void guardarListaEstudiantes(LinkedList<String> listaEstudiantes, String nombreArchivo) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo));
            for (String estudiante : listaEstudiantes) {
                pw.println(estudiante);
            }
            pw.close();
        } catch (IOException e) {
            System.err.println("Error al guardar la lista de estudiantes: " + e.getMessage());
        }
    }

    // Función para remover un estudiante de la lista
    private static void removerEstudiante(LinkedList<String> listaEstudiantes, String nombreArchivo) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre completo del estudiante que desea remover: ");
        String nombreCompleto = scanner.nextLine();
        boolean removido = listaEstudiantes.removeIf(estudiante -> estudiante.contains(nombreCompleto));
        if (removido) {
            guardarListaEstudiantes(listaEstudiantes, nombreArchivo);
            System.out.println("Estudiante removido exitosamente.");
        } else {
            System.out.println("No se encontró al estudiante en la lista.");
        }
    }

    // Función para editar un estudiante en la lista
    private static void editarEstudiante(LinkedList<String> listaEstudiantes, String nombreArchivo) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre completo del estudiante que desea editar: ");
        String nombreCompleto = scanner.nextLine();
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            String estudiante = listaEstudiantes.get(i);
            if (estudiante.contains(nombreCompleto)) {
                System.out.println("Estudiante encontrado. Ingrese los nuevos datos:");
                System.out.print("Nueva sigla: ");
                String sigla = scanner.nextLine();
                System.out.print("Nuevo grupo: ");
                String grupo = scanner.nextLine();
                System.out.print("Nueva modalidad de estudio: ");
                String modalidadEstudio = scanner.nextLine();
                System.out.print("Nuevo carné: ");
                String carne = scanner.nextLine();
                System.out.print("Nuevo nombre completo: ");
                String nuevoNombreCompleto = scanner.nextLine();
                System.out.print("Nueva dirección electrónica institucional: ");
                String direccionElectronica = scanner.nextLine();
                System.out.print("Nuevo tipo de matrícula: ");
                String tipoMatricula = scanner.nextLine();
                String nuevoEstudiante = sigla + "\t" + grupo + "\t" + modalidadEstudio + "\t" + carne + "\t" +
                                         nuevoNombreCompleto + "\t" + direccionElectronica + "\t" + tipoMatricula;
                listaEstudiantes.set(i, nuevoEstudiante);
                guardarListaEstudiantes(listaEstudiantes, nombreArchivo);
                System.out.println("Estudiante editado exitosamente.");
                return; // Salir del método después de editar el estudiante
            }
        }
        System.out.println("No se encontró al estudiante en la lista.");
    }

    // Función para buscar un estudiante en la lista
    private static void buscarEstudiante(LinkedList<String> listaEstudiantes) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre completo del estudiante que desea buscar: ");
        String nombreCompleto = scanner.nextLine();
        boolean encontrado = false;
        for (String estudiante : listaEstudiantes) {
            if (estudiante.contains(nombreCompleto)) {
                System.out.println("Estudiante encontrado:");
                System.out.println(estudiante);
                encontrado = true;
                break; // Salir del bucle una vez que se encuentra al estudiante
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró al estudiante en la lista.");
        }
    }

    // Función para mostrar el estado actual del archivo
    private static void mostrarEstadoArchivo(String nombreArchivo) {
        System.out.println("Estado actual del archivo:");
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error al mostrar el estado actual del archivo: " + e.getMessage());
        }
    }

    // Función para agregar un nuevo estudiante al final o al inicio de la lista
    private static void agregarNuevoEstudiante(LinkedList<String> listaEstudiantes, String nombreArchivo, boolean alInicio) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los datos del nuevo estudiante:");
        System.out.print("Sigla: ");
        String sigla = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Modalidad de estudio: ");
        String modalidadEstudio = scanner.nextLine();
        System.out.print("Carné: ");
        String carne = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Dirección electrónica institucional: ");
        String direccionElectronica = scanner.nextLine();
        System.out.print("Tipo de matrícula: ");
        String tipoMatricula = scanner.nextLine();

        String nuevoEstudiante = sigla + "\t" + grupo + "\t" + modalidadEstudio + "\t" + carne + "\t" +
                                 nombreCompleto + "\t" + direccionElectronica + "\t" + tipoMatricula;
        
        if (alInicio) {
            listaEstudiantes.addFirst(nuevoEstudiante);
        } else {
            listaEstudiantes.addLast(nuevoEstudiante);
        }

        guardarListaEstudiantes(listaEstudiantes, nombreArchivo);
        System.out.println("Nuevo estudiante agregado correctamente.");
    }
}

