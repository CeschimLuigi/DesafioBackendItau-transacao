package com.luigiceschim.api_transacao.controller;

import com.luigiceschim.api_transacao.dto.EstatisticaResponseDTO;
import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;
import com.luigiceschim.api_transacao.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    public ResponseEntity<Void> criarTransacao(@RequestBody TransacaoRequestDTO requestDTO){
        service.adicionarTransacoes(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<EstatisticaResponseDTO> deletarTransacoes(){
        service.deletarTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
