package br.com.Atividade_API_2.model;

import br.com.Atividade_API_2.enums.Prioridade;
import br.com.Atividade_API_2.enums.Status;
import lombok.*;

/**
 * Entidade que representa Tarefa
 * Possui atributos contendo id, titulo, descricao, prioridade e status que vão ser os dados que vamos manipular ao decorrer do projeto
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private Status status;
}