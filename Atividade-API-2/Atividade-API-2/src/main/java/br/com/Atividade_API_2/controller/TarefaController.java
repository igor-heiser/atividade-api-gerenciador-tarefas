package br.com.Atividade_API_2.controller;

import br.com.Atividade_API_2.dto.TarefaCreateRequest;
import br.com.Atividade_API_2.dto.TarefaPutDto;
import br.com.Atividade_API_2.dto.TarefaResponse;
import br.com.Atividade_API_2.model.Tarefa;
import br.com.Atividade_API_2.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

/**
 *
 *
 */

@RestController
@RequestMapping("/v1/api_tarefas")
public class TarefaController {

    TarefaRepository tarefaRepository;

    public TarefaController(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> cadastrar(@RequestBody TarefaCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(){
        return ResponseEntity.ok(tarefaRepository.listar());
    }

    @GetMapping("/{tarefaId}")
    public ResponseEntity<Optional<TarefaResponse>> buscarPorId(@PathVariable Long tarefaId){
        return ResponseEntity.ok().body(tarefaRepository.buscarPorId(tarefaId));
    }

    @PutMapping("/{tarefaId}")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable Long tarefaId, @RequestBody TarefaPutDto tarefa){
        Optional<TarefaResponse> lista =  tarefaRepository.atualizar(tarefaId, tarefa);

        if (lista.isPresent()) {
            return ResponseEntity.ok().body(lista.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{tarefaId}")
    public ResponseEntity<TarefaResponse> atualizarParcial(@PathVariable Long tarefaId){
        Optional<TarefaResponse> lista = tarefaRepository.atualizarParcial(tarefaId);

        if (lista.isPresent()){
            return ResponseEntity.ok().body(lista.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{tarefaId}")
    public ResponseEntity<Void> deletar(@PathVariable Long tarefaId){
        tarefaRepository.delete(tarefaId);
        return ResponseEntity.noContent().build();
    }

}