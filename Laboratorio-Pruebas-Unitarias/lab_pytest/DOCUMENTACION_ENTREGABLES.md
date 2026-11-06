# Laboratorio: Pruebas Unitarias - SW505

## 1. Implementación de pruebas (`test_calculadora.py`)
Se implementaron los 5 tipos de prueba solicitados en `tests/test_calculadora.py` para validar `calculadora.py`.

---

## 2. Documentación de Casos de Prueba

### CP-001: Camino Normal
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-001 |
| **Tipo** | Camino Normal |
| **Descripción** | Sumar dos enteros positivos y división exacta |
| **Precondición** | Ninguna |
| **Datos de entrada** | `a = 2, b = 3` / `a = 10, b = 2` |
| **Resultado esperado** | `5` y `5.0` |
| **Resultado actual** | Retornó `5` y `5.0` |
| **Estado** | Aprobado |

### CP-002: Valor Límite
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-002 |
| **Tipo** | Valor Límite |
| **Descripción** | Verificar edad en los límites 18 y 17 |
| **Precondición** | Ninguna |
| **Datos de entrada** | `edad = 18`, `edad = 17` |
| **Resultado esperado** | `True` para 18, `False` para 17 |
| **Resultado actual** | Retornó `True` y `False` según lo esperado |
| **Estado** | Aprobado |

### CP-003: Manejo de Excepciones
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-003 |
| **Tipo** | Excepción |
| **Descripción** | División entre cero lanza ValueError |
| **Precondición** | Ninguna |
| **Datos de entrada** | `a = 10, b = 0` |
| **Resultado esperado** | ValueError con mensaje "No se puede dividir por cero" |
| **Resultado actual** | Capturó ValueError con el mensaje correcto |
| **Estado** | Aprobado |

### CP-004: Entrada Inválida
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-004 |
| **Tipo** | Entrada Inválida |
| **Descripción** | Edad negativa lanza ValueError |
| **Precondición** | Ninguna |
| **Datos de entrada** | `edad = -5` |
| **Resultado esperado** | ValueError con mensaje "La edad no puede ser negativa" |
| **Resultado actual** | Capturó ValueError con el mensaje correcto |
| **Estado** | Aprobado |

### CP-005: Parametrizada
| Campo | Valor |
| :--- | :--- |
| **ID** | CP-005 |
| **Tipo** | Parametrizada (múltiples entradas) |
| **Descripción** | Sumar varios pares de números |
| **Precondición** | Ninguna |
| **Datos de entrada** | `(1, 1) -> 2`, `(-1, 1) -> 0`, `(0, 0) -> 0`, `(100, 200) -> 300` |
| **Resultado esperado** | `2`, `0`, `0`, `300` |
| **Resultado actual** | Las 4 combinaciones retornaron el valor esperado |
| **Estado** | Aprobado |

---

## 3. Reporte de Ejecución
* Archivo: `reporte_completo.html`
* Se ejecutaron 10 pruebas y todas pasaron (10 passed).

---

## 4. Reporte de Cobertura
* Carpeta: `htmlcov/index.html`
* Se obtuvo 100% de cobertura en `calculadora.py`.

---

## 5. Pregunta de Cobertura
**¿Qué línea de `calculadora.py` quedaría sin cubrir si se elimina el test de edad negativa? Justifique usando el reporte de cobertura.**

Quedaría sin cubrir la **línea 19**: `raise ValueError("La edad no puede ser negativa")`.

**Justificación:** Si quitamos el test `test_edad_negativa`, ningún otro caso le envía una edad menor a 0 a la función `es_mayor_de_edad`. Por lo tanto, la condición `if edad < 0` nunca se cumple y esa línea jamás se ejecuta, bajando la cobertura de 100% a 90% (marcada en rojo en el reporte de cobertura).
