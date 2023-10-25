package com.metrodataacademy.repository;

import com.metrodataacademy.domain.entity.StagingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagingUserRepository extends JpaRepository<StagingUser, String> {

}
