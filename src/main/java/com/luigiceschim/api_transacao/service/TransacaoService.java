package com.luigiceschim.api_transacao.service;

import com.luigiceschim.api_transacao.dto.TransacaoRequestDTO;
import com.luigiceschim.api_transacao.exception.UnprocessableEntity;
import com.luigiceschim.api_transacao.interfaces.ITransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransacaoService implements ITransacaoService {

    private List<TransacaoRequestDTO>listaTransacoes = new ArrayList<>();


    @Override
    public List<TransacaoRequestDTO> getListaTransacoes() {
        return listaTransacoes;
    }

    @Override
    public void adicionarTransacoes(TransacaoRequestDTO dto){

        log.info("Iniciando processo de persistência de transações");

        if (dto.dataHora().isAfter(OffsetDateTime.now())){

            log.error("Data e hora estao fora dos paramentros(Data e hora estão no futuro)");
            throw new UnprocessableEntity("Data e hora maiores que a data e horas atuais!");
        }
        if (dto.valor() < 0){
            log.error("valor esta fora dos parametros(valor negativo)");
            throw new UnprocessableEntity("Valor da transação está negativo");
        }

        listaTransacoes.add(dto);

    }
    @Override
    public void deletarTransacoes(){

        log.info("Todas as informações foram apagadas com sucesso");

        listaTransacoes.clear();
    }





}
