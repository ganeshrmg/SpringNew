package com.duediligence.repository;
import com.duediligence.entity.CustomerDueEntity;

import java.util.Optional;

import org.springframework.data.repository.*;

public interface DueRepository extends CrudRepository<CustomerDueEntity,Long> {

	Optional<CustomerDueEntity> findByCustomerId(Long id);

}
