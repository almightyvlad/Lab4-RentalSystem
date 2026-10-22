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

        // ИЗМЕНЕНИЕ 1:
        // Проверяем, что дата окончания не раньше даты начала.
        // isBefore() возвращает true, если endDate находится раньше startDate.
        if (endDate.isBefore(startDate)) {
            // ИЗМЕНЕНИЕ 2:
            // Выбрасываем исключение, потому что такой договор некорректен.
            throw new IllegalArgumentException(
                    "Дата окончания договора не может быть раньше даты начала"
            );
        }

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