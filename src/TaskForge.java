// TaskForge - Do Código ao Contrato
// Código final da aula 3.1: visibilidade + enum + LISTAS.
// (classe abstrata, interface e o criarTarefa() ficaram pra aula 3.2)

import src.Tarefa;
import src.TarefaComPrazo;
import src.TarefaSimples;
import src.TarefaStatus;

import java.util.ArrayList;
import java.util.List;

void main() {
    IO.println("=== TaskForge ===");

    // A LISTA: guarda quantas tarefas a gente quiser.
    // Adeus tarefa1, tarefa2, tarefa3... e adeus contador na mao.
    List<Tarefa> listaTarefa = new ArrayList<>();

    listaTarefa.add(new TarefaSimples("Estudar Java", "Estudar Collections", 1,
            "Lander Andrade", 2, TarefaStatus.EM_ANDAMENTO));
    listaTarefa.add(new TarefaComPrazo("Estudar Frontend", "Estudar React", 3,
            "Lander Andrade", 2, TarefaStatus.PENDENTE, 3));
    listaTarefa.add(new TarefaSimples("Revisar PRs", "Revisar as entregas da turma", 2,
            "Lander Andrade", 1, TarefaStatus.CONCLUIDA));

    // size() ja sabe contar — nao precisa de int contador
    IO.println("Total de tarefas: " + listaTarefa.size());
    IO.println();

    // get(i) pega pela POSICAO — e a posicao comeca em ZERO
    IO.println("A primeira da lista e: " + listaTarefa.get(0).getNome());
    IO.println("A ultima da lista e:   " + listaTarefa.get(listaTarefa.size() - 1).getNome());
    IO.println();

    // FOR TRADICIONAL: usa < e nao <=, senao estoura a lista
    IO.println("--- percorrendo com o for tradicional ---");
    for (int i = 0; i < listaTarefa.size(); i++) {
        IO.println(i + " - " + listaTarefa.get(i).getNome());
    }
    IO.println();

    // ENHANCED FOR (for-each): mais bonito, e o que a gente usa no dia a dia.
    // Cada tarefa imprime do SEU jeito — polimorfismo dentro da lista.
    IO.println("--- percorrendo com o enhanced for ---");
    for (Tarefa t : listaTarefa) {
        t.resumo();
        IO.println("---------------------------");
    }
    IO.println();

    // REMOVE: pela posicao...
    listaTarefa.remove(0);
    IO.println("Depois do remove(0): " + listaTarefa.size() + " tarefa(s)");

    // ...ou pelo proprio objeto
    Tarefa ultima = listaTarefa.get(listaTarefa.size() - 1);
    listaTarefa.remove(ultima);
    IO.println("Depois do remove(objeto): " + listaTarefa.size() + " tarefa(s)");

    for (Tarefa t : listaTarefa) {
        IO.println("Ainda na lista: " + t.getNome());
    }
}
