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

    @Embedded
    private SocialDetails socialDetails;

    public Player(SocialDetails socialDetails) {
        this.socialDetails = socialDetails;
    }

    public Player(Long socialId, String nickname, MediaFile mediaFile) {
        this.socialDetails = new SocialDetails(socialId, nickname, mediaFile);
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
