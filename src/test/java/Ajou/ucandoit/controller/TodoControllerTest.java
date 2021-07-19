package Ajou.ucandoit.controller;

import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.dto.TodoSaveRequestDto;
import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.repository.TodoRepository;
import Ajou.ucandoit.repository.UserRepository;
import Ajou.ucandoit.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TodoControllerTest {
    TodoService todoService;
    TodoRepository todoRepository;
    UserRepository userRepository;

    //UserSaveRequestDto user;


    @Autowired
    public TodoControllerTest(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @BeforeEach
    void before() {

    }

    @Test
    void 일정_등록() {
        //given
        TodoSaveRequestDto todoSaveRequestDto =
                TodoSaveRequestDto.builder().title("test").build();
        //when
        Todo todo = todoRepository.save(todoSaveRequestDto);

        //then
    }
}