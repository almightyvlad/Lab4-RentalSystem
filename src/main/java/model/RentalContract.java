package model;

import java.time.LocalDate;

public class RentalContract {
    private final int id;
    private final Property property;
    private final Owner owner;
    private final Tenant tenant;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public RentalContract(
            int id,
            Property property,
            Owner owner,
            Tenant tenant,
            LocalDate startDate,
            LocalDate endDate) {

        this.id = id;
        this.property = property;
        this.owner = owner;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public Owner getOwner() {
        return owner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}
