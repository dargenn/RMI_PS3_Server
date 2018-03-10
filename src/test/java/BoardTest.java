import io.dargenn.external.Board;
import io.dargenn.external.GameService;
import io.dargenn.external.GameServiceImpl;
import io.dargenn.external.Player;
import org.junit.Assert;
import org.junit.Test;

import java.rmi.RemoteException;

public class BoardTest {
    @Test
    public void assureBoardHasNineFields() {
        Board board = new Board();
        Assert.assertEquals(9, board.getFields().size());
    }

    @Test
    public void assureNewBoardHasOnlyEmptyFields() {
        Board board = new Board();
        boolean hasNotEmptyField = board.getFields().stream().anyMatch(field -> !field.isEmpty());
        Assert.assertEquals(false, hasNotEmptyField);
    }

    @Test
    public void simulateGame() throws RemoteException {
        Player playerOne = new Player("playerOne");
        Player playerTwo = new Player("playerTwo");
        GameService gameService = new GameServiceImpl();
        gameService.join(playerOne);
        gameService.join(playerTwo);
        gameService.move(1, 1, playerOne);
        gameService.move(2, 1, playerTwo);
        gameService.move(1, 2, playerOne);
        gameService.move(2, 2, playerTwo);
        gameService.move(1, 3, playerOne);
        Assert.assertEquals(false, gameService.move(2, 3, playerTwo));
    }
}
