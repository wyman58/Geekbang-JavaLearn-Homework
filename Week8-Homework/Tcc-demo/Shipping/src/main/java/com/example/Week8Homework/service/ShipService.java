package com.example.Week8Homework.service;

import com.example.Week8Homework.entity.Shipper;
import com.example.Week8Homework.repository.ShipperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipService {

    private final ShipperRepository shipperRepository;

    public void updateShippingInfo(Long id){
        Optional<Shipper> shipper = shipperRepository.findById(id);
        if (shipper.isPresent()){
            shipper.get().setPhone("0449862811");
            shipperRepository.save(shipper.get());
        }

    }

}
