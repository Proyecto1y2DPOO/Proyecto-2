package uniandes.dpoo.plataforma.consola;

import uniandes.dpoo.plataforma.Plataforma;

public class ConsolaProfesor extends  ConsolaBase{
	
	

    public ConsolaProfesor(Plataforma plataforma) {
        super(plataforma);
    }

    @Override
    protected void showMainMenu() {
        System.out.println("\nMenú de Profesor:");
        System.out.println("1. Crear Learning Path");
        System.out.println("2. Crear Actividad");
        System.out.println("3. Clonar Actividad");
        System.out.println("4. Cambiar Estado de Actividad");
        System.out.println("5. Cerrar sesión");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    protected boolean handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                createLearningPath();
                break;
            case 2:
                createActivity();
                break;
            case 3:
                cloneActivity();
                break;
            case 4:
                changeActivityState();
                break;
            case 5:
                System.out.println("Cerrando sesión...");
                return true;
            case 0:
                System.out.println("Saliendo de la aplicación...");
                return true;
            default:
                System.out.println("Opción no válida.");
        }
        return false;
    }

    private void createLearningPath() {
        System.out.print("Ingrese el título del Learning Path: ");
        String titulo = scanner.nextLine();
        // Other fields can be added as needed
        try {
            plataforma.crearLearningPath(titulo, "Descripción", "Objetivos", "Nivel", 60, plataforma.getUsuario());
            System.out.println("Learning Path creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear Learning Path: " + e.getMessage());
        }
    }

    private void createActivity() {
        System.out.print("Ingrese el título del Learning Path: ");
        String tituloLP = scanner.nextLine();
        System.out.print("Ingrese el título de la actividad: ");
        String titulo = scanner.nextLine();
        // Other activity details can be added
        try {
            plataforma.crearTarea(tituloLP, titulo, "Descripción", 30, "Nivel", null, null, true, plataforma.getUsuario());
            System.out.println("Actividad creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear actividad: " + e.getMessage());
        }
    }

    private void cloneActivity() {
        System.out.print("Ingrese el título del Learning Path de origen: ");
        String tituloOrigen = scanner.nextLine();
        System.out.print("Ingrese el título del Learning Path de destino: ");
        String tituloDestino = scanner.nextLine();
        System.out.print("Ingrese el título de la actividad a clonar: ");
        String actividad = scanner.nextLine();
        try {
            plataforma.clonarActividad(tituloOrigen, actividad, tituloDestino);
            System.out.println("Actividad clonada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al clonar actividad: " + e.getMessage());
        }
    }

    private void changeActivityState() {
        System.out.print("Ingrese el título del Learning Path: ");
        String tituloLP = scanner.nextLine();
        System.out.print("Ingrese el estado de la actividad (en progreso/completada/fallida): ");
        String estado = scanner.nextLine();
        System.out.print("Ingrese el título de la actividad: ");
        String tituloActividad = scanner.nextLine();
        try {
            plataforma.cambiarEstadoActividad(tituloLP, estado, plataforma.getUsuario(), tituloActividad);
            System.out.println("Estado de actividad cambiado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al cambiar el estado de la actividad: " + e.getMessage());
        }
    }
}


	


