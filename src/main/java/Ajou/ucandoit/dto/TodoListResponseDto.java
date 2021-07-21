package Ajou.ucandoit.dto;

import Ajou.ucandoit.domain.Todo;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class TodoListResponseDto {
    private String title;

    private String start;
    private String end;

    private String category;
    private String alarm;
    private String set_repeat;
    private Long star;
    private String content;
    private Long calendar;

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public TodoListResponseDto(Todo entity)
    {
        this.title = entity.getTitle();
        //this.start = entity.getStart().toString();
        //this.end = entity.getEnd().toString();

        this.start = formatter.format(entity.getStart());
        this.end = formatter.format(entity.getEnd());
        this.alarm = formatter.format(entity.getAlarm());

        this.category = entity.getCategory();
        this.set_repeat = entity.getSetRepeat();
        this.star = entity.getStar();
        this.content = entity.getContent();
        this.calendar = entity.getCalendar().getId();
    }
}
