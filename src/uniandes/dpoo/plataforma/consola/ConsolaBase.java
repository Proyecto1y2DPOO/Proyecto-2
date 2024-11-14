package uniandes.dpoo.plataforma.consola;

import uniandes.dpoo.plataforma.Plataforma;

import java.util.Scanner;

public abstract class ConsolaBase {
    protected Plataforma plataforma;
    protected Scanner scanner;
    
    public ConsolaBase(Plataforma plataforma) {
        this.plataforma = plataforma;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Bienvenido a la plataforma de aprendizaje.");
        boolean exit = false;
        while (!exit) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer
            exit = handleMainMenuChoice(choice);
        }
    }
    
    protected abstract void showMainMenu();
    protected abstract boolean handleMainMenuChoice(int choice);

    protected void login() {
        System.out.println("Ingrese su login:");
        String login = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();
        
        try {
            plataforma.iniciarSesion(login, password);
            System.out.println("Inicio de sesión exitoso.");
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }
    
    protected void register() {
        System.out.println("Ingrese su login:");
        String login = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();
        System.out.println("Es profesor? (S/N):");
        String isProfessor = scanner.nextLine();
        
        try {
            plataforma.registrar(login, password, isProfessor.equalsIgnoreCase("S"));
            System.out.println("Registro exitoso.");
        } catch (Exception e) {
            System.out.println("Error en el registro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma();
        Scanner scanner = new Scanner(System.in);
        ConsolaBase console = null;

        System.out.println("¿Desea (1) Iniciar sesión o (2) Registrarse?");
        int option = scanner.nextInt();
        scanner.nextLine();  // Clear buffer

        if (option == 1) {
            // Iniciar sesión
            System.out.println("Ingrese su login:");
            String login = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String password = scanner.nextLine();
            
            try {
                plataforma.iniciarSesion(login, password);
                System.out.println("Inicio de sesión exitoso.");

                // Redirigir según el rol del usuario
                if (plataforma.getEsProfe()) {
                    console = new ConsolaProfesor(plataforma);
                } else {
                    console = new ConsolaStudent(plataforma);
                }
                
            } catch (Exception e) {
                System.out.println("Error al iniciar sesión: " + e.getMessage());
            }

        } else if (option == 2) {
            // Registrarse
            System.out.println("Ingrese su login:");
            String login = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String password = scanner.nextLine();
            System.out.println("Es profesor? (S/N):");
            String isProfessor = scanner.nextLine();

            try {
                plataforma.registrar(login, password, isProfessor.equalsIgnoreCase("S"));
                System.out.println("Registro exitoso.");

                // Redirigir según el rol del usuario
                if (isProfessor.equalsIgnoreCase("S")) {
                    console = new ConsolaProfesor(plataforma);
                } else {
                    console = new ConsolaStudent(plataforma);
                }

            } catch (Exception e) {
                System.out.println("Error en el registro: " + e.getMessage());
            }
        } else {
            System.out.println("Opción no válida.");
        }

        // Iniciar consola si fue inicializada correctamente
        if (console != null) {
            console.start();
        }
        scanner.close();
    }
}