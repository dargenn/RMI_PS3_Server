package io.dargenn.external;

import java.rmi.RemoteException;
import java.util.List;

public class GameServiceImpl implements GameService {
    private GameManager gameManager;

    public boolean join(Player player) throws RemoteException {
        return gameManager.addPlayer(player);
    }

    public boolean move(int x, int y, Player player) throws RemoteException {
        return gameManager.move(x, y, player);
    }

    @Override
    public void disconnect(Player player) throws RemoteException {
        gameManager.disconnect(player);
    }

    @Override
    public boolean isGameActive() throws RemoteException {
        return gameManager.getGame().isActive();
    }

    @Override
    public int getPlayerNumber(String name) throws RemoteException {
        return gameManager.getGame().getPlayers().stream().filter(p -> p.getName().equals(name)).findFirst().get().getNumber();
    }

    @Override
    public TicTacToeType getActivePlayerNumber() throws RemoteException {
        return gameManager.getGame().getNextSignToPlay();
    }

    @Override
    public int getMoveCount() throws RemoteException {
        return gameManager.getGame().getMoveCount();
    }

    @Override
    public String getGameBoard() throws RemoteException {
        return gameManager.getGame().gameToString();
    }

    @Override
    public boolean isGameOver() throws RemoteException {
        return gameManager.isGameOver(TicTacToeType.TIC) || gameManager.isGameOver(TicTacToeType.TAC);
    }

    @Override
    public void destroy() throws RemoteException {
        gameManager.setGame(null);
    }

    public GameServiceImpl() {
        this.gameManager = GameManager.getInstance();
    }
}
