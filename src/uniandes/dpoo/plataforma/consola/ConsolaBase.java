package uniandes.dpoo.plataforma.consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.actividades.Actividad;
import uniandes.dpoo.actividades.Tarea;
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
        
        ConsolaBase console;
        System.out.println("¿Es usted un profesor o un estudiante? (P/E)");
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine();

        if (userType.equalsIgnoreCase("P")) {
            console = new ConsolaProfesor(plataforma);
        } else {
            console = new ConsolaStudent(plataforma);
        }

        console.start();
        scanner.close();
    }
}



