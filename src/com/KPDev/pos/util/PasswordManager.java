package com.KPDev.pos.util;

import org.mindrot.BCrypt;

public class PasswordManager {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
