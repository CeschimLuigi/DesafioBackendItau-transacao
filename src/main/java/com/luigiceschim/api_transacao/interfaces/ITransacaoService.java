package com.luigiceschim.api_transacao.interfaces;

import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;

import java.util.List;

public interface ITransacaoService {
    List<TransacaoRequestDTO> getListaTransacoes();
    void adicionarTransacoes(TransacaoRequestDTO dto);
    void deletarTransacoes();

}
