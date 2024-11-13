package uniandes.dpoo.plataforma.consola;

import uniandes.dpoo.plataforma.Plataforma;

public class ConsolaStudent extends ConsolaBase{
	
	

	    public ConsolaStudent(Plataforma plataforma) {
	        super(plataforma);
	    }

	    @Override
	    protected void showMainMenu() {
	        System.out.println("\nMenú de Estudiante:");
	        System.out.println("1. Inscribir en Learning Path");
	        System.out.println("2. Ver actividades");
	        System.out.println("3. Marcar actividad como completada");
	        System.out.println("4. Ver progreso");
	        System.out.println("5. Cerrar sesión");
	        System.out.println("0. Salir");
	        System.out.print("Seleccione una opción: ");
	    }

	    @Override
	    protected boolean handleMainMenuChoice(int choice) {
	        switch (choice) {
	            case 1:
	                enrollInLearningPath();
	                break;
	            case 2:
	                viewActivities();
	                break;
	            case 3:
	                markActivityAsComplete();
	                break;
	            case 4:
	                viewProgress();
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

	    private void enrollInLearningPath() {
	        System.out.print("Ingrese el título del Learning Path: ");
	        String tituloLP = scanner.nextLine();
	        try {
	            plataforma.inscribirLearningPath(tituloLP);
	            System.out.println("Inscripción exitosa.");
	        } catch (Exception e) {
	            System.out.println("Error al inscribir en Learning Path: " + e.getMessage());
	        }
	    }

	    private void viewActivities() {
	        System.out.print("Ingrese el título del Learning Path: ");
	        String tituloLP = scanner.nextLine();
	        try {
	            plataforma.mostrarActividades(tituloLP).forEach((key, actividad) -> 
	                System.out.println("Actividad: " + key + " - " + actividad.getDescripcion()));
	        } catch (Exception e) {
	            System.out.println("Error al ver actividades: " + e.getMessage());
	        }
	    }

	    private void markActivityAsComplete() {
	        System.out.print("Ingrese el título del Learning Path: ");
	        String tituloLP = scanner.nextLine();
	        System.out.print("Ingrese el estado de la actividad (completada/fallida): ");
	        String estado = scanner.nextLine();
	        try {
	            plataforma.marcaCompletarActividad(tituloLP, estado);
	            System.out.println("Actividad marcada como " + estado + ".");
	        } catch (Exception e) {
	            System.out.println("Error al marcar actividad: " + e.getMessage());
	        }
	    }

	    private void viewProgress() {
	        System.out.print("Ingrese el título del Learning Path: ");
	        String tituloLP = scanner.nextLine();
	        try {
	            int progreso = plataforma.verProgreso(tituloLP, plataforma.getUsuario());
	            System.out.println("Progreso en " + tituloLP + ": " + progreso + "%.");
	        } catch (Exception e) {
	            System.out.println("Error al ver progreso: " + e.getMessage());
	        }
	    }
	}



