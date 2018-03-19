package DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Models.Post;

public interface DAOPost extends CrudRepository<Post, Long>{

	@Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<Post> getPostList();

    @Modifying
    @Transactional
    @Query("delete from Post p where p.title = ?1")
    void deletePost(String title);

    @Query("select p from Post p where p.id = ?1")
    Post findByIdPost(Long idPost);
}
