package com.basketclub.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PLAYER", indexes = @Index(columnList = "email"), uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public Player(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void update(Player edit) {
        this.name = edit.name;
        this.email = edit.email;
        this.password = edit.password;
    }

    public boolean isMatchPassword(String password) {
        return this.password.equals(password);
    }
}
