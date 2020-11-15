package securityWithSpring.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static securityWithSpring.security.UserPermissions.*;

public enum UserRoles {
    EMPLOYEE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE,
            DEPARTMENT_READ, DEPARTMENT_WRITE)),
    ADMINTRAIN(Sets.newHashSet(EMPLOYEE_READ, DEPARTMENT_READ));

    private final Set<UserPermissions> userPermissions;

    UserRoles(Set<UserPermissions> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<UserPermissions> getUserPermissions() {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permisisons = getUserPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permisisons.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permisisons;
    }
}
