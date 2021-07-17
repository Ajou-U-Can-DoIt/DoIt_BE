package Ajou.ucandoit.controller;

import Ajou.ucandoit.domain.Todo;
import Ajou.ucandoit.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("/id")
    public Optional<Todo> checkTodo(@RequestParam Long id, Model model) {
        Optional<Todo> findTodos = todoService.findTodoById(id);
        model.addAttribute("todoList", findTodos);
        return findTodos;
    }

    //일정 조회
    //상세 일정 조회
    //일정 추가
    //일정 삭제
    //일정 수정
    //일정 삭제
}
