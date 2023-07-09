package br.com.uanderson.blackboxannotations.controller;

import br.com.uanderson.blackboxannotations.model.Funcionario;
import br.com.uanderson.blackboxannotations.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<Page<Funcionario>> listAllPageable(Pageable pageable){
        return new ResponseEntity<>(funcionarioService.listAllPageable(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id){
        return new ResponseEntity<>(funcionarioService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario){
        return new ResponseEntity<>(funcionarioService.save(funcionario), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        funcionarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> update(@RequestBody Funcionario funcionario){
        funcionarioService.update(funcionario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}//class
