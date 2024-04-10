import com.davidbonelo.models.Borrowing;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingTest {

    @Test
    public void returnDateMoreThan15DaysThrow() {
        LocalDate returnDate16 = LocalDate.now().plusDays(16);
        User user = new User(1, "Pedro", "p@p", UserRole.READER);
        List<LibraryItem> borrowingItems = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Borrowing(borrowingItems, returnDate16, user));
    }

    @Test
    public void borrowingFor15Days() {
        LocalDate returnDate15 = LocalDate.now().plusDays(15);
        User user = new User(1, "Pedro", "p@p", UserRole.READER);
        List<LibraryItem> borrowingItems = new ArrayList<>();
        Borrowing newBorrowing = new Borrowing(borrowingItems, returnDate15, user);

        Assertions.assertInstanceOf(Borrowing.class, newBorrowing);
    }
}
