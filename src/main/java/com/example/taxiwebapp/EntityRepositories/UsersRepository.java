package com.example.taxiwebapp.EntityRepositories;

import com.example.taxiwebapp.EntityClasses.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends CrudRepository<UsersEntity,Long> {
    UsersEntity findByUsername(String username);

    UsersEntity findByPhoneNumber(String phoneNumber);

    UsersEntity findByUserEmail(String userEmail);

    Optional<UsersEntity> findById(Long id);
    @Override
    List<UsersEntity> findAll();
}
