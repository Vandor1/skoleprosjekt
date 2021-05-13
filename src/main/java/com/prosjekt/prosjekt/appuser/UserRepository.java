package com.prosjekt.prosjekt.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * AppUserRepository class - implements JpaRepository with value AppUser and key Long(appuserID). Has built in methods
 * we often use which can be explored within Jpa's documentation.
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Boolean existsByEmail(String email);
}
