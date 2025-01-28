package com.luigiceschim.api_transacao.controller;

import com.luigiceschim.api_transacao.dto.EstatisticaResponseDTO;
import com.luigiceschim.api_transacao.service.EstatisticaService;
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

    @Autowired
    private EstatisticaService service;

    @GetMapping
    public ResponseEntity<EstatisticaResponseDTO> obterEstatistica(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca){
        var result = service.obterEstatistica(intervaloBusca);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
