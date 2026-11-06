class ServicioPedidos:
    def __init__(self, repositorio, notificador):
        self.repositorio = repositorio
        self.notificador = notificador

    def crear_pedido(self, cliente, total):
        if total <= 0:
            raise ValueError("El total debe ser mayor a cero")
        pedido_id = self.repositorio.guardar(cliente, total)
        self.notificador.enviar_confirmacion(cliente, pedido_id)
        return pedido_id
