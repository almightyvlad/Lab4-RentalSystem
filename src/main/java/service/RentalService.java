package service;

import model.RentalContract;
import repository.RentalContractRepository;

public class RentalService {
    private final RentalContractRepository repository;

    public RentalService(RentalContractRepository repository) {
        this.repository = repository;
    }

    public void createContract(RentalContract contract) {
        if (contract.getStartDate().isAfter(contract.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        if (hasOverlappingContract(contract)) {
            throw new IllegalArgumentException("Property is already rented for this period");
        }

        repository.save(contract);
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
