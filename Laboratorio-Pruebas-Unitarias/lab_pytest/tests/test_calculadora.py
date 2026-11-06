import pytest
from calculadora import sumar, dividir, es_mayor_de_edad

# 4.1 Prueba de Camino Normal (Happy Path) - CP-001
def test_sumar_positivos():
    assert sumar(2, 3) == 5


def test_dividir_exacto():
    assert dividir(10, 2) == 5


# 4.2 Prueba de Valor Límite (Boundary) - CP-002
def test_mayor_edad_limite_inferior():
    assert es_mayor_de_edad(18) is True


def test_menor_edad_limite_superior():
    assert es_mayor_de_edad(17) is False


# 4.3 Prueba de Manejo de Excepciones - CP-003
def test_dividir_por_cero():
    with pytest.raises(ValueError, match="No se puede dividir por cero"):
        dividir(10, 0)


# 4.4 Prueba de Entrada Inválida (Negativa) - CP-004
def test_edad_negativa():
    with pytest.raises(ValueError, match="La edad no puede ser negativa"):
        es_mayor_de_edad(-5)


# 4.5 Prueba con Múltiples Aserciones (Parametrizada) - CP-005
@pytest.mark.parametrize("a, b, esperado", [
    (1, 1, 2),
    (-1, 1, 0),
    (0, 0, 0),
    (100, 200, 300),
])
def test_sumar_parametrizado(a, b, esperado):
    assert sumar(a, b) == esperado
