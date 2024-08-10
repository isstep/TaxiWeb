package com.example.taxiwebapp.EntityRepositories;

import com.example.taxiwebapp.EntityClasses.RolesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<RolesEntity, Long> {
RolesEntity findRolesEntityById(Long id);
}