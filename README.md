# TriApp

Módulo de triaje

- Implementacion de callback que no se usa en DiscriminatorAdapter

TODO

- Buscador en el selector de workflows
- Sustituyendo el ListView por RecyclerView + CardView -> E/RecyclerView: No adapter attached; skipping layout (parado) 

Bugs

- El SlideAdapter no soporta un número dinamico de niveles debido a que el Report se crea de forma estática
cuando se selecciona un discriminador del último tab (verde) no funciona probablemento debido al ciclo de vida a la hora de crear el último fragment del pager. No está entrando en onResume


In process

- StarTriageActivity -> void enTriage(View view): implementar un modo eficiente de vaciar el buffer de manchester para la próxima vez que se use (escribir los metodos en el propio Manchester).
- Checkear en DiscriminatorAdapter.java si se selecciona un discriminador cuando ya hay otro seleccionado para avisar o borrar la selección anterior.
