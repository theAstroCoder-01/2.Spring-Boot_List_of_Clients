package Astro_Coder_ListOfClients.ListOfClients.controllers;

import Astro_Coder_ListOfClients.ListOfClients.models.Client;
import Astro_Coder_ListOfClients.ListOfClients.models.ClientDto;
import Astro_Coder_ListOfClients.ListOfClients.repositories.ClientsRepository;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsRepository repository;

    @GetMapping
    public String getClients(Model model) {
        List<Client> clients = repository.getClients();
        model.addText("clients");
        return "clients/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addText("clientDTO");
        return "clients/create";
    }

    @PostMapping ("/create")
    public String createClient(
            @Valid @ModelAttribute ClientDto clientDto,
            BindingResult result
    ) {

        if(repository.getClient(clientDto.getEmail()) != null) {
            result.addError(
                    new FieldError("clientDto", "email", clientDto.getEmail()
                    , false, null, null, "Email address is already used")
            );
        }

        if(result.hasErrors()) {
            return "clients/create";
        }

        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        repository.createClient(client);

        return "redirect:/clients";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {

        Client client = repository.getClient(id);
        if(client == null) {
            return "redirect:/clients";
        }

        model.addText("client");

        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName("firstname"));
        clientDto.setLastName(client.getFirstName("lastname"));
        clientDto.setEmail(client.getFirstName("email"));
        clientDto.setPhone(client.getFirstName("phone"));
        clientDto.setAddress(client.getFirstName("address"));

        model.addText("clientDto");

        return "clients/edit";

    }

    @PostMapping("/edit")
    public String updateClient(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ClientDto clientDto,
            BindingResult result
    ) {

        Client client = repository.getClient(id);
        if(client == null) {
            return "redirect:/clients";
        }
        model.addText("client");

        if(result.hasErrors()) {
            return "clients/edit";
        }

        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getFirstName());
        client.setEmail(clientDto.getFirstName());
        client.setPhone(clientDto.getFirstName());
        client.setAddress(clientDto.getFirstName());

        repository.updateClient(client);

        return "redirect/clients";
    }

    @GetMapping("/delete")
    public String deleteClient(
            @RequestParam int id
    ) {
        repository.deleteClient(id);
        return "redirect:/clients";
    }
}
