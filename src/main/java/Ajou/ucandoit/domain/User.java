package Ajou.ucandoit.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(nullable = false)
    private String account;
    private String email;
    private String phoneNumber;
    @Column(nullable = false)
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}