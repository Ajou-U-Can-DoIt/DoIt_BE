package Ajou.ucandoit.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/*
@Entity: DB의 테이블을 뜻함
@Table: DB 테이블의 이름을 명시 (테이블 명과 클래스 명이 동일하면 따로 설정하지 않아도 됨)
@Id: Index primary key를 뜻함
@Column: DB Column을 명시
@GeneratedValue: Primary Key의 전략(Strategy)를 설정할 수 있음
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // like MySQL AI
    private Long id;

    private String userName;

    private String pwd;

    private String salt;

    private String email;

    private String nickName;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createdAt;
}