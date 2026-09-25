public class ServicioDocente {
    private final RepositorioDocente repositorio;
    public ServicioDocente(RepositorioDocente r) { this.repositorio = r; }
    public List<Docente> listar() { return repositorio.todos(); }
}
