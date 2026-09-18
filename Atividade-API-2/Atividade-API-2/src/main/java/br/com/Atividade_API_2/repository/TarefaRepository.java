package br.com.Atividade_API_2.repository;

import br.com.Atividade_API_2.dto.TarefaCreateRequest;
import br.com.Atividade_API_2.dto.TarefaPutDto;
import br.com.Atividade_API_2.dto.TarefaResponse;
import br.com.Atividade_API_2.enums.Status;
import br.com.Atividade_API_2.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio responsavel pelo acesso dos dados das Tarefas e armazenar eles
 */

@Repository
public class TarefaRepository {

    public final List<Tarefa> listaTarefas = new ArrayList<>();
    public final AtomicLong sequencialId = new AtomicLong(1);

    /**
     * Metodo que cadastra uma tarefa
     *
     * @param request para fazer a requisição e chamar o titulo, descricao e prioridade
     * @return retorna a tarefa resposta com os dados da tarefa
     */
    public TarefaResponse cadastrar(TarefaCreateRequest request){
        Tarefa tarefa = Tarefa.builder()
                .id(sequencialId.getAndIncrement())
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .prioridade(request.getPrioridade())
                .status(Status.PENDENTE)
                .build();
        TarefaResponse tarefaResponse = new TarefaResponse(tarefa);
        listaTarefas.add(tarefa);
        return tarefaResponse;
    }

    /**
     * Lista todas as tarefas cadastradas
     *
     * @return retorna a lista de tarefas
     */

    public List<Tarefa> listar(){
        return listaTarefas;
    }

    /**
     * Busca uma tarefa pelo seu identificador unico
     *
     * @param id identificador da tarefa a ser buscada
     * @return retorna a tarefa especifica pelo seu identificador unico
     */

    public Optional<TarefaResponse> buscarPorId(Long id){
        return listaTarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .map(tarefa -> new TarefaResponse(tarefa))
                .findFirst();
    }

    /**
     * Atualiza a tarefa desejada pelo seu identificador unico
     *
     * @param id identificador do produto a ser atualizado
     * @param request objeto que tem os atributos da tarefa
     * @return retorna a tarefa com seus dados atualizados
     */

    public Optional<TarefaResponse> atualizar(Long id, TarefaPutDto request){
        return listaTarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .map(tarefa -> {
                    tarefa.setTitulo(request.getTitulo());
                    tarefa.setDescricao(request.getDescricao());
                    tarefa.setPrioridade(request.getPrioridade());
                    tarefa.setStatus(request.getStatus());
                    return new TarefaResponse(tarefa);
                }).findFirst();
    }

    /**
     * Atuliza apenas o status de uma tarefa
     *
     * @param id identificador unico da tarefa a ser parcialmente atualizada
     * @return retorna a tarefa com seu status atualizado
     */

    public Optional<TarefaResponse> atualizarParcial(Long id){
        return listaTarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .map(tarefa -> {
                    tarefa.setStatus(Status.CONCLUIDO);
                    return new TarefaResponse(tarefa);
                }).findFirst();
    }

    /**
     * Deleta a tarefa pelo seu identificador unico
     *
     * @param id identificador unico da tarefa a ser deletada
     */

    public void delete(Long id){
        listaTarefas.removeIf(tarefa -> tarefa.getId().equals(id));
    }

}