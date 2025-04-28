package edu.phystech.hw2.contact;

public record Contact(String username, String email) implements Comparable<Contact> {

    public static final String UNKNOWN_EMAIL = "unknown@gmail.com";

    public Contact {
        // Валидация username
        if (username == null || username.isBlank()) {
            throw new InvalidContactFieldException("username");
        }

        // Валидация email
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidContactFieldException("email");
        }
        if (!email.matches("^[\\w.+\\-]+@gmail\\.com$")) {
            throw new InvalidContactFieldException("email");
        }
    }

    // Конструктор без email
    public Contact(String username) {
        this(username, UNKNOWN_EMAIL);
    }

    // Сравнение по длине username
    @Override
    public int compareTo(Contact other) {
        return Integer.compare(this.username.length(), other.username.length());
    }
}
