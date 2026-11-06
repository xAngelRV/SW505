import pytest
from calculadora import sumar, dividir, es_mayor_de_edad

# 1. Camino normal (happy path)
def test_sumar_positivos():
    assert sumar(2, 3) == 5

def test_dividir_exacto():
    assert dividir(10, 2) == 5


# 2. Valores limite
def test_mayor_edad_limite_inferior():
    assert es_mayor_de_edad(18) is True

def test_menor_edad_limite_superior():
    assert es_mayor_de_edad(17) is False


# 3. Manejo de excepciones
def test_dividir_por_cero():
    with pytest.raises(ValueError, match="No se puede dividir por cero"):
        dividir(10, 0)


# 4. Entrada invalida
def test_edad_negativa():
    with pytest.raises(ValueError, match="La edad no puede ser negativa"):
        es_mayor_de_edad(-5)


# 5. Prueba parametrizada
@pytest.mark.parametrize("a, b, esperado", [
    (1, 1, 2),
    (-1, 1, 0),
    (0, 0, 0),
    (100, 200, 300),
])
def test_sumar_parametrizado(a, b, esperado):
    assert sumar(a, b) == esperado
