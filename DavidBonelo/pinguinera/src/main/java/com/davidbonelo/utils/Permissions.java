package com.davidbonelo.utils;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;

import java.util.ResourceBundle;

public class Permissions {
    public static boolean validMenuAccess(User user, UserRole requiredRole) {
        boolean valid = validPermission(user, requiredRole);
        if (!valid) {
            System.out.println(ResourceBundle.getBundle("messages").getString("unknownOption"));
        }
        return valid;
    }

    public static boolean validPermission(User user, UserRole requiredRole) {
        if (user == null) return false;
        if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
            return true;
        }
        return user.getRole().equals(requiredRole);
    }
}
