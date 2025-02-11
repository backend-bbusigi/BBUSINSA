package spring.bbusinsa.user.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import spring.bbusinsa.user.domain.enums.Gender;

@Entity @Getter
@Table(name = "members")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

}
