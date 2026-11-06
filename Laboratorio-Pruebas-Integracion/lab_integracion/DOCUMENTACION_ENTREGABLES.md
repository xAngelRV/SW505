# Laboratorio: Pruebas de Integración - SW505

## 1. Implementación de pruebas (`test_integracion.py`)
Se implementaron los 5 tipos de pruebas de integración solicitados en `tests/test_integracion.py` para validar la interacción entre `ServicioPedidos`, `RepositorioPedidos` y `Notificador`.

---

## 2. Documentación de Casos de Integración

### CI-001: Integración con Stub
| Campo | Valor |
| :--- | :--- |
| **ID** | CI-001 |
| **Tipo** | Stub |
| **Componentes integrados** | `ServicioPedidos` + `StubNotificador` |
| **Descripción** | Crear un pedido válido y verificar que se retorna un ID, usando un stub que no hace nada |
| **Precondición** | Repositorio con BD en memoria inicializada |
| **Datos de entrada** | `cliente="Ana"`, `total=150.0` |
| **Resultado esperado** | Retorna un entero > 0 |
| **Resultado actual** | Retornó ID = 1 (entero mayor a cero) |
| **Estado** | Aprobado |

### CI-002: Integración con Mock
| Campo | Valor |
| :--- | :--- |
| **ID** | CI-002 |
| **Tipo** | Mock |
| **Componentes integrados** | `ServicioPedidos` + `MockNotificador` |
| **Descripción** | Verificar que al crear un pedido, el notificador recibe exactamente `(cliente, pedido_id)` |
| **Precondición** | Repositorio con BD en memoria |
| **Datos de entrada** | `cliente="Luis"`, `total=200.0` |
| **Resultado esperado** | `enviar_confirmacion` llamado una vez con `("Luis", id_retornado)` |
| **Resultado actual** | Se verificó la llamada con `("Luis", 1)` exactamente una vez |
| **Estado** | Aprobado |

### CI-003: Base de Datos Real (SQLite en memoria)
| Campo | Valor |
| :--- | :--- |
| **ID** | CI-003 |
| **Tipo** | Base de datos real |
| **Componentes integrados** | `ServicioPedidos` + `RepositorioPedidos` (SQLite) |
| **Descripción** | Crear un pedido y recuperarlo de la BD verificando que los datos persisten correctamente |
| **Precondición** | BD SQLite en memoria con tabla pedidos creada |
| **Datos de entrada** | `cliente="María"`, `total=300.0` |
| **Resultado esperado** | El registro recuperado tiene `cliente="María"`, `total=300.0`, `estado="pendiente"` |
| **Resultado actual** | Fila recuperada correctamente: `(1, "María", 300.0, "pendiente")` |
| **Estado** | Aprobado |

### CI-004: Base de Datos Temporal (fixture `tmp_path`)
| Campo | Valor |
| :--- | :--- |
| **ID** | CI-004 |
| **Tipo** | Base de datos temporal (archivo) |
| **Componentes integrados** | `RepositorioPedidos` + SQLite (archivo temporal) |
| **Descripción** | Verificar que el repositorio funciona con una BD en disco y persiste entre conexiones |
| **Precondición** | Archivo de BD temporal creado por `tmp_path` |
| **Datos de entrada** | `cliente="Pedro"`, `total=50.0` |
| **Resultado esperado** | Al cerrar y reabrir conexión, el registro sigue existiendo |
| **Resultado actual** | Una nueva instancia del repositorio leyó el registro persistido (`fila[1] == "Pedro"`) |
| **Estado** | Aprobado |

### CI-005: Integración con Manejo de Errores
| Campo | Valor |
| :--- | :--- |
| **ID** | CI-005 |
| **Tipo** | Integración con error |
| **Componentes integrados** | `ServicioPedidos` + `RepositorioPedidos` |
| **Descripción** | Total inválido no debe llegar al repositorio ni al notificador |
| **Precondición** | Mock del repositorio y mock del notificador |
| **Datos de entrada** | `total=0` |
| **Resultado esperado** | `ValueError`; `repositorio.guardar` NO llamado; `notificador.enviar_confirmacion` NO llamado |
| **Resultado actual** | Lanzó `ValueError("El total debe ser mayor a cero")` sin interactuar con repo ni notificador |
| **Estado** | Aprobado |

---

## 3. Reporte de Ejecución
* **Archivo:** `reporte_completo_integracion.html` / `captura_reporte.png`
* Se ejecutaron 5 pruebas de integración y todas pasaron (5 passed).

---

## 4. Reporte de Cobertura
* **Carpeta / Captura:** `htmlcov/index.html` / `captura_cobertura.png`
* Cobertura por archivo:
  * `repositorio_pedidos.py`: 100%
  * `servicio_pedidos.py`: 100%
  * `notificador.py`: 0% (esperado, al ser un servicio externo simulado mediante mocks/stubs en pruebas)
* **Total app:** 91%

---

## 5. Pregunta de Análisis
**¿Qué diferencia hay entre el caso CI-002 (mock) y CI-003 (BD real)? ¿Qué tipo de defecto detecta cada uno que el otro no puede detectar? Justifique con los resultados obtenidos.**

La diferencia radica en qué aspecto del sistema valida cada una:
- **CI-002 (Mock):** Valida el contrato de comunicación e interacción entre clases (`ServicioPedidos` hacia `Notificador`). Permite detectar si el servicio pasa los argumentos equivocados, en orden invertido o si duplica/omite la llamada a `enviar_confirmacion`. Sin embargo, no puede detectar fallos de base de datos ni errores de SQL.
- **CI-003 (BD Real):** Valida la persistencia directa contra el motor SQLite. Detecta errores de sintaxis SQL, tablas o columnas inexistentes (`OperationalError`), constraints o tipos incorrectos, fallos que un mock no detectaría jamás. Por contraparte, no verifica cómo se comporta el servicio externo de notificaciones.

En los resultados, CI-002 verificó la interacción con `assert_called_once_with`, mientras que CI-003 comprobó los datos físicos guardados en la tupla resultante de SQLite.
