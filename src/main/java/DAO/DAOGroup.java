package DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import org.springframework.*;
import org.springframework.data.jpa.repository.Query;


import Models.Group;
@Repository
public  interface DAOGroup extends CrudRepository<Group, Long> {
	
	

	    @Query(value = "SELECT * FROM groups WHERE name = ?1",nativeQuery = true)
	    Group findByName(String name);

	    @Query(value = "SELECT * FROM groups WHERE id_group = ?1", nativeQuery = true)
	    Group findByIdGroup(Long id);
	

}
