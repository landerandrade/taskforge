// TaskForge - Do Código ao Contrato
// Código final da aula 3.1: visibilidade + enum + LISTAS.
// (classe abstrata, interface e o criarTarefa() ficaram pra aula 3.2)

import src.*;

import java.util.ArrayList;
import java.util.List;

void main() {
    // A LISTA: guarda quantas tarefas a gente quiser.
    // Adeus tarefa1, tarefa2, tarefa3... e adeus contador na mao.
    List<Tarefa> listaTarefa = new ArrayList<>();
    listaTarefa.add(new TarefaComPrazo("Tarefa com Prazo", "Descricao", 1,
            "sem responsavel", 2, TarefaStatus.PENDENTE, 4));

    int opcao = 0;
    do {
        IO.println("===== TaskForge =====");
        IO.println("1 - Criar Tarefa");
        IO.println("2 - Listar Tarefas");
        IO.println("3 - Listar Pendentes");
        IO.println("4 - Sair");
        IO.println("=====================");
        try {
            opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));
        } catch (NumberFormatException e) {
            IO.println("Opção inválida! Escolha entre 1 e 4");
        }
        if (opcao < 1 || opcao > 4) {
            IO.println("Opção inválida! Escolha entre 1 e 4");
            continue;
        }

        switch (opcao) {
            case 1:
                try {
                    listaTarefa.add(criarTarefa());
                } catch (TarefaInvalidaException e) {
                    IO.println("Erro ao criar tarefa: " + e.getMessage());
                }
                break;
            case 2:
                listarTarefas(listaTarefa);
                break;
            case 3:
                //TODO listar tarefas pendentes
                break;
            default:
        }
    } while(opcao != 4);

    // size() ja sabe contar — nao precisa de int contador
    IO.println("Total de tarefas: " + listaTarefa.size());
    IO.println();

    // REMOVE: pela posicao...
//    listaTarefa.remove(0);
//    IO.println("Depois do remove(0): " + listaTarefa.size() + " tarefa(s)");

    // ...ou pelo proprio objeto
//    Tarefa ultima = listaTarefa.get(listaTarefa.size() - 1);
//    listaTarefa.remove(ultima);
//    IO.println("Depois do remove(objeto): " + listaTarefa.size() + " tarefa(s)");
//
//    for (Tarefa t : listaTarefa) {
//        IO.println("Ainda na lista: " + t.getNome());
//    }
}

Tarefa criarTarefa() throws TarefaInvalidaException {
    String nome = IO.readln("Digite o nome da tarefa: ");
    if (nome.isBlank()) {
        throw new TarefaInvalidaException("O nome não pode ficar vazio");
    }
    String descricao = IO.readln("Digite a descricao da tarefa: ");
    int prioridade = 0;
    do {
        try {
            prioridade = Integer.parseInt(IO.readln("Digite a prioridade da tarefa: "));
        } catch (NumberFormatException e) {
            IO.println("Essa prioridade não existe!");
        }
        if (prioridade < 1 || prioridade > 5) {
            IO.println("Essa prioridade não existe! Digite novamente");
        }
    } while(prioridade < 1 || prioridade > 5);
//    String responsavel;
//    double horasEstimadas;
//    TarefaStatus status;
    return new TarefaSimples(nome, descricao, prioridade,
            "sem responsavel", 2, TarefaStatus.PENDENTE);
}

void listarTarefas(List<Tarefa> tarefas) {
    for (Tarefa tarefa : tarefas) {
        tarefa.resumo();
        if (tarefa instanceof Notificavel notificacao) {
            IO.println(notificacao.notificar());
        }
        IO.println("---------------------------");
    }

    IO.println();
}
