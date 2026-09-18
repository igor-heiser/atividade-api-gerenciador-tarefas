package br.com.Atividade_API_2.dto;

import br.com.Atividade_API_2.enums.Prioridade;
import br.com.Atividade_API_2.enums.Status;
import br.com.Atividade_API_2.model.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaPutDto {
    Long id;
    String titulo;
    String descricao;
    Prioridade prioridade;
    Status status;

    public TarefaPutDto(Tarefa tarefa) {
    }
}