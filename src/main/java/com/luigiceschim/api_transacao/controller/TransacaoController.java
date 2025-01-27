package com.luigiceschim.api_transacao.controller;

import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;
import com.luigiceschim.api_transacao.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    public void criarTransacao(@RequestBody TransacaoRequestDTO requestDTO){
        service.adicionarTransacoes(requestDTO);
    }

    @DeleteMapping
    public void deletarTransacoes(){
        service.deletarTransacoes();
    }
}
