package com.example.taxiwebapp.EntityRepositories;

import com.example.taxiwebapp.EntityClasses.TaxiOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaxiOrderRepository extends CrudRepository<TaxiOrderEntity,Long> {
    TaxiOrderEntity findTaxiOrderEntityById(Long id);
    List<TaxiOrderEntity> findTaxiOrderEntitiesBySenderId(Long id);

    List<TaxiOrderEntity> findTaxiOrderEntitiesByRecipientId(Long id);

    List<TaxiOrderEntity> findTaxiOrderEntitiesByRecipientIdAndStatusId(Long id,Long statusId);

    List<TaxiOrderEntity> findTaxiOrderEntitiesByStatusId(Long statusId);
}
