package com.mercadolibre.genomax.controller;

import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.service.GenomeXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class GenomeXController {

    @Autowired
    private GenomeXService genomeXService;

    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isMutant(@RequestBody DnaInDto dna) {
        try {
            return new ResponseEntity<>(genomeXService.isMutant(dna) ? HttpStatus.OK : HttpStatus.FORBIDDEN);
        } catch (GenomeBusinessException g) {
            return new ResponseEntity<>(g.getMessage(), g.getErrorCode().getHttpStatus());
        }
    }

    @GetMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStats() {
        try {
            return new ResponseEntity<>(genomeXService.stats(), HttpStatus.OK);
        } catch (GenomeBusinessException g) {
            return new ResponseEntity<>(g.getMessage(), g.getErrorCode().getHttpStatus());
        }

    }

}
