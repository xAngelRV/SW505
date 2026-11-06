import pytest
from app.repositorio_pedidos import RepositorioPedidos

@pytest.fixture
def repo_memoria():
    return RepositorioPedidos(db_path=":memory:")

@pytest.fixture
def stub_notificador():
    class Stub:
        def enviar_confirmacion(self, cliente, pedido_id):
            pass
    return Stub()
