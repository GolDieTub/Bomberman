import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server extends Thread {

    private final Object LOCK = new Object();

    private boolean active;

    private final List<Lobby> lobbies;
    private final List<Client> clients;

    public Server() {
        active = true;
        clients = new ArrayList<>();
        lobbies = new ArrayList<>();
        new LobbyCreator().start();
    }

    @Override
    public void run() {
        while (active) {
            try {
                final ServerSocket serverSocket = new ServerSocket(8080);
                final Socket clientSocket = serverSocket.accept();
                synchronized (LOCK) {
                    clients.add(new Client(clientSocket));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cleanInactiveClients() {
        clients.removeIf(client -> !client.isActive());
    }

    private class LobbyCreator extends Thread {
        @Override
        public void run() {
            while (active) {
                try {
                    synchronized (LOCK) {
                        cleanInactiveClients();
                        if (clients.size() >= Lobby.LOBBY_CLIENT_COUNT) {

                            final Set<Client> lobbyClients = new HashSet<>();

                            for (int i = 0; i < Lobby.LOBBY_CLIENT_COUNT; i++) {
                                final Client client = clients.get(0);
                                lobbyClients.add(client);
                                clients.remove(client);
                            }
                            final Lobby lobby = new Lobby(lobbyClients);
                            lobbies.add(lobby);
                        }
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
