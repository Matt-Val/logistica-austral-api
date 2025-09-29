Logística Austral - API

Backend REST en Spring Boot para gestionar el arriendo de camiones: Clientes - Flota y Reservas.



Características del Modelo:
Relaciones:
 - 1:N Cliente -> Reservas.
 - 1:N Camion -> Reservas.
 - N:1 Reserva -> Cliente.
 - N:1 Reserva -> Camion.

Campos Importantes: 
 - Cliente: Rut (Se puede introducir una licencia de conducir vigente).
 - Camion: Patente unica, capacidad, estado (Se puede introducir el precio por dia).
 - Reserva: Fechas, estados de proceso, Kilometraje.

Datos a considerar: 
  - Se usa LocalDate para una API clara y moderna, el uso del Date es ambiguo.
  - LocalDate para fechas simples (Sin Hora).
  - LocalDatetime para fecha + hora.
  - Esto se hace para que sea compatible con las bases de datos.

  - En Relaciones OneToMany
  - mapped by = "camion": "No crees una tabla extra, usa la FK que ya esta en Reservas."
  - Cascade = CascadeType.All: "Si borro el camion, borra tambien las reservas."
  - fetch = FetchType.LAZY: "No traigas las reservas hasta que las necesite."
  - @JSONIgnore: "No incluyas las reservas en el JSON."
