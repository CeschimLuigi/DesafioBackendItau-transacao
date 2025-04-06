package com.luigiceschim.api_transacao.repository;

import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;

import java.util.List;


public interface TransacaoRepository {
    List<TransacaoRequestDTO> getListaTransacoes();
    void adicionarTransacoes(TransacaoRequestDTO dto);
    void deletarTransacoes();
}
