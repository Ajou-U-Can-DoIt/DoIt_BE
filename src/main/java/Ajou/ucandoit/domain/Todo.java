package Ajou.ucandoit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
//import java.sql.Date;

@Entity
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

    //calendar id
    private Long calendarId;
}
