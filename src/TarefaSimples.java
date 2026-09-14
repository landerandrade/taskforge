package src;

public class TarefaSimples extends Tarefa {

    public TarefaSimples(String nome, String descricao, int prioridade, String responsavel,
                         double horasEstimadas, TarefaStatus status) {
        super(nome, descricao, prioridade, responsavel, horasEstimadas, status);
    }

    // POLIMORFISMO (da aula 2.2): a filha tem a SUA versao do resumo.
    public void resumo() {
        IO.println("Resumo da tarefa Simples");
        super.resumo();
    }

    public String tipo() {
        return "Tarefa Simples";
    }
}
