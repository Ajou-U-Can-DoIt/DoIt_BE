package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.dto.TodoListResponseDto;
import Ajou.ucandoit.dto.TodoSaveRequestDto;
import Ajou.ucandoit.dto.TodoUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<TodoListResponseDto> findByCalendar(Long calender);

    Long saveTodo(TodoSaveRequestDto todoSaveRequestDto);//일정 등록

    Long update(TodoUpdateRequestDto updateRequestDto);
}
