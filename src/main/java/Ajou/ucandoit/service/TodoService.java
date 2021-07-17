package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    Optional<Todo> findTodoById(Long userid);
}
