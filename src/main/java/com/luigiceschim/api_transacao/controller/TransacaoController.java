package com.luigiceschim.api_transacao.controller;

import com.luigiceschim.api_transacao.dto.EstatisticaResponseDTO;
import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;
import com.luigiceschim.api_transacao.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsavel por adicionar transações")
    @ApiResponses(value ={
            @ApiResponse (responseCode = "201", description = "Transação gravada com sucesso"),
            @ApiResponse(responseCode = "422",description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500",description = "Erro interno do servidor")

    })
    public ResponseEntity<Void> criarTransacao(@RequestBody TransacaoRequestDTO requestDTO){
        service.adicionarTransacoes(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsavel por deletar transações")
    @ApiResponses(value ={
            @ApiResponse (responseCode = "200", description = "Transação deletadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500",description = "Erro interno do servidor")

    })
    public ResponseEntity<Void> deletarTransacoes(){
        service.deletarTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
