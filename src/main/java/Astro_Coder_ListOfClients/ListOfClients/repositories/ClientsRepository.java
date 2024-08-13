package Astro_Coder_ListOfClients.ListOfClients.repositories;

import Astro_Coder_ListOfClients.ListOfClients.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Client> getClients() {
        var clients = new ArrayList<Client>();

        String sql = "SELECT * FROM clients ORDER BY id DESC";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while (rows.next()) {
            Client client = new Client();
            client.setId(rows.getInt("id"));
            client.getFirstName(rows.getString("firstname"));
            client.getLastName(rows.getString("lastname"));
            client.getEmail(rows.getString("email"));
            client.getPhone(rows.getString("phone"));
            client.getAddress(rows.getString("address"));
            client.getCreatedAt(rows.getString("created_at"));

            clients.add(client);
        }
        return clients;
    }

    public Client getClient(int id) {
        String sql = "SELECT * FROM clients WHERE id=?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);

        if (rows.next()) {
            Client client = new Client();
            client.setId(rows.getInt("id"));
            client.getFirstName(rows.getString("firstname"));
            client.getLastName(rows.getString("lastname"));
            client.getEmail(rows.getString("email"));
            client.getPhone(rows.getString("phone"));
            client.getAddress(rows.getString("address"));
            client.getCreatedAt(rows.getString("created_at"));

            return client;
        }
        return null;
    }

    public Client getClient(String email) {
        String sql = "SELECT * FROM clients WHERE email=?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, email);

        if (rows.next()) {
            Client client = new Client();
            client.setId(rows.getInt("id"));
            client.getFirstName(rows.getString("firstname"));
            client.getLastName(rows.getString("lastname"));
            client.getEmail(rows.getString("email"));
            client.getPhone(rows.getString("phone"));
            client.getAddress(rows.getString("address"));
            client.getCreatedAt(rows.getString("created_at"));

            return client;
        }
        return null;
    }

    public void createClient(Client client) {
        String sql = "INSERT INTO clients (firstname, lastname, email, phone, " +
                "address, created_at) VALUES (?, ?, ?, ?, ?)";

        int count = jdbcTemplate.update(sql,
                client.getFirstName("firstname"),
                client.getLastName("lastname"),
                client.getEmail("email"),
                client.getPhone("phone"),
                client.getAddress("address"),
                client.getCreatedAt("create_at"));

        if (count > 0) {
            int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            getClient(id);
        }

    }

    public void updateClient(Client client) {
        String sql = "UPDATE clients SET firstname=?, lastname=?, email=?, " +
                "phone=?, address=?, created_at=?, WHERE id=?";

        jdbcTemplate.update(sql,
                client.getFirstName("firstname"),
                client.getLastName("lastname"),
                client.getEmail("email"),
                client.getPhone("phone"),
                client.getAddress("address"),
                client.getCreatedAt("create_at"));

        getClient(client.getId());
    }

    public void deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

}


