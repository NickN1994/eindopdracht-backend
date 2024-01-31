package nl.novi.eindopdracht.LoginAndSecurity.Repository;

import nl.novi.eindopdracht.LoginAndSecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
