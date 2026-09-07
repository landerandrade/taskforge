package src;

public class TarefaComPrazo extends Tarefa implements Notificavel {

    double tempoLimite;   // o campo que so a filha tem

    public TarefaComPrazo(String nome, String descricao, int prioridade, String responsavel,
                          double horasEstimadas, TarefaStatus status, double tempoLimite) {
        super(nome, descricao, prioridade, responsavel, horasEstimadas, status);
        this.tempoLimite = tempoLimite;
    }

    public void resumo() {
        IO.println("Resumo da tarefa com Prazo");
        super.resumo();
        IO.println("Tempo limite: " + this.tempoLimite);
    }

    public String tipo() {
        return "Tarefa com Prazo";
    }

    public String notificar() {
        return "Lembrete: " + getNome() + " vence em: " + this.tempoLimite + " horas";
    }
}
