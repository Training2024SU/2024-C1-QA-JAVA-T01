import com.davidbonelo.models.Borrowing;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BorrowingTest {

    @Test
    void returnDateMoreThan15DaysThrow() {
        LocalDate returnDate16 = LocalDate.now().plusDays(16);
        User user = new User("Pedro", "p@p", UserRole.READER);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Borrowing(returnDate16,
                user));
    }

    @Test
    void borrowingFor15Days() {
        LocalDate returnDate15 = LocalDate.now().plusDays(15);
        User user = new User("Pedro", "p@p", UserRole.READER);
        Borrowing newBorrowing = new Borrowing(returnDate15, user);

        Assertions.assertInstanceOf(Borrowing.class, newBorrowing);
    }
}
