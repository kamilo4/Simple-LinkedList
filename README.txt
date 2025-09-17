Repositorio sugerido: linkedlist-academico
Contenido:
- src/Student.java
- src/Course.java
- src/CourseManager.java
- src/Main.java
- README.txt (este archivo)
Instrucciones:
1. Abrir SublimeText.
2. Crear carpeta de proyecto, por ejemplo 'linkedlist-academico/src'.
3. Copiar los archivos .java dentro de src.
4. Compilar:
   javac src/*.java
5. Ejecutar:
   java -cp src Main
Notas:
- La gestión de alumnos está implementada exclusivamente con una lista enlazada simple (campo 'head' en Course y puntero 'next' en Student).
- Inserción: la función insertStudent mantiene la lista ordenada por lastName (alfabético, case-insensitive). Si lastName coincide, se usa idNumber como criterio secundario.
- Se validan duplicados por idNumber antes de insertar.
- Al eliminar una Course se llama freeStudents() para liberar la lista (se separan referencias 'next' para ayudar al recolector de basura).
- No se usan colecciones de alto nivel para los alumnos; sí se usa ArrayList para la colección de cursos (permitido por el enunciado).
