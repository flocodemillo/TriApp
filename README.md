# TriApp

Módulo de triaje

- Implementacion de callback que no se usa en DiscriminatorAdapter

ToDo

- Buscador en el selector de workflows
- Implementar metodo para calcular el tiempo que tarda el usuario en realizar el triaje
- Sustituyendo el ListView por RecyclerView + CardView -> E/RecyclerView: No adapter attached; skipping layout (parado) 


Bugs

- El SlideAdapter no soporta un número dinamico de niveles debido a que el Report se crea de forma estática
cuando se selecciona un discriminador del último tab (azul) no funciona probablemento debido al ciclo de vida a la hora de crear el último fragment del pager


In process

- StarTriageActivity -> void enTriage(View view): implementar un modo eficiente de vaciar el buffer de manchester para la próxima vez que se use.
