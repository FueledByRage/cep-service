package com.example.cepservice.services;

import org.springframework.stereotype.Service;

import com.example.cepservice.data.Fretes;
import com.example.cepservice.dtos.CepRequestDTO;
import com.example.cepservice.dtos.OutPutCepRequestDTO;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.IllegalArgumentException;

@Service
public class CepService {

    public OutPutCepRequestDTO CepGetValue(CepRequestDTO dto) throws Exception {
        String cep = dto.getCep().replaceAll("-", "").replaceAll(" ", "");
        if (!isValidCep(cep))
            throw new IllegalArgumentException("Cep value is not valid");

        URL url = new URL("https://viacep.com.br/ws/" + cep + "/json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        if (connection.getResponseCode() >= 400) {
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        String inputLine;
        StringBuffer res = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            res.append(inputLine);
        }
        in.close();
        if (res.toString().contains("erro")) {
            throw new RuntimeException("CEP não encontrado");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        OutPutCepRequestDTO output = objectMapper.readValue(res.toString(), OutPutCepRequestDTO.class);
        output = this.setFrete(output);
        return output;
    }

    private boolean isValidCep(String cep) {
        String regexCep = "^\\d{5}-?\\d{3}$";

        return cep.matches(regexCep);
    }

    private OutPutCepRequestDTO setFrete(OutPutCepRequestDTO data) {
        Fretes fretes = new Fretes();
        data.setFrete(fretes.values.get(data.getUf()));
        return data;
    }
}
