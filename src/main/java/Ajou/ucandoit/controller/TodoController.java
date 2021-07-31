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

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    //list형식
    @GetMapping("/{id}")
    public Map<String, Object> checkTodo(@PathVariable Long id) {
        Map<String, Object> result = new LinkedHashMap<>();
        List<TodoListResponseDto> todoList = todoService.getTodoList(id);

        result.put("msg", "success");
        result.put("status", 200);
        result.put("data", todoList);
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> addTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto) {
        Map<String, Object> result = new LinkedHashMap<>();
        Long todoId = todoService.saveTodo(todoSaveRequestDto);
        result.put("msg", "success");
        result.put("status", 200);
        result.put("data", todoId);

        return result;
    }

    @PutMapping("/revision")
    public Map<String, Object> revisionTodo(@RequestBody TodoUpdateRequestDto updateRequestDto) {
        Map<String, Object> result = new LinkedHashMap<>();

        todoService.update(updateRequestDto);

        result.put("msg", "success");
        result.put("status", 200);
        return result;
    }

    @DeleteMapping("/delete/{todo_id}")
    public Map<String, Object> deleteTodo(@PathVariable Long todo_id) {
        Map<String, Object> result = new LinkedHashMap<>();
        todoService.delete(todo_id);
        result.put("msg", "success");
        result.put("status", 200);
        return result;
    }

    @GetMapping("/detail/{todo_id}")
    public Map<String, Object> detailTodo(@PathVariable Long todo_id){
        Map<String, Object> result = new LinkedHashMap<>();
        String detail = todoService.getDetail(todo_id);
        result.put("msg", "success");
        result.put("status", 200);
        result.put("data", detail);

        return result;
    }

    //상세 일정 조회
    //일정 추가
    //일정 삭제
    //일정 수정
    //일정 삭제
}
