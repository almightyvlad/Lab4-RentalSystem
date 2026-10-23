import model.Owner;
import model.Property;
import model.RentalContract;
import model.Tenant;
import org.junit.jupiter.api.Test;
import repository.RentalContractRepository;
import repository.RentalContractRepositoryImpl;
import service.RentalService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

public class RentalServiceTest {

    @Test
    void testCreateContract() {

        RentalContractRepository repository =
                new RentalContractRepositoryImpl();

        RentalService service =
                new RentalService(repository);

        Property property =
                new Property(1, "Minsk, Lenina 10");

        Owner owner =
                new Owner(1, "Ivan");

        Tenant tenant =
                new Tenant(1, "Petr");

        RentalContract contract =
                new RentalContract(
                        1,
                        property,
                        owner,
                        tenant,
                        LocalDate.of(2026, 9, 1),
                        LocalDate.of(2026, 9, 10)
                );

        assertDoesNotThrow(
                () -> service.createContract(contract)
        );
    }

    @Test
    void testOverlappingPeriod() {

        RentalContractRepository repository =
                new RentalContractRepositoryImpl();

        RentalService service =
                new RentalService(repository);

        Property property =
                new Property(1, "Minsk, Lenina 10");

        Owner owner =
                new Owner(1, "Ivan");

        Tenant firstTenant =
                new Tenant(1, "Petr");

        Tenant secondTenant =
                new Tenant(2, "Alex");

        RentalContract firstContract =
                new RentalContract(
                        1,
                        property,
                        owner,
                        firstTenant,
                        LocalDate.of(2026, 9, 1),
                        LocalDate.of(2026, 9, 10)
                );

        RentalContract secondContract =
                new RentalContract(
                        2,
                        property,
                        owner,
                        secondTenant,
                        LocalDate.of(2026, 9, 5),
                        LocalDate.of(2026, 9, 15)
                );

        service.createContract(firstContract);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.createContract(secondContract)
        );
    }

    @Test
    void testAnotherProperty() {

        RentalContractRepository repository =
                new RentalContractRepositoryImpl();

        RentalService service =
                new RentalService(repository);

        Property firstProperty =
                new Property(1, "Minsk, Lenina 10");

        Property secondProperty =
                new Property(2, "Minsk, Pobediteley 20");

        Owner owner =
                new Owner(1, "Ivan");

        Tenant firstTenant =
                new Tenant(1, "Petr");

        Tenant secondTenant =
                new Tenant(2, "Alex");

        RentalContract firstContract =
                new RentalContract(
                        1,
                        firstProperty,
                        owner,
                        firstTenant,
                        LocalDate.of(2026, 9, 1),
                        LocalDate.of(2026, 9, 10)
                );

        RentalContract secondContract =
                new RentalContract(
                        2,
                        secondProperty,
                        owner,
                        secondTenant,
                        LocalDate.of(2026, 9, 5),
                        LocalDate.of(2026, 9, 15)
                );

        service.createContract(firstContract);

        assertDoesNotThrow(
                () -> service.createContract(secondContract)
        );
    }

    @Test
    void testEndDateBeforeStartDate() {
        Property property = new Property(1, "Minsk, Lenina 10");
        Owner owner = new Owner(1, "Ivan");
        Tenant tenant = new Tenant(1, "Petr");

        assertThrows(
                IllegalArgumentException.class,
                () -> new RentalContract(
                        1,
                        property,
                        owner,
                        tenant,
                        LocalDate.of(2026, 10, 10),
                        LocalDate.of(2026, 11, 1)
                )
        );
    }
}