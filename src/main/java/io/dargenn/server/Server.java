package io.dargenn.server;

import io.dargenn.external.GameService;
import io.dargenn.external.GameServiceImpl;
import lombok.SneakyThrows;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    @SneakyThrows
    public static void main(String[] args) {
//        System.setProperty("java.rmi.server.hostname", "tutajIP");
        GameService gameService = new GameServiceImpl();
        GameService stub = (GameService) UnicastRemoteObject.exportObject(gameService, Registry.REGISTRY_PORT);
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.rebind("TicTacToe", stub);
        System.out.println("TicTacToe server is running.");
    }
}
