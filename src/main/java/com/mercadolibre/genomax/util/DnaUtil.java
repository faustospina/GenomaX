package com.mercadolibre.genomax.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "genome")
public class DnaUtil {

    private List<Code> dna;

    public List<Code> getDna() {
        return dna;
    }

    @Data
    public static class Code {
        private String code;
    }
}

