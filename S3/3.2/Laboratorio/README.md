# Sistema de Procesamiento de Pedidos (Fase 6 — Reto Integrador)

Este proyecto aísla los puntos de variación del procesamiento de pedidos aplicando el principio de anticipación al cambio mediante la siguiente arquitectura de paquetes:

- `pe.empresa.pedidos`: Contiene la clase `Main` que compone y orquesta las dependencias del sistema.
- `pe.empresa.pedidos.domain`: Modela la entidad central `Pedido` y las estrategias de descuento comercial (`EstrategiaDescuento`).
- `pe.empresa.pedidos.pago`: Encapsula el contrato de cobro `MetodoPago` y sus implementaciones polimórficas (`PagoYape`, `PagoTarjeta`).
- `pe.empresa.pedidos.notificacion`: Gestiona los canales de mensajería (`Notificador`, `NotificadorEmail`, `NotificadorSms`, `NotificadorWhatsApp`).
- `pe.empresa.pedidos.impuesto`: Aloja las reglas impositivas regionales (`CalculadoraImpuesto`, `ImpuestoLima`, `ImpuestoArequipa`, `ImpuestoSelva`).
- `resources`: Almacena `application.properties` con los parámetros y credenciales externalizadas para desacoplar el entorno del binario.
