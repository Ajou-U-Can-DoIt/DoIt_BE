package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.dto.TodoListResponseDto;
import Ajou.ucandoit.dto.TodoSaveRequestDto;
import Ajou.ucandoit.dto.TodoUpdateRequestDto;
import Ajou.ucandoit.repository.TodoRepository;
import Ajou.ucandoit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

//    @Override
//    @Transactional
//    public Todo findTodoById(Long id) {
//        return todoRepository.findById(id).get();
//    }

    @Override
    @Transactional
    public Long saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        return todoRepository.save(todoSaveRequestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(TodoUpdateRequestDto updateRequestDto) {
        Todo todo = todoRepository.findById(updateRequestDto.getId()).orElseThrow(()->new IllegalArgumentException("해당 일정이 없습니다"));

        todoRepository.save(updateRequestDto.toEntity());
        return updateRequestDto.getId();
    }

    @Override
    @Transactional
    public Long delete(Long todo_id) {
        Todo todo = todoRepository.findById(todo_id).get();
        Long deletedId = todo.getId();
        todoRepository.delete(todo);
        return deletedId;
    }

    @Override
    public String getDetail(Long todo_id) {
        Todo todo = todoRepository.findById(todo_id).get();
        return todo.getContent();
    }

    @Override
    @Transactional
    public List<TodoListResponseDto> getTodoList(Long calendarId) {
        List<Todo> todos = todoRepository.findByCalendar_Id(calendarId);
        List<TodoListResponseDto> listResponseDtos = new ArrayList<>();

        for(Todo todo : todos) {
            TodoListResponseDto listResponseDto = new TodoListResponseDto(todo);
            listResponseDtos.add(listResponseDto);
        }

        return listResponseDtos;
    }


}
