package repository;

import model.RentalContract;

import java.util.ArrayList;
import java.util.List;

public class RentalContractRepositoryImpl implements RentalContractRepository {

    private final List<RentalContract> contracts = new ArrayList<>();

    @Override
    public void save(RentalContract contract) {
        contracts.add(contract);
    }

    @Override
    public List<RentalContract> findByPropertyId(int propertyId) {
        return contracts.stream()
                .filter(contract -> contract.getProperty().getId() == propertyId).toList();
    }

}
