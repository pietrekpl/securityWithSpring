package securityWithSpring.userauth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import securityWithSpring.security.UserRoles;

import java.util.List;
import java.util.Optional;

import static securityWithSpring.security.UserRoles.*;



@Repository("demo")
public class DemoUserDaoService implements UserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DemoUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserAuthorization> selectUserAuthorizationByUsername(String username) {
        return getUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<UserAuthorization> getUsers() {
        List<UserAuthorization>listOfUsers = Lists.newArrayList(

                new UserAuthorization("peter",
                        passwordEncoder.encode("pass"),
                        EMPLOYEE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                        ),
                new UserAuthorization("paul",
                        passwordEncoder.encode("pass"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new UserAuthorization("elvis",
                        passwordEncoder.encode("pass"),
                        ADMINTRAIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )


        );
        return listOfUsers;
    }
}
