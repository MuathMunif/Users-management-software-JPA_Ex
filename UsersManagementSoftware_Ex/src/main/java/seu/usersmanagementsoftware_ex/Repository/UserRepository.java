package seu.usersmanagementsoftware_ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import seu.usersmanagementsoftware_ex.Model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // used JPA
    User findUserById(Integer id);
    User findUserByEmail(String email);
    List<User> findUserByRole(String role);

    //used JPQL
    @Query("select u from User u where u.username = ?1 AND u.password =?2")
    User checkUserByUsernameAndPassword(String username , String password);
    @Query("select u from User u where u.age >= ?1")
    List<User> getAllUserByAgeOrApove(Integer age);

}
