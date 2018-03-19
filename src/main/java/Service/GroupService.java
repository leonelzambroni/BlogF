package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import DAO.DAOGroup;
import Models.Group;
import Models.User;

@Service
public class GroupService {

	@Autowired
	DAOGroup daoGroup;
    Group group = null;
    Long idGroup = null;

    public GroupService() {}

    public Boolean newGroup(String groupname, User user){
        try{
            group = new Group(groupname);
            group.addMember(user);
            user.addGroup(group);
            idGroup = group.getIdGroup();
            daoGroup.save(group);
            return true;
        } catch(Exception e){
            System.out.println(e.toString());
            return false;
        }

    }
    
    public Boolean joinGroup(String name, User user){
        try{
            Group g = daoGroup.findByName(name);
            if (g!=null){
                g.addMember(user);
                user.addGroup(g);
                daoGroup.save(g);
                return true;
            } else {return false;}

        } catch (Exception e){

            return false;
        }
    }

    public Group findById(Long idGroup){
        group = daoGroup.findByIdGroup(idGroup);
        return group;
    }
    

    public Group getGroup() {
        return group;
    }
}
