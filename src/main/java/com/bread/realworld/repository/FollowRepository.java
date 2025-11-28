package com.bread.realworld.repository;

import com.bread.realworld.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {
}
