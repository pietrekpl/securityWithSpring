package securityWithSpring.userauth;


import java.util.Optional;

public interface UserDAO {

    Optional<UserAuthorization> selectUserAuthorizationByUsername(String username);
}
