package com.luigiceschim.api_transacao.service;

import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;
import com.luigiceschim.api_transacao.interfaces.ITransacaoService;

import com.luigiceschim.api_transacao.repository.TransacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransacaoServiceImpl implements ITransacaoService {

    private final TransacaoRepository repository;

    public TransacaoServiceImpl(TransacaoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<TransacaoRequestDTO> getListaTransacoes() {
        return repository.getListaTransacoes();
    }

    @Override
    public void adicionarTransacoes(TransacaoRequestDTO dto){
    repository.adicionarTransacoes(dto);


    }
    @Override
    public void deletarTransacoes(){
        repository.deletarTransacoes();
    }





}
