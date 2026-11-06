# Laboratorio: Pruebas Unitarias - SW505
**Escuela Profesional de Ingeniería de Software – FIIS - UNI**  
**Curso:** Construcción de Software I (SW505)  
**Profesor:** Mg. Juan Luis Herencia Guerra  

---

## 1. Archivo `test_calculadora.py` (Entregable 1)
Implementación completa con los 5 tipos fundamentales de pruebas unitarias solicitados en `tests/test_calculadora.py`.

---

## 2. Tablas de Documentación de Casos de Prueba (Entregable 2)
Resultados reales obtenidos tras la ejecución con `pytest`.

### Caso CP-001: Camino Normal (Happy Path)
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-001 |
| **Tipo** | Camino Normal |
| **Descripción** | Sumar dos enteros positivos y verificar división exacta |
| **Precondición** | Ninguna |
| **Datos de entrada** | `a = 2, b = 3` / `a = 10, b = 2` |
| **Resultado esperado** | `5` / `5.0` |
| **Resultado actual** | Retornó `5` y `5.0` correctamente |
| **Estado** | **Aprobado (PASSED)** |

### Caso CP-002: Prueba de Valor Límite (Boundary)
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-002 |
| **Tipo** | Valor Límite |
| **Descripción** | Verificar edad en los bordes de decisión (límite inferior 18 y superior 17) |
| **Precondición** | Ninguna |
| **Datos de entrada** | `edad = 18`, `edad = 17` |
| **Resultado esperado** | `True` para 18, `False` para 17 |
| **Resultado actual** | `es_mayor_de_edad(18) -> True`, `es_mayor_de_edad(17) -> False` |
| **Estado** | **Aprobado (PASSED)** |

### Caso CP-003: Prueba de Manejo de Excepciones
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-003 |
| **Tipo** | Excepción |
| **Descripción** | Dividir por cero debe lanzar `ValueError` con mensaje informativo |
| **Precondición** | Ninguna |
| **Datos de entrada** | `a = 10, b = 0` |
| **Resultado esperado** | `ValueError` con mensaje exacto `"No se puede dividir por cero"` |
| **Resultado actual** | Se capturó la excepción `ValueError` con el mensaje esperado |
| **Estado** | **Aprobado (PASSED)** |

### Caso CP-004: Prueba de Entrada Inválida (Negativa)
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-004 |
| **Tipo** | Entrada Inválida |
| **Descripción** | Edad negativa debe lanzar `ValueError` con mensaje específico |
| **Precondición** | Ninguna |
| **Datos de entrada** | `edad = -5` |
| **Resultado esperado** | `ValueError` con mensaje exacto `"La edad no puede ser negativa"` |
| **Resultado actual** | Se capturó la excepción `ValueError` con el mensaje esperado |
| **Estado** | **Aprobado (PASSED)** |

### Caso CP-005: Prueba Parametrizada (Múltiples Aserciones)
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-005 |
| **Tipo** | Parametrizada (múltiples entradas) |
| **Descripción** | Sumar varios pares de números enteros (positivos, negativos, ceros y números grandes) |
| **Precondición** | Ninguna |
| **Datos de entrada** | `(1, 1) -> 2`, `(-1, 1) -> 0`, `(0, 0) -> 0`, `(100, 200) -> 300` |
| **Resultado esperado** | `2`, `0`, `0`, `300` respectivamente |
| **Resultado actual** | Todas las iteraciones retornaron las sumas esperadas |
| **Estado** | **Aprobado (PASSED)** |

---

## 3. Reporte de Ejecución (Entregable 3)
* Archivo generado: [`reporte_completo.html`](./reporte_completo.html)
* **Resultado:** 10 pruebas ejecutadas, **10 PASSED**, 0 FAILED en 0.62s.

---

## 4. Reporte de Cobertura (Entregable 4)
* Carpeta generada: [`htmlcov/index.html`](./htmlcov/index.html)
* **Métrica alcanzada:**
  ```text
  Name             Stmts   Miss  Cover   Missing
  ----------------------------------------------
  calculadora.py      10      0   100%
  ----------------------------------------------
  TOTAL               10      0   100%
  ```

---

## 5. Respuesta a Pregunta de Cobertura (Entregable 5)
**Pregunta:** *¿Qué línea de `calculadora.py` quedaría sin cubrir si se elimina el test de edad negativa? Justifique usando el reporte de cobertura.*

**Respuesta:**
> Si se elimina el test de edad negativa (`test_edad_negativa`), quedaría sin cubrir la **línea 19** de `calculadora.py`: `raise ValueError("La edad no puede ser negativa")`. 
> **Justificación:** Al no existir otra prueba que suministre un valor menor a cero a `es_mayor_de_edad()`, la condición `if edad < 0:` nunca se evaluará como verdadera y la rama de dicha excepción jamás será ejecutada, lo que se reflejaría en el reporte de cobertura como una sentencia no cubierta (*Missing line 19*) reduciendo la cobertura total al 90%.
