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
    private int moveCount = 0;

    public Game() {
        this.board = new Board();
    }

    public String gameToString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int x = 1; x <= 3; x++) {
            for(int y = 1; y <= 3; y++) {
                Field field = board.getFieldByXY(x, y);
                if(field.getTicTacToeType() == TicTacToeType.TIC) {
                    stringBuilder.append("O");
                } else if(field.getTicTacToeType() == TicTacToeType.TAC) {
                    stringBuilder.append("X");
                } else {
                    stringBuilder.append(" ");
                }
                if(y == 1 || y == 2) {
                    stringBuilder.append("|");
                }
            }
            if(x != 3) {
                stringBuilder.append("\n-----\n");
            }
        }

        return stringBuilder.toString();
    }
}
