class Notificador:
    def enviar_confirmacion(self, cliente, pedido_id):
        """Simula envío de email/SMS. En producción usa un servicio externo."""
        raise NotImplementedError("Servicio externo no disponible en tests")
