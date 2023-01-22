package com.experiment.regular.repository;

import com.experiment.regular.entity.Instance;
import com.experiment.regular.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {

}
