package Models;

import java.util.ArrayList;

import java.util.Date;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import static javax.persistence.GenerationType.IDENTITY;




@Entity
@Table(name = "Posts")

public class Post 
{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "id_post",unique = true, nullable = false)
	Long idPost;
	
	@Column(name = "title")
	private String title;
	@Column(name = "body")
	private String body;
	@Column (name = "date")
	@Temporal(TemporalType.DATE)
    private Date date ;
	@Column (name = "id_tag")
    ArrayList<String> tags;
    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
	User creator;
    @ManyToOne(optional = false)
    @JoinColumn(name="group_id")
	Group group;

    
    
    public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	

	
	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}
	
	public Post(String title,String body, Date date, ArrayList<String> tags, User creator,Group group) {
		this.title = title;
		this.body = body;
		this.date = date;
		this.tags = tags;
		this.creator = creator;
		this.group = group;
		
				
	}
	
	public String toStringUser()
	{
		String respuesta = creator.getUsername();
		return respuesta;
	}
	
	public String toStringGroup()
	{
		String respuesta = group.getName();
		return respuesta;
	}
	public String toStringPost()
	{
		return(title + "\n" + toStringUser() + "\n"  +"Group:" + toStringGroup() + "\n" + body + "\n" +"tags: " + tags.get(0) + "," + tags.get(1) + "\n" + date);
	}
	
	public String toStringTitle()
	{
		return(title);
	}
	

}
