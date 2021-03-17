package com.mercadolibre.genomax.controller;

import com.mercadolibre.genomax.common.ApiResponseDF;
import com.mercadolibre.genomax.common.Notification;
import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.service.GenomeXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/genome")
public class GenomeXController {

    private static final String API_NAME="GenomeXController";

    @Autowired
    private GenomeXService genomeXService;

    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseDF<Boolean> addActivity(@RequestBody DnaInDto dna) throws GenomeBusinessException {
        return new ApiResponseDF(genomeXService.isMutant(dna),onSuccessNotification());
    }



    private Notification onSuccessNotification() {
        return new Notification.Builder(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.toString())
                .reference(UUID.randomUUID().toString()).source(API_NAME).build();
    }



}
