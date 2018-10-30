package hh.palvelinohjelmointi.domain;

import java.util.Date;
import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorkdayRepository extends CrudRepository<Workday, Long> {

    List<Workday> findByDateAdded(Date dateAdded);
    
}