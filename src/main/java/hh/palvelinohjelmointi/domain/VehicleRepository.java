package hh.palvelinohjelmointi.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findByMake(String make);
    Vehicle findByRegNo(String regNo);
    Vehicle findByVehicleid(long vehicleid);
}