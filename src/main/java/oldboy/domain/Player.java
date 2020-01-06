package oldboy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Player {
    private String name;
    private String email;
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
