package DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Models.User;

public interface DAOUser extends CrudRepository<User, Long>{
	   @Query(value = "SELECT * FROM users WHERE username = ?1",nativeQuery = true)
	    User findByUsername(String username);


	    @Query(value = "SELECT * FROM users WHERE id_user = ?1", nativeQuery = true)
	    User findByIdUser(Long idUser);


}
