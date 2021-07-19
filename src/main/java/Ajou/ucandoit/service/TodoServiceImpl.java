package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.repository.TodoRepository;
import Ajou.ucandoit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Optional<Todo> findTodoById(Long userid) {
        return todoRepository.findByUserId(userid);
    }

    @Override
    public Long registerTodo(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }




}
