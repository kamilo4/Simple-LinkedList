import java.util.Scanner;

/*
 Simple CLI to demonstrate functionality.
*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseManager mgr = new CourseManager();

        System.out.println("Demo gestor de ofertacadémica (lista enlazada simple para alumnos).");

        // create sample course
        mgr.createCourse("C101", "Introducción a Programación", 3);
        Course c = mgr.getCourseById("C101");
        c.insertStudent(new Student("Ana", "García", "1001", 1, "Sistemas"));
        c.insertStudent(new Student("Luis", "Alvarez", "1002", 2, "Sistemas"));
        c.insertStudent(new Student("María", "Pérez", "1003", 3, "Sistemas"));

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Listar cursos");
            System.out.println("2. Crear curso");
            System.out.println("3. Eliminar curso");
            System.out.println("4. Listar alumnos de un curso");
            System.out.println("5. Inscribir alumno");
            System.out.println("6. Actualizar alumno");
            System.out.println("7. Retirar alumno");
            System.out.println("8. Salir");
            System.out.print("Seleccione opción: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    for (Course cc : mgr.listCourses()) {
                        System.out.println(cc.toString());
                    }
                    break;
                case "2":
                    System.out.print("Id curso: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Nombre: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Créditos (entero positivo): ");
                    int creds = Integer.parseInt(sc.nextLine().trim());
                    boolean created = mgr.createCourse(id, name, creds);
                    System.out.println(created ? "Curso creado." : "Error: id duplicado o créditos inválidos.");
                    break;
                case "3":
                    System.out.print("Id curso a eliminar: ");
                    String idd = sc.nextLine().trim();
                    boolean del = mgr.deleteCourse(idd);
                    System.out.println(del ? "Curso eliminado (y lista de alumnos liberada)." : "No se encontró curso.");
                    break;
                case "4":
                    System.out.print("Id curso: ");
                    String ic = sc.nextLine().trim();
                    Course cc = mgr.getCourseById(ic);
                    if (cc == null) {
                        System.out.println("No se encontró curso.");
                    } else {
                        System.out.println(cc.listAllStudents());
                    }
                    break;
                case "5":
                    System.out.print("Id curso: ");
                    String ic2 = sc.nextLine().trim();
                    Course c5 = mgr.getCourseById(ic2);
                    if (c5 == null) {
                        System.out.println("No se encontró curso.");
                        break;
                    }
                    System.out.print("ID alumno (idNumber): ");
                    String idnum = sc.nextLine().trim();
                    if (c5.existsById(idnum)) {
                        System.out.println("Error: idNumber duplicado.");
                        break;
                    }
                    System.out.print("First name: ");
                    String fn = sc.nextLine().trim();
                    System.out.print("Last name: ");
                    String ln = sc.nextLine().trim();
                    System.out.print("Semester (int): ");
                    int sem = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Program: ");
                    String prog = sc.nextLine().trim();
                    c5.insertStudent(new Student(fn, ln, idnum, sem, prog));
                    System.out.println("Alumno inscrito (ordenado por apellido).");
                    break;
                case "6":
                    System.out.print("Id curso: ");
                    String ic3 = sc.nextLine().trim();
                    Course c6 = mgr.getCourseById(ic3);
                    if (c6 == null) {
                        System.out.println("No se encontró curso.");
                        break;
                    }
                    System.out.print("ID alumno a actualizar: ");
                    String idu = sc.nextLine().trim();
                    Student found = c6.findById(idu);
                    if (found == null) {
                        System.out.println("Alumno no encontrado.");
                        break;
                    }
                    System.out.println("Encontrado: " + found.toString());
                    System.out.print("Nuevo first name (enter para mantener): ");
                    String nfn = sc.nextLine().trim(); if (nfn.isEmpty()) nfn = null;
                    System.out.print("Nuevo last name (enter para mantener): ");
                    String nln = sc.nextLine().trim(); if (nln.isEmpty()) nln = null;
                    System.out.print("Nuevo semester (enter para mantener): ");
                    String ns = sc.nextLine().trim(); Integer nsem = ns.isEmpty() ? null : Integer.parseInt(ns);
                    System.out.print("Nuevo program (enter para mantener): ");
                    String np = sc.nextLine().trim(); if (np.isEmpty()) np = null;
                    boolean up = c6.updateStudent(idu, nfn, nln, nsem, np);
                    System.out.println(up ? "Alumno actualizado." : "Error al actualizar.");
                    break;
                case "7":
                    System.out.print("Id curso: ");
                    String ic4 = sc.nextLine().trim();
                    Course c7 = mgr.getCourseById(ic4);
                    if (c7 == null) {
                        System.out.println("No se encontró curso.");
                        break;
                    }
                    System.out.print("ID alumno a retirar: ");
                    String idr = sc.nextLine().trim();
                    boolean rem = c7.removeById(idr);
                    System.out.println(rem ? "Alumno retirado y lista consistente." : "Alumno no encontrado.");
                    break;
                case "8":
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        sc.close();
        System.out.println("Fin demo.");
    }
}
