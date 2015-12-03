# TriApp

Módulo de triaje

- Implementacion de callback que no se usa en DiscriminatorAdapter

TODO

- Buscador en el selector de workflows
- Sustituir el ListView por RecyclerView + CardView -> E/RecyclerView: No adapter attached; skipping layout (parado) 
- Checkear en DiscriminatorAdapter.java si se selecciona un discriminador cuando ya hay otro seleccionado para avisar o borrar la selección anterior.

Bugs

- Cuando se selecciona un discriminador del tab más próximo a Report (último tab) el ReportFragment no recibe el callback onResume() y no actualiza la vista.

In process

- StarTriageActivity -> void enTriage(View view): implementar un modo eficiente de vaciar el buffer de manchester para la próxima vez que se use (escribir los metodos en el propio Manchester).
- Darle formato a ReportFragment

