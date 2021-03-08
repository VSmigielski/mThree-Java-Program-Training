package org.example.CowsAndBulls.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Getter
@Setter
public class Games {

    private int gameId;
    private String answer;
    private boolean finished;

    public Games() {
    }

    public Games(String answer, boolean finished) {
        this.answer = answer;
        this.finished = finished;
    }



    public Games(int gameId, String answer, boolean finished) {
        this.gameId = gameId;
        this.answer = answer;
        this.finished = finished;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.gameId;
        hash = 47 * hash + Objects.hashCode(this.answer);
        hash = 47 * hash + (this.finished ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Games other = (Games) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finished != other.finished) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer + ", finished=" + finished + '}';
    }

}