package Astro_Coder_ListOfClients.ListOfClients.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ClientDto {

    @NotEmpty(message = "The First Name is required")
    private String firstName;

    @NotEmpty(message = "The Last Name is required")
    private String lastName;

    @NotEmpty(message = "The Email is required")
    @Email
    private String email;
    private String phone;
    private String address;



    public @NotEmpty(message = "The First Name is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty(message = "The First Name is required") String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotEmpty(message = "The Email is required") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "The Email is required") @Email String email) {
        this.email = email;
    }

    public @NotEmpty(message = "The Last Name is required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "The Last Name is required") String lastName) {
        this.lastName = lastName;
    }
}
