package com.example.cepservice.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.cepservice.dtos.CepRequestDTO;
import org.junit.jupiter.api.Assertions;
import com.example.cepservice.dtos.OutPutCepRequestDTO;
import com.example.cepservice.services.CepService;
import java.lang.IllegalArgumentException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CepService.class })
public class CepServiceTest {

    @Autowired
    CepService service;

    @Test
    public void TestarService() {
        try {
            CepRequestDTO data = new CepRequestDTO();
            data.setCep("01153 000");
            OutPutCepRequestDTO output = service.CepGetValue(data);
            Assertions.assertEquals(output.getFrete(), 7.85);
        } catch (Exception exception) {
            Assertions.assertEquals(2, 3);
        }
    }

    @Test
    public void TestarServiceValidation() {
        CepRequestDTO data = new CepRequestDTO();
        data.setCep("abc - deg");
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            OutPutCepRequestDTO output = service.CepGetValue(data);
        });
        Assertions.assertEquals("Cep value is not valid", exception.getMessage());
    }
}
