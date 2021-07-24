package Ajou.ucandoit.controller;

import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.dto.TodoListResponseDto;
import Ajou.ucandoit.dto.TodoSaveRequestDto;
import Ajou.ucandoit.dto.TodoUpdateRequestDto;
import Ajou.ucandoit.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    //list형식
    @GetMapping("/{id}")
    public List<TodoListResponseDto> checkTodo(@PathVariable Long id) {
        List<TodoListResponseDto> todoList = todoService.getTodoList(id);
        return null;
    }

    @PostMapping("/add")
    public Map<String, Object> addTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto) {
        Map<String, Object> result = new LinkedHashMap<>();
        Long todoId = todoService.saveTodo(todoSaveRequestDto);
        result.put("msg", "일정 추가 성공");
        result.put("todoId", todoId);

        return result;
    }

    @PutMapping("/revision")
    public Long revisionTodo(@RequestBody TodoUpdateRequestDto updateRequestDto) {
        Map<String, Object> result = new LinkedHashMap<>();
        return todoService.update(updateRequestDto);
    }

    //상세 일정 조회
    //일정 추가
    //일정 삭제
    //일정 수정
    //일정 삭제
}
