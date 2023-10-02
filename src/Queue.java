import java.util.LinkedList;
public class Queue {
    private LinkedList<Client> clients = new LinkedList<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    public Client getClient() {
        return clients.poll();
    }
}
