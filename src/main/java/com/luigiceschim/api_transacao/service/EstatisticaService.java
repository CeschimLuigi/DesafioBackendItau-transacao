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

        log.info("Iniciada a busca das estatistícas neste intervalo de tempo: {} Segundos", intervaloBusca);

        List<TransacaoRequestDTO>listaTransacoes = service.getListaTransacoes();

        DoubleSummaryStatistics estatistica = listaTransacoes
                .stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(OffsetDateTime.now()
                                .minusSeconds(intervaloBusca)))
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        if (estatistica.getCount() == 0){

            log.info("Não há registros neste intervalo de tempo: {} Segundos", intervaloBusca);

            return new EstatisticaResponseDTO(0,0.0,0.0,0.0,0.0);
        }

        log.info("Estatistícas processadas com sucesso");
        return new  EstatisticaResponseDTO((int) estatistica.getCount(),
                estatistica.getSum(),
                estatistica.getAverage(),
                estatistica.getMin(),
                estatistica.getMax());

    }

}
