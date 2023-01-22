package com.experiment.regular.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @Column(name = "person_id")
    private String id;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Instance> instances;

    private Timestamp timestamp;
}
