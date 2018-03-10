package io.dargenn.external;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GameManager {
    private static GameManager instance;
    private Game game;

    private GameManager() {
        game = new Game();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            return new GameManager();
        }
        return instance;
    }

    public boolean addPlayer(Player player) {
        List<Player> players = game.getPlayers();
        if (players.size() < 2 && game.isActive()) {
            player.setTicTacToeType(players.isEmpty() ? TicTacToeType.TIC : TicTacToeType.TAC);
            player.setNumber(players.isEmpty() ? 1 : 2);
            game.getPlayers().add(player);
            return true;
        }
        return false;
    }

    public boolean move(int x, int y, Player player) {
        boolean canMove = game.getBoard().getFields().stream().anyMatch(field -> field.getX() == x && field.getY() == y && field.isEmpty() && game.getNextSignToPlay().equals(player.getTicTacToeType())) && game.isActive();
        if (canMove) {
            Field field = game.getBoard().getFields().stream().filter(f -> f.getX() == x && f.getY() == y).findFirst().orElseThrow(() -> new IllegalArgumentException("Value not permitted"));
            field.setTicTacToeType(player.getTicTacToeType());
            game.setNextSignToPlay(player.getTicTacToeType().negate());
            boolean isGameOver = isGameOver(player.getTicTacToeType());
            game.setActive(!isGameOver);
            if(isGameOver) {
                game.getPlayers().forEach(p -> p.notifyGameOver(player.getNumber()));
            }
        }
        return canMove;
    }

    private boolean isGameOver(TicTacToeType ticTacToeType) {
        List<Field> fields = game.getBoard().getFields();
        boolean isThreeInColumns = isThreeInColumns(fields, ticTacToeType);
        boolean isThreeInRow = isThreeInRow(fields, ticTacToeType);
        boolean isThreeInDiagonal = isThreeInDiagonal(fields, ticTacToeType);
        return isThreeInColumns || isThreeInRow || isThreeInDiagonal;
    }

    private boolean isThreeInColumns(List<Field> fields, TicTacToeType ticTacToeType) {
        for(int column = 1; column <= 3; column++) {
            int finalColumn = column;
            List<Field> fieldsInColumn = fields.stream().filter(field -> field.getX() == finalColumn).collect(Collectors.toList());
            boolean isThreeInColumn = fieldsInColumn.stream().allMatch(field -> field.getTicTacToeType() == ticTacToeType);
            if(isThreeInColumn) {
                return true;
            }
        }
        return false;
    }

    private boolean isThreeInRow(List<Field> fields, TicTacToeType ticTacToeType) {
        for(int row = 1; row <= 3; row++) {
            int finalRow = row;
            List<Field> fieldsInRow = fields.stream().filter(field -> field.getY() == finalRow).collect(Collectors.toList());
            boolean isThreeInRow = fieldsInRow.stream().allMatch(field -> field.getTicTacToeType() == ticTacToeType);
            if(isThreeInRow) {
                return true;
            }
        }
        return false;
    }

    private boolean isThreeInDiagonal(List<Field> fields, TicTacToeType ticTacToeType) {
        List<Field> diagonalOneFields = fields.stream().filter(field -> (field.getX() == 1 && field.getY() == 1) || (field.getY() == 2 && field.getX() == 2) || (field.getX() == 3 && field.getY() == 3)).collect(Collectors.toList());
        List<Field> diagonalTwoFields = fields.stream().filter(field -> (field.getX() == 1 && field.getY() == 3) || (field.getY() == 2 && field.getX() == 2) || (field.getX() == 3 && field.getY() == 1)).collect(Collectors.toList());
        return diagonalOneFields.stream().allMatch(field -> field.getTicTacToeType() == ticTacToeType) || diagonalTwoFields.stream().allMatch(field -> field.getTicTacToeType() == ticTacToeType);
    }
}
