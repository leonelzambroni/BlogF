package Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import Models.User;
import static javax.persistence.GenerationType.IDENTITY;



@Entity
@Table(name = "Groups")

public class Group {

		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id_group",unique = true, nullable = false)
		Long idGroup;
		
		

		@Column(name = "name")
		String name;
		
		@Column(name = "id_member")
		
		@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "Group_Users",
	            joinColumns = @JoinColumn(name = "id_group"),
	            inverseJoinColumns = @JoinColumn(name = "id_user"))
		
	    List<User> members;
		
		public Long getIdGroup() {
			return idGroup;
		}
		public void setIdGroup(Long idGroup) {
			this.idGroup = idGroup;
		}

		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<User> getMembers() {
			return members;
		}
		public void setMembers(List<User> members) {
			this.members = members;
		}

		public Group() {
			// TODO Auto-generated constructor stub
		}
		
		public Group(String name) {
			this.name = name;
			this.members = new ArrayList<User>();
			
		}
		
		public void addMember(User user)
		{
	        this.members.add(user);
		}
		
		
		public String toStringGroup()
		{
			return ("Name: " + name + " Members: " + members.size());
		}
		

}
