package Ajou.ucandoit.repository;

import Ajou.ucandoit.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

    Auth findByRefreshToken(String refreshToken);

    @Transactional
    @Modifying
    @Query(value = "delete from auth o where o.subject = ?1", nativeQuery = true)
    void deleteBySubject(String subject);
}
