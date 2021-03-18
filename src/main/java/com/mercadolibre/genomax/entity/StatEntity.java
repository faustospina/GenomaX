package com.mercadolibre.genomax.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stat")
public class StatEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "mutant_dna")
    private int mutant;
    @Column(name = "human_dna")
    private int human;




}
