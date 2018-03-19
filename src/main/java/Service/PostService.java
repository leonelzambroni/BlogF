package Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import DAO.DAOPost;
import Models.Post;
import Models.User;
import Models.Group;


@Service

public class PostService {
	
	@Autowired
    DAOPost daoPost;
    Post post = null;
    Long idPost = null;
    

    public PostService() {}

    public Post findById(Long pid){
        post = daoPost.findByIdPost(pid);
        return post;
    }

    //String title,String body, Date date, ArrayList<String> tags, User creator,Group group
    public Boolean newPost(String title,String body, Date date, ArrayList<String> tags, User creator,Group group) {
        try {
            post = new Post(title, body,date, tags, creator, group);
            idPost = post.getIdPost();
            daoPost.save(post);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean removePost(String title){
        try{
            daoPost.deletePost(title);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<Post> searchByTag(String tag) {
        ArrayList<Post> retrivedPosts = new ArrayList<Post>();
        for (Post post : daoPost.getPostList()) {
            List<String> tags = post.getTags();
            for (String taglist : tags){
                if (tag.equals(taglist)){
                    retrivedPosts.add(post);
                }
            }

        }
        return retrivedPosts;
    }

    public ArrayList<Post> searchByBody(String body){
        ArrayList<Post> retrivedPosts = new ArrayList<Post>();
        for(Post post : daoPost.getPostList()){
            if (post.getBody().contains(body)) {
                retrivedPosts.add(post);
            }
        }
        return retrivedPosts;
    }

    public ArrayList<Post> searchByCreator(String username){
        ArrayList<Post> retrivedPosts = new ArrayList<Post>();
        for (Post post : daoPost.getPostList()){
            String creator = post.getCreator().getUsername();
            if (creator.equals(username)) {
                retrivedPosts.add(post);
            }
        }
        return retrivedPosts;
    }

    public ArrayList<Post> searchByDates(Date from, Date to){
        ArrayList<Post> retrivedPosts = new ArrayList<Post>();
        for(Post post : daoPost.getPostList()){
            if(post.getDate().after(from) && post.getDate().before(to)){
                retrivedPosts.add(post);
            }
        }
        return retrivedPosts;
    }

    public void orderNewToOld(){
        Collections.sort(daoPost.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return t1.getDate().compareTo(post.getDate());
            }
        });

    }
    public void orderOldToNew(){
        Collections.sort(daoPost.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return post.getDate().compareTo(t1.getDate());
            }
        });
    }
    public void orderAlphabetically(){
        Collections.sort(daoPost.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return post.getTitle().compareTo(t1.getTitle());
            }
        });
    }
    public void orderReAlphabetically(){
        Collections.sort(daoPost.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return t1.getTitle().compareTo(post.getTitle());
            }
        });
    }

    public Post getPost() {
        return post;
    }

}
