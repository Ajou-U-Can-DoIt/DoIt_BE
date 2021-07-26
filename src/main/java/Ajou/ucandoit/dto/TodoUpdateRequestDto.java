package Ajou.ucandoit.dto;

import Ajou.ucandoit.domain.Todo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequestDto {
    private Long id;
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    private String category;

    //alarm Date 형식 인가용?
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date alarm;

    private String setRepeat;
    private Long star;
    private String content;
    private Long calenderId;

    public Todo toEntity() {
        return Todo.builder()
                .id(id)
                .title(title)
                .start(start)
                .end(end)
                .category(category)
                .alarm(alarm)
                .setRepeat(setRepeat)
                .star(star)
                .content(content)
                .build();
    }
}
