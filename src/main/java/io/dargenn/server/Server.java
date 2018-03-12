package io.dargenn.server;

import io.dargenn.common.SecurityUtils;
import io.dargenn.external.GameService;
import io.dargenn.external.GameServiceImpl;
import lombok.SneakyThrows;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        SecurityUtils.prepareSecurity();
        System.setProperty("java.rmi.server.hostname", "192.168.0.103");
        GameService gameService = new GameServiceImpl();
        GameService stub = (GameService) UnicastRemoteObject.exportObject(gameService, Registry.REGISTRY_PORT);
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.rebind("TicTacToe", stub);
        System.out.println("TicTacToe server is running.");
        while(true) {}
    }
}
