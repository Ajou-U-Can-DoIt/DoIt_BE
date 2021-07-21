package Ajou.ucandoit.controller;

import Ajou.ucandoit.domain.Calendar;
import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.dto.TodoListResponseDto;
import Ajou.ucandoit.dto.TodoSaveRequestDto;
import Ajou.ucandoit.dto.TodoUpdateRequestDto;
import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.repository.TodoRepository;
import Ajou.ucandoit.repository.UserRepository;
import Ajou.ucandoit.service.TodoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoControllerTest {
    TodoService todoService;
    TodoRepository todoRepository;

    @Autowired
    public TodoControllerTest(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @AfterEach
    void after() {
        todoRepository.deleteAll();
    }

    @Test
    void 일정_등록() {
        //given
        TodoSaveRequestDto todoSaveRequestDto =TodoSaveRequestDto.builder().title("test").build();
        //when
        Long result = todoService.saveTodo(todoSaveRequestDto);
        //then
        Assertions.assertThat(result).isEqualTo(1L);
    }

    @Test
    void 일정_수정() {
        //given
        Todo saveTodo = todoRepository.save(Todo.builder().title("title").content("content").build());

        //when
        Long updateId = saveTodo.getId();
        String updateTitle = "title2";
        String updateContent = "content2";
        TodoUpdateRequestDto updateRequestDto =
                TodoUpdateRequestDto.builder().id(updateId).title(updateTitle).content(updateContent).build();

        Long id = todoService.update(updateRequestDto);

        //then
        Assertions.assertThat(todoRepository.findById(id).get().getContent()).isEqualTo(updateContent);
    }

    @Test
    void 일정_조회() {
        //given
        Todo todo = todoRepository.save(Todo.builder().title("test").content("content").build());
        
        //when
        Long id = todo.getId();
        
        Todo findTodo = todoService.findTodoById(id);
        
        TodoListResponseDto responseDto = new TodoListResponseDto(findTodo);

        //then
        Assertions.assertThat(responseDto.getContent()).isEqualTo("content");
    }

}