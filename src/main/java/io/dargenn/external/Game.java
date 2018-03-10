package io.dargenn.external;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private boolean active;
    private List<Player> players = new ArrayList<>();
    private Board board;
    private TicTacToeType nextSignToPlay = TicTacToeType.TIC;

    public Game() {
        this.board = new Board();
        this.active = true;
    }
}
