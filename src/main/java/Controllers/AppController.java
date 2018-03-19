package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Models.Group;
import Models.Post;
import Models.User;
import Service.GroupService;
import Service.PostService;
import Service.UserService;

public class AppController {
	@RestController
	@RequestMapping("")
	public class MainController {
	    @Autowired
	    private UserService userService = new UserService();
	    @Autowired
	    private GroupService groupService = new GroupService();
	    @Autowired
	    private PostService postService = new PostService();


	    @RequestMapping(method = RequestMethod.POST,value = "/user/add")
	    public ResponseEntity<?> addNewUser (@RequestParam String name
	            , @RequestParam String email) {
	        Boolean created = userService.newUser(name,email);
	        if (created){
	            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	        } else {
	            return new ResponseEntity( HttpStatus.CREATED);
	        }

	    }

	    @RequestMapping(method = RequestMethod.POST, value = "/user/login")
	    public ResponseEntity<?> login(@RequestParam String username){
	        try{
	            User user = userService.existingUser(username);
	            return new ResponseEntity<String>("redirect:/user/"+user.getIdUser(),HttpStatus.OK);
	        }catch (Exception e){
	            return new ResponseEntity<String>("redirect:/user/login", HttpStatus.BAD_REQUEST);
	        }

	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/user/{idUser}")
	    public  ResponseEntity<?> showUser(@PathVariable("idUser") Long idUser){
	        User user = userService.findById(idUser);
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }


	    @RequestMapping(method = RequestMethod.POST, value = "/user/{idUser}/group/add")
	    public ResponseEntity<?> addGroup(@PathVariable("idUser") Long idUser, @RequestParam("name") String groupName){
	        User user = null;
	        try{
	            user = userService.findById(idUser);
	        } catch (Exception e){
	            return new ResponseEntity<String>("redirect:/user/login",HttpStatus.BAD_REQUEST);
	        }
	        try {
	            if(groupService.newGroup(groupName,user)){
	                return new ResponseEntity<String>("Group created succesfully", HttpStatus.OK);
	            }else{
	                return new ResponseEntity<String>("This group already exists", HttpStatus.BAD_REQUEST);
	            }

	        } catch (Exception j){
	            return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @RequestMapping(method = RequestMethod.POST, value = "/user/{idUser}/group/suscribe")
	    public  ResponseEntity<?> suscribeGroup(@PathVariable("idUser") Long idUser,@RequestParam("name") String groupName){
	        User user= null;
	        try{
	            user = userService.findById(idUser);
	        } catch (Exception e){
	            return new ResponseEntity<String>("redirect:/user/login",HttpStatus.BAD_REQUEST);
	        }
	        try {
	            if(groupService.joinGroup(groupName,user)){
	                return new ResponseEntity<String>("You are now subscribed to"+groupName+"!", HttpStatus.OK);
	            }else{
	                return new ResponseEntity<String>("Non existent group", HttpStatus.BAD_REQUEST);
	            }

	        } catch (Exception j){
	            return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @RequestMapping(method = RequestMethod.POST, value = "/post/add")
	    public  ResponseEntity<?> newPost(@RequestParam("idUser") Long idUser, @RequestParam
	            String title,@RequestParam String body,@RequestParam String tags, @RequestParam Long idGroup){
	        ArrayList<String> tagList = new ArrayList<String >(Arrays.asList(tags.split(",")));
	        Date date = new Date();
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        date = cal.getTime();
	        Boolean newpost = postService.newPost(title, body,  date, tagList, userService.findById(idUser),groupService.findById(idGroup));
	        if (newpost){
	            return new ResponseEntity<String>("Post created successfully",
	                    HttpStatus.CREATED);
	        }else{
	            return new ResponseEntity<String>("This post already exists",HttpStatus.BAD_REQUEST);
	        }


	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/post/{idPost}")
	    public  ResponseEntity<?> showPost(@PathVariable("pid") Long idPost){
	        Post post = postService.findById(idPost);
	        return new ResponseEntity<Post>(post, HttpStatus.OK);
	    }
	}

}
