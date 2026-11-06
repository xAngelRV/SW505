import pytest
from app.servicio_pedidos import ServicioPedidos
from app.repositorio_pedidos import RepositorioPedidos

class StubNotificador:
    """Stub basico que no realiza acciones ni validaciones"""
    def enviar_confirmacion(self, cliente, pedido_id):
        pass


# 1. Integracion con Stub - CI-001
def test_integracion_con_stub_crea_pedido():
    repo = RepositorioPedidos(db_path=":memory:")
    servicio = ServicioPedidos(repo, StubNotificador())
    pedido_id = servicio.crear_pedido("Ana", 150.0)
    assert isinstance(pedido_id, int)
    assert pedido_id > 0


# 2. Integracion con Mock (verificacion de interaccion) - CI-002
def test_integracion_con_mock_notificador(mocker):
    repo = RepositorioPedidos(db_path=":memory:")
    mock_notif = mocker.MagicMock()
    servicio = ServicioPedidos(repo, mock_notif)
    pedido_id = servicio.crear_pedido("Luis", 200.0)
    mock_notif.enviar_confirmacion.assert_called_once_with("Luis", pedido_id)


# 3. Integracion con Base de Datos Real SQLite - CI-003
def test_integracion_bd_real_persiste_datos():
    repo = RepositorioPedidos(db_path=":memory:")
    servicio = ServicioPedidos(repo, StubNotificador())
    pedido_id = servicio.crear_pedido("María", 300.0)
    fila = repo.obtener(pedido_id)
    assert fila is not None
    assert fila[1] == "María"
    assert fila[2] == 300.0
    assert fila[3] == "pendiente"


# 4. Integracion con Base de Datos Temporal tmp_path - CI-004
def test_integracion_bd_temporal_persiste_entre_conexiones(tmp_path):
    db_file = tmp_path / "test_pedidos.db"
    repo = RepositorioPedidos(db_path=str(db_file))
    servicio = ServicioPedidos(repo, StubNotificador())
    pedido_id = servicio.crear_pedido("Pedro", 50.0)
    
    # Abrimos una nueva conexion contra el mismo archivo
    repo2 = RepositorioPedidos(db_path=str(db_file))
    fila = repo2.obtener(pedido_id)
    assert fila is not None
    assert fila[1] == "Pedro"


# 5. Integracion con Manejo de Errores - CI-005
def test_integracion_error_no_persiste_ni_notifica(mocker):
    mock_repo = mocker.MagicMock()
    mock_notif = mocker.MagicMock()
    servicio = ServicioPedidos(mock_repo, mock_notif)

    with pytest.raises(ValueError, match="El total debe ser mayor a cero"):
        servicio.crear_pedido("Error", 0)

    mock_repo.guardar.assert_not_called()
    mock_notif.enviar_confirmacion.assert_not_called()
