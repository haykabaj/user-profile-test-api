package user.profile.demo.api.model.enums;

public enum Permission {
    READ("READ"), CHANGE("CHANGE");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
