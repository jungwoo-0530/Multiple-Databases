package com.multiple.multipledatabases.repository.db2;

import com.multiple.multipledatabases.domain.db2.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * fileName     : PostRepository
 * author       : jungwoo
 * description  :
 */
//@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  Post findByTitle(String title);
}
