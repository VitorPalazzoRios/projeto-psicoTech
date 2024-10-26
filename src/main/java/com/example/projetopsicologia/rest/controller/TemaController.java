package com.example.projetopsicologia.rest.controller;

import com.example.projetopsicologia.domain.entity.Tema;
import com.example.projetopsicologia.domain.repository.TemaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/temas")
public class TemaController {

    private final TemaRepository temaRepository;

    public TemaController(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @GetMapping("/{id}")
    public Tema getTemaById(@PathVariable Integer id) {
        return temaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado"));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Tema save(@RequestBody Tema tema) {
        return temaRepository.save(tema);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        temaRepository.findById(id).map(tema -> {
            temaRepository.delete(tema);
            return tema;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Tema tema) {
        temaRepository.findById(id).map(temaExistente -> {
            tema.setCod_tema(temaExistente.getCod_tema());
            temaRepository.save(tema);
            return temaExistente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado"));
    }
}
