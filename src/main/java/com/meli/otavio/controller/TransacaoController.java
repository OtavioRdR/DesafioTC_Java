package com.meli.otavio.controller;

import com.meli.otavio.model.Transacao;
import com.meli.otavio.service.TransacaoService;
import com.meli.otavio.dto.EstatisticasTransacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> addTransacao(@RequestBody Transacao transacao) {
        if (transacaoService.addTransacao(transacao)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransacoes() {
        transacaoService.deleteTransacoes();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticasTransacao> getEstatisticas() {
        EstatisticasTransacao estatisticas = transacaoService.getEstatisticas();
        return new ResponseEntity<>(estatisticas, HttpStatus.OK);
    }
}
