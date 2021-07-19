package Ajou.ucandoit.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
//import java.sql.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String category; //List??
    private Date alarm;
    private String repeat;
    private int star;

    private Date start;
    private Date end;

    //calendar id : fk
    @ManyToOne
    @JoinColumn(name = "calender_id")
    private Calendar calendar;
}
