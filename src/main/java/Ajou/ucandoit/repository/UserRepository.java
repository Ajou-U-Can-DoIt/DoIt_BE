package Ajou.ucandoit.repository;

import Ajou.ucandoit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select o from User o where o.userName = ?1")
    User findByUserName(String userName);
}
