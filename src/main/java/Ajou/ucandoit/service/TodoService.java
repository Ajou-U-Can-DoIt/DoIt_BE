package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.dto.TodoListResponseDto;
import Ajou.ucandoit.dto.TodoSaveRequestDto;
import Ajou.ucandoit.dto.TodoUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    //Todo findTodoById(Long id);

    Long saveTodo(TodoSaveRequestDto todoSaveRequestDto);//일정 등록

    Long update(TodoUpdateRequestDto updateRequestDto);//일정 수정

    Long delete(Long todo_id);

    String getDetail(Long todo_id);

    List<TodoListResponseDto> getTodoList(Long calendarId);//일정 조회
}
