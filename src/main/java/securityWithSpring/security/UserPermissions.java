package securityWithSpring.security;

public enum UserPermissions {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    DEPARTMENT_READ("department:read"),
    DEPARTMENT_WRITE("department:write");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
