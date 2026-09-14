package src;

// Aula 3.1: a unica mudanca aqui foi o STATUS, que virou enum.
// (Classe abstrata e interface ficaram pra proxima aula.)
public abstract class Tarefa {

    public abstract String tipo();

    // ATRIBUTOS — todos private: quem quiser mexer usa os metodos public.
    private String nome;
    private String descricao;
    private int prioridade;
    private String responsavel;
    private double horasEstimadas;
    private TarefaStatus status;   // era int; agora e o enum

    public Tarefa(String nome, String descricao, int prioridade, String responsavel,
                  double horasEstimadas, TarefaStatus status) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.responsavel = responsavel;
        this.horasEstimadas = horasEstimadas;
        this.status = status;
    }

    public void resumo() {
        IO.println("Tarefa: " + this.nome);
        IO.println("Descricão: " + this.descricao);
        IO.println("Prioridade: " + this.prioridade);
        IO.println("Responsável: " + this.responsavel);
        // getDescricao() traz o nome bonito: "Em andamento" no lugar de EM_ANDAMENTO
        IO.println("Status: " + this.status.getDescricao());
    }

    // METODOS ACESSORIOS - BOILERPLATES
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getPrioridade() { return prioridade; }
    public void setPrioridade(int prioridade) { this.prioridade = prioridade; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public double getHorasEstimadas() { return horasEstimadas; }
    public void setHorasEstimadas(double horasEstimadas) { this.horasEstimadas = horasEstimadas; }

    public TarefaStatus getStatus() { return status; }
    public void setStatus(TarefaStatus status) { this.status = status; }
}
