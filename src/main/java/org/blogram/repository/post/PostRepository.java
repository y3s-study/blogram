package org.blogram.repository.post;

import org.blogram.domain.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByOrderByCreatedDateDesc(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.id = ?1")
    Post findById(Integer memberId);
}
