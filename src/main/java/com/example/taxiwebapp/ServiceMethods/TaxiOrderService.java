package com.example.taxiwebapp.ServiceMethods;

import com.example.taxiwebapp.EntityRepositories.TaxiOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class TaxiOrderService {

    TaxiOrderRepository taxiOrderRepository;

    public TaxiOrderService(TaxiOrderRepository taxiOrderRepository){this.taxiOrderRepository=taxiOrderRepository;}
}
