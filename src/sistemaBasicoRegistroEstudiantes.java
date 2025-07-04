import java.util.Scanner;

public class sistemaBasicoRegistroEstudiantes {

    private static String nombreActual = "N/A";
    private static double nota1Actual;
    private static double nota2Actual;
    private static double nota3Actual;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        mostrarMenu(input);
        input.close();
    }

    private static void mostrarMenu(Scanner sc) {
        var salir = false;
        do {
            System.out.println("--- Sistema de Registro de Estudiantes ---");
            System.out.println("""
                    1. Registrar datos de un estudiante
                    2. Mostrar datos del estudiante actual
                    3. Calcular promedio de notas del estudiante actual
                    0. Salir
                     """);
            int opcion = leerEntero(sc, "Ingrese la opción deseada: ");

            switch (opcion) {
                case 1:
                    registrarDatos(sc);
                    break;
                case 2:
                    mostrarInfoEstudiante();
                    break;
                case 3:
                    System.out.printf("Promedio: %.2f%n", calcularPromedioEstudiante());
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (!salir);

        System.out.println("Hasta pronto");
    }

    private static int leerEntero(Scanner input, String mensaje) {
        System.out.print(mensaje);
        while (!input.hasNextInt()) {
            System.out.println("⚠ Entrada inválida. Intente de nuevo.");
            input.nextLine(); // limpiar entrada inválida
            System.out.print(mensaje);
        }
        int entrada = input.nextInt();
        input.nextLine(); // limpiar salto de línea
        return entrada;
    }

    private static void registrarDatos(Scanner input) {
        System.out.print("Nombre del estudiante: ");
        String nombre = input.nextLine();

        double nota1 = leerNota(input, "Primera nota: ");
        double nota2 = leerNota(input, "Segunda nota: ");
        double nota3 = leerNota(input, "Tercera nota: ");

        nombreActual = nombre;
        nota1Actual = nota1;
        nota2Actual = nota2;
        nota3Actual = nota3;
    }

    private static double leerNota(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                double n = sc.nextDouble();
                sc.nextLine(); // consumir el salto de línea pendiente
                return n;

            } else {
                System.out.println("⚠ Entrada inválida. Intente de nuevo.");
                sc.nextLine(); // limpiar entrada incorrecta
            }
        }
    }

    public static void mostrarInfoEstudiante() {
        if (nombreActual.equals("N/A")) {
            System.out.println("⚠️  No se ha registrado ningún estudiante aún.");
        } else {
            System.out.printf("""
                    ─────────────────────────────
                    Estudiante : %s
                    Nota 1     : %.2f
                    Nota 2     : %.2f
                    Nota 3     : %.2f
                    Promedio   : %.2f
                    ─────────────────────────────
                    """, nombreActual, nota1Actual, nota2Actual, nota3Actual,
                    calcularPromedioEstudiante());
        }
    }

    public static double calcularPromedioEstudiante() {
        return (nota1Actual + nota2Actual + nota3Actual) / 3.0;
    }
}
