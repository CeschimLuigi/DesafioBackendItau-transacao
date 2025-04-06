package com.luigiceschim.api_transacao.interfaces;

import com.luigiceschim.api_transacao.dto.EstatisticaResponseDTO;

public interface IEstatisticaService {
    EstatisticaResponseDTO obterEstatistica(Integer intervaloBusca);
}
