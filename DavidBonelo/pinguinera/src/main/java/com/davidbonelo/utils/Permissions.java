package com.davidbonelo.utils;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;

public class Permissions {
    public static boolean validMenuAccess(User user, UserRole requiredRole) {
        boolean valid = validPermission(user, requiredRole);
        if (!valid) {
            System.out.println("Unknown menu option");
        }
        return valid;
    }

    public static boolean validPermission(User user, UserRole requiredRole) {
        if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
            return true;
        }
        return user.getRole().equals(requiredRole);
    }
}
