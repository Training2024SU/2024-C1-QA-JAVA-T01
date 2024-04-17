import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.davidbonelo.utils.Permissions.validPermission;

class PermissionsTest {
    @Test
    void employeeIsValidReader() {
        User user = new User("pedro", "p@p", UserRole.EMPLOYEE);
        boolean valid = validPermission(user, UserRole.READER);
        Assertions.assertTrue(valid);
    }
}
