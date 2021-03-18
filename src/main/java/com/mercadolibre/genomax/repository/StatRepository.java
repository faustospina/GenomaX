package com.mercadolibre.genomax.repository;

import com.mercadolibre.genomax.dto.IResult;
import com.mercadolibre.genomax.dto.StatDto;
import com.mercadolibre.genomax.entity.StatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StatRepository  extends CrudRepository<StatEntity, UUID> {

    @Query("SELECT SUM(s.mutant) FROM StatEntity s")
    int ValueMutant();

    @Query("SELECT SUM(s.human) FROM StatEntity s")
    int ValueHuman();


}