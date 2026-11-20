package service;

import model.RentalContract;
import repository.RentalContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RentalService {
    private final RentalContractRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

    public RentalService(RentalContractRepository repository) {
        this.repository = repository;
    }

    public void createContract(RentalContract contract) {
        if (contract.getStartDate().isAfter(contract.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        if (hasOverlappingContract(contract)) {
            logger.warn("Property is already rented for the requested period: propertyId={}",
                    contract.getProperty().getId()); throw new IllegalArgumentException("Property is already rented for this period");
        }

        try {
            repository.save(contract);
        } catch (Exception exception) {
            logger.error("Failed to save rental contract: id={}", contract.getId(), exception);
            throw exception;
        }

        logger.info("Rental contract created: id={}", contract.getId());
    }

    private boolean hasOverlappingContract(RentalContract newContract) {
        return repository.findByPropertyId(newContract.getProperty().getId())
                .stream().anyMatch(contract -> periodsOverlap(contract, newContract));
    }

    private boolean periodsOverlap(RentalContract first, RentalContract second) {
        return !first.getStartDate().isAfter(first.getEndDate()) &&
                !second.getStartDate().isAfter(second.getEndDate());
    }

}
