package org.geeksforgeeks.e_wallet.repository;

import org.geeksforgeeks.e_wallet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNo(String phoneNo);

}
