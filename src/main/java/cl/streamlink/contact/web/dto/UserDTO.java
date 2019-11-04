package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Chemakh on 10/07/2018.
 */
public class UserDTO {


    private String reference;

   /* @NotNull
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    private String username;*/


    @NotNull
    @Size(min = 2, max = 255)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastname;

    @NotNull
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @Email
    private String email;

    private List<Role> roles;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }



/*    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
