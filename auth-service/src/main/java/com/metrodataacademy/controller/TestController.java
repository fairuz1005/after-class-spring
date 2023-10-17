package com.metrodataacademy.controller;

import com.metrodataacademy.domain.constant.ConstantVariables;
import com.metrodataacademy.domain.dto.response.ResTemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    Environment environment;

    @GetMapping("/get-port")
    public ResponseEntity<ResTemplateDto> getPort() {
        return new ResponseEntity<>(new ResTemplateDto(environment.getProperty("local.server.port"), ConstantVariables.SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
