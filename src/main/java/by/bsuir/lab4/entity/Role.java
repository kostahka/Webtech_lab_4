package by.bsuir.lab4.entity;

public enum Role {
    USER("user"),
    ADMIN("admin"),
    UnAUTH("unAuth");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
