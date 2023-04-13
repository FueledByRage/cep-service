package com.example.cepservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cepservice.dtos.CepRequestDTO;
import com.example.cepservice.dtos.OutPutCepRequestDTO;
import com.example.cepservice.services.CepService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Cepcontroller {

    @Autowired
    CepService cepService;

    @PostMapping("/cep-service")
    public ResponseEntity<Object> CepService(@RequestBody CepRequestDTO dto) {
        try {
            OutPutCepRequestDTO res = cepService.CepGetValue(dto);
            return new ResponseEntity<Object>(res, HttpStatus.valueOf(200));

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.valueOf(403));

        }
    }
}
