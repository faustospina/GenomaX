package com.mercadolibre.genomax.service;

import com.mercadolibre.genomax.common.NotificationCode;
import com.mercadolibre.genomax.entity.StatEntity;
import com.mercadolibre.genomax.exception.GenomeBusinessException;
import com.mercadolibre.genomax.repository.StatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * servicio para la capa de persistencia y consulta de la base de datos
 */
@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    /**
     * metodo para guardar la informacion de la cadena que se reconozca como
     * mutante o humano
     * @param statEntity
     * @throws GenomeBusinessException
     */
    @Override
    public void saveStat(StatEntity statEntity) throws GenomeBusinessException {
        StatEntity statEntityToSave = Optional.ofNullable(statEntity)
                .orElseThrow(() -> new GenomeBusinessException(NotificationCode.NULL_ENTITY));
        statRepository.save(statEntityToSave);
    }

    /**
     * metodo que consulta el numero de mutantes
     * @return
     * @throws GenomeBusinessException
     */
    @Override
    public int getNumMutant() throws GenomeBusinessException {
        return Optional
                .ofNullable(statRepository.ValueMutant())
                .orElseThrow(() -> new GenomeBusinessException(NotificationCode.EMPTY_DATA));
    }

    /**
     * metodo que consulta el numero de humanos
     * @return
     * @throws GenomeBusinessException
     */
    @Override
    public int getNumHuman() throws GenomeBusinessException {
        return Optional.
                ofNullable(statRepository.ValueHuman())
                .orElseThrow(() -> new GenomeBusinessException(NotificationCode.EMPTY_DATA));
    }
}
