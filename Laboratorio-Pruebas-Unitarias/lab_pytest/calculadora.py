# calculadora.py

def sumar(a, b):
    """Retorna la suma de dos números."""
    return a + b


def dividir(a, b):
    """Retorna la división de a entre b. Lanza ValueError si b es cero."""
    if b == 0:
        raise ValueError("No se puede dividir por cero")
    return a / b


def es_mayor_de_edad(edad):
    """Retorna True si edad >= 18, False en caso contrario.
    Lanza ValueError si edad es negativa."""
    if edad < 0:
        raise ValueError("La edad no puede ser negativa")
    return edad >= 18
