package com.example.taxiwebapp.EntityRepositories;

import com.example.taxiwebapp.EntityClasses.StatusEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository <StatusEntity,Long>{

List<StatusEntity> findAll();
}
