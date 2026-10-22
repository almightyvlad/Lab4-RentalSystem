package repository;

import model.RentalContract;

import java.util.List;

public interface RentalContractRepository {
    void save(RentalContract contract);

    List<RentalContract> findByPropertyId(int propertyId);
}
