package org.example.CowsAndBulls.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
public class Rounds {

    private int roundId;
    private int gameId;
    private LocalDateTime guessTime;
    private String guess;
    private String result;

    public Rounds() {
    }

    public Rounds(int gameId, String guess) {
        this.gameId = gameId;
        this.guess = guess;
    }


    public Rounds(int roundId, int gameId, LocalDateTime guessTime, String guess, String result) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guessTime = guessTime;
        this.guess = guess;
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.roundId;
        hash = 43 * hash + this.gameId;
        hash = 43 * hash + Objects.hashCode(this.guessTime);
        hash = 43 * hash + Objects.hashCode(this.guess);
        hash = 43 * hash + Objects.hashCode(this.result);
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
        final Rounds other = (Rounds) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }


}
