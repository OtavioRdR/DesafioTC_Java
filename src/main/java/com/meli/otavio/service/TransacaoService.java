package com.meli.otavio.service;

import com.meli.otavio.model.Transacao;
import com.meli.otavio.dto.EstatisticasTransacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransacaoService {

    private final List<Transacao> transacoes = new ArrayList<>();

    public boolean addTransacao(Transacao transacao) {
        OffsetDateTime now = OffsetDateTime.now();
        if (transacao.getDataHora().isAfter(now) || transacao.getValor() < 0) {
            return false;
        }
        synchronized (transacoes) {
            transacoes.add(transacao);
        }
        return true;
    }

    public void deleteTransacoes() {
        synchronized (transacoes) {
            transacoes.clear();
        }
    }

    public EstatisticasTransacao getEstatisticas() {
        OffsetDateTime now = OffsetDateTime.now();
        synchronized (transacoes) {
            DoubleSummaryStatistics stats = transacoes.stream()
                    .filter(t -> t.getDataHora().isAfter(now.minus(60, ChronoUnit.SECONDS)))
                    .mapToDouble(Transacao::getValor)
                    .summaryStatistics();
            return new EstatisticasTransacao(
                    stats.getCount(),
                    stats.getSum(),
                    stats.getAverage(),
                    stats.getMin(),
                    stats.getMax()
            );
        }
    }
}
