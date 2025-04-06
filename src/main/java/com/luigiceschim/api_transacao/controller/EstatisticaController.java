package com.luigiceschim.api_transacao.controller;

import com.luigiceschim.api_transacao.dto.EstatisticaResponseDTO;
import com.luigiceschim.api_transacao.interfaces.IEstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estatistica")
public class EstatisticaController {


    private final IEstatisticaService service;

    public EstatisticaController(IEstatisticaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar estatisticas")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Estatisticas obtidas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de busca de estatistic"),
            @ApiResponse(responseCode = "500",description = "Erro interno do servidor")

    })
    public ResponseEntity<EstatisticaResponseDTO> obterEstatistica(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca){
        var result = service.obterEstatistica(intervaloBusca);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
