package Service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import DAO.DAOUser;
import Models.User;

@Service

public class UserService {
	
	@Autowired
    private DAOUser daoUser;
    private User user = null;
    private Long idUser = null;


    public Boolean newUser(String name, String email) {
        try {
            this.user = new User(name, email);
            idUser=user.getIdUser();
            System.out.println(user);
            daoUser.save(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public User existingUser(String username) throws SQLException{
        try{
            user = daoUser.findByUsername(username);
            return user;
        } catch (Exception e){
            throw new SQLException();
        }
    }

    public User findById(Long idUser) {
        user = daoUser.findByIdUser(idUser);
        return user;

    }
    public User getUser() {
        return user;
    }

}
