package Models;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import static javax.persistence.GenerationType.IDENTITY;



import javax.persistence.*;

@Entity
@Table(name = "Users")

public class User implements Serializable{
	
	@Id
	@Column(name = "id_user",unique = true, nullable = false)
	@GeneratedValue(strategy = IDENTITY)
	Long idUser;


	@Column(name = "username")
	String username;
	@Column(name = "email")
	String email;
	@Column (name = "id_group")
    @ManyToMany(mappedBy = "users")
    List<Group> groups;
    
    
    public User() {
		// TODO Auto-generated constructor stub
	}
    
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
    
    public User(String username,String email)
    {
    	this.username = username;
    	this.email = email;
        this.groups = new ArrayList<Group>();

    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public void addGroup(Group group){
        this.groups.add(group);
    }

}
