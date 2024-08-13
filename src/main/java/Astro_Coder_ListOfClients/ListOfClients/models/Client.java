package Astro_Coder_ListOfClients.ListOfClients.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCreatedAt(String createdAt) {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress(String string) {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone(String phone) {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName(String lastname) {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName(String firstname) {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
