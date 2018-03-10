package io.dargenn.external;

import java.rmi.RemoteException;

public class GameServiceImpl implements GameService {
    private GameManager gameManager;

    public boolean join(Player player) throws RemoteException {
        return gameManager.addPlayer(player);
    }

    public boolean move(int x, int y, Player player) throws RemoteException {
        return gameManager.move(x, y, player);
    }

    public GameServiceImpl() {
        this.gameManager = GameManager.getInstance();
    }
}
