package com.meli.otavio.controller;

import com.meli.otavio.model.Transacao;
import com.meli.otavio.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> receberTransacao(@RequestBody Transacao transacao) {
        if (transacaoService.isTransacaoValida(transacao)) {
            transacaoService.addTransacao(transacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> limparTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<?> getEstatisticas() {
        return ResponseEntity.ok(transacaoService.calcularEstatisticas());
    }
}
