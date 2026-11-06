import sqlite3

class RepositorioPedidos:
    def __init__(self, db_path=":memory:"):
        self.db_path = db_path
        # Si es en memoria mantenemos la conexion viva para que no se borren las tablas entre llamadas
        self.conn = sqlite3.connect(self.db_path) if db_path == ":memory:" else None
        self._crear_tabla()

    def _get_conn(self):
        return self.conn if self.conn else sqlite3.connect(self.db_path)

    def _crear_tabla(self):
        with self._get_conn() as conn:
            conn.execute("""
            CREATE TABLE IF NOT EXISTS pedidos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                cliente TEXT NOT NULL,
                total REAL NOT NULL,
                estado TEXT DEFAULT 'pendiente'
            )
            """)

    def guardar(self, cliente, total):
        with self._get_conn() as conn:
            cursor = conn.execute(
                "INSERT INTO pedidos (cliente, total) VALUES (?, ?)",
                (cliente, total)
            )
            return cursor.lastrowid

    def obtener(self, pedido_id):
        with self._get_conn() as conn:
            row = conn.execute(
                "SELECT id, cliente, total, estado FROM pedidos WHERE id = ?",
                (pedido_id,)
            ).fetchone()
            return row
