package br.com.Atividade_API_2.dto;

import br.com.Atividade_API_2.enums.Prioridade;
import lombok.Getter;
import lombok.Setter;

/**
 * Dados necessarios para cadastrar uma nova tarefa
 *
 *
 */

@Getter
@Setter
public class TarefaCreateRequest{
    String titulo;
    String descricao;
    Prioridade prioridade;
}