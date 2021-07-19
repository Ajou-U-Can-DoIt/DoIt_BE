package Ajou.ucandoit.dto;

import Ajou.ucandoit.domain.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class TodoSaveRequestDto {

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    private String category;

    //alarm Date 형식 인가용?
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date alarm;

    private String repeat;
    private int star;
    private String content;
    private Long calenderId;


    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .start(start)
                .end(end)
                .category(category)
                .alarm(alarm)
                .repeat(repeat)
                .star(star)
                .content(content)
                .build();
    }

}
