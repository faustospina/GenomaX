package com.mercadolibre.genomax.repository;

import com.mercadolibre.genomax.entity.StatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatRepository  extends CrudRepository<StatEntity, UUID> {

    @Query("SELECT SUM(s.mutant) FROM StatEntity s")
    Integer ValueMutant();

    @Query("SELECT SUM(s.human) FROM StatEntity s")
    Integer ValueHuman();


}
