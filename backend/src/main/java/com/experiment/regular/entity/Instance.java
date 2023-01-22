package com.experiment.regular.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "instance")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;
    private String instance_index;
    private Double posX;
    private Double posY;
    private Double velX;
    private Double velY;
    private Double confidence;

}
