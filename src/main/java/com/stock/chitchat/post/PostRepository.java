package com.stock.chitchat.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByStockIdOrderByCreatedDateDesc(Long postId, Pageable pageable);
}
