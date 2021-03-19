package com.mercadolibre.genomax.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * clase que mapea de las propiedades los codigos
 * ejemplo (AAAA,CCCC,TTTT,GGGG)
 */
@Data
@Component
@ConfigurationProperties(prefix = "genome")
public class DnaUtil {

    private List<Code> dna;


    @Data
    public static class Code {
        private String code;
    }
}

