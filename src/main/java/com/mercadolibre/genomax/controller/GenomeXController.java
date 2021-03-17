package com.mercadolibre.genomax.controller;

import com.mercadolibre.genomax.dto.DnaInDto;
import com.mercadolibre.genomax.service.GenomeXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/genome")
public class GenomeXController {

    @Autowired
    private GenomeXService genomeXService;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public Boolean addActivity(@RequestBody DnaInDto dna) {
        return genomeXService.isMutant(dna);
    }


}
