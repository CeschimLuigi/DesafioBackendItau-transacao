package com.luigiceschim.api_transacao.service;

import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;
import com.luigiceschim.api_transacao.dto.EstatisticaResponseDTO;
import com.luigiceschim.api_transacao.interfaces.ITransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@Slf4j
public class EstatisticaService {

    private final ITransacaoService service;

    @Autowired
    public EstatisticaService(ITransacaoService service) {
        this.service = service;
    }

    public EstatisticaResponseDTO obterEstatistica(Integer intervaloBusca){
        long startTime = System.currentTimeMillis();

        log.info("Iniciada a busca das estatísticas neste intervalo de tempo: {} Segundos", intervaloBusca);

        List<TransacaoRequestDTO>listaTransacoes = service.getListaTransacoes();

        List<TransacaoRequestDTO> transacoesFiltradas = listaTransacoes
                .stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(OffsetDateTime.now()
                                .minusSeconds(intervaloBusca))).toList();



        if (transacoesFiltradas.isEmpty()){

            log.info("Não há registros neste intervalo de tempo: {} Segundos", intervaloBusca);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            log.info("Tempo gasto para calcular as estatísticas: {} ms", duration);

            return new EstatisticaResponseDTO(0,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatistica = transacoesFiltradas
                .stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Tempo gasto para calcular as estatísticas: {} ms", duration);

        log.info("Estatísticas processadas com sucesso");
        return new  EstatisticaResponseDTO((int) estatistica.getCount(),
                estatistica.getSum(),
                estatistica.getAverage(),
                estatistica.getMin(),
                estatistica.getMax());

    }

}
