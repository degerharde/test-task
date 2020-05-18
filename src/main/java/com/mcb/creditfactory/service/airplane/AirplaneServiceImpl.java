package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.IModelObject;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService{

    private ExternalApproveService approveService;

    private AirplaneRepository airplaneRepository;

    private AssessmentRepository assessmentRepository;

    @Autowired
    public AirplaneServiceImpl(ExternalApproveService approveService,
                               AirplaneRepository airplaneRepository,
                               AssessmentRepository assessmentRepository) {
        this.approveService = approveService;
        this.airplaneRepository = airplaneRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public boolean approve(Collateral dto) {
        return approveService.approve(new AirplaneAdapter((AirplaneDto) dto)) == 0;
    }

    @Override
    public IModelObject save(IModelObject airplane) {
        return airplaneRepository.save((Airplane) airplane);
    }


    @Override
    public Optional<Airplane> load(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public IModelObject fromDto(Collateral dto) {
        AirplaneDto tmp = (AirplaneDto) dto;
        return new Airplane(
                tmp.getId(),
                tmp.getBrand(),
                tmp.getModel(),
                tmp.getManufacturer(),
                tmp.getYear(),
                tmp.getFuelCapacity(),
                tmp.getSeats(),
                tmp.getValue()
        );
    }

    @Override
    public Collateral toDTO(IModelObject airplane) {
        Airplane tmp = (Airplane) airplane;
        return new AirplaneDto(
                tmp.getId(),
                tmp.getBrand(),
                tmp.getModel(),
                tmp.getManufacturer(),
                tmp.getYear(),
                tmp.getFuelCapacity(),
                tmp.getSeats(),
                tmp.getValue()
        );
    }

    @Override
    public Long getId(IModelObject airplane) {
        return airplane.getId();
    }
}
