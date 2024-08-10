package com.example.taxiwebapp.EntityRepositories;

import com.example.taxiwebapp.EntityClasses.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity,Long> {
}
