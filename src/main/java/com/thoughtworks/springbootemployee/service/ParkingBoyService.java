package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.ParkingBoy;
import com.thoughtworks.springbootemployee.repository.ParkingBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParkingBoyService {
    @Autowired
    private ParkingBoyRepository parkingboyRepository;

    public List<ParkingBoy> getAll() {
        return parkingboyRepository.findAll();
    }

    public ParkingBoy createParkingBoy(ParkingBoy parkingboy) {
        return parkingboyRepository.save(parkingboy);
    }
}
