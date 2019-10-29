package entities;

/**
 * Created by odiachuk on 13.07.17.
 */
public class UserEntity extends BaseEntity{
    String firstname;
    String lastname;
    String username;
    String password;

    public UserEntity(String firstname, String lastname, String password, String username, AddressEntity address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    AddressEntity address;

    public UserEntity() { }


    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

}
