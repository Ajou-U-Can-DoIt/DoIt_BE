package Ajou.ucandoit.repository;

import Ajou.ucandoit.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByCalenderId(Long calenderId);

    Optional<Todo> findByUserId(Long Id);

}