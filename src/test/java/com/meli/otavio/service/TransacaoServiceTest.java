package com.meli.otavio.service;

import com.meli.otavio.model.Transacao;
import com.meli.otavio.dto.EstatisticasTransacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoServiceTest {

    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        transacaoService = new TransacaoService();
    }

    @Test
    void addTransacaoTest() {
        Transacao transacao = new Transacao();
        transacao.setValor(100);
        transacao.setDataHora(OffsetDateTime.now().minusSeconds(30));

        assertTrue(transacaoService.addTransacao(transacao));
    }

    @Test
    void addTransacaoFutureTest() {
        Transacao transacao = new Transacao();
        transacao.setValor(100);
        transacao.setDataHora(OffsetDateTime.now().plusSeconds(30));

        assertFalse(transacaoService.addTransacao(transacao));
    }

    @Test
    void getEstatisticasTest() {
        Transacao transacao1 = new Transacao();
        transacao1.setValor(100);
        transacao1.setDataHora(OffsetDateTime.now().minusSeconds(30));

        Transacao transacao2 = new Transacao();
        transacao2.setValor(200);
        transacao2.setDataHora(OffsetDateTime.now().minusSeconds(50));

        transacaoService.addTransacao(transacao1);
        transacaoService.addTransacao(transacao2);

        EstatisticasTransacao estatisticas = transacaoService.getEstatisticas();

        assertEquals(2, estatisticas.getCount());
        assertEquals(300, estatisticas.getSum());
        assertEquals(150, estatisticas.getAvg());
        assertEquals(100, estatisticas.getMin());
        assertEquals(200, estatisticas.getMax());
    }
}
