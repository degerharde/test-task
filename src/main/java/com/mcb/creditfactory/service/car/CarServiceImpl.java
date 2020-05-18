package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.IModelObject;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    private ExternalApproveService approveService;

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(ExternalApproveService approveService, CarRepository carRepository) {
        this.approveService = approveService;
        this.carRepository = carRepository;
    }

    @Override
    public boolean approve(Collateral dto) {
        return approveService.approve(new CarAdapter((CarDto)dto)) == 0;
    }

    @Override
    public IModelObject save(IModelObject car) {
        return carRepository.save((Car)car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public IModelObject fromDto(Collateral dto) {
        CarDto tmp = (CarDto) dto;
        return new Car(
                tmp.getId(),
                tmp.getBrand(),
                tmp.getModel(),
                tmp.getPower(),
                tmp.getYear(),
                tmp.getValue()
        );
    }

    @Override
    public Collateral toDTO(IModelObject car) {
        Car tmp = (Car) car;
        return new CarDto(
                tmp.getId(),
                tmp.getBrand(),
                tmp.getModel(),
                tmp.getPower(),
                tmp.getYear(),
                tmp.getValue()
        );
    }

    @Override
    public Long getId(IModelObject car) {
        return car.getId();
    }
}
