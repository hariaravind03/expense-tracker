package com.hari.Expense_Tracker.repository;

import com.hari.Expense_Tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmailId(String email);

    User findByEmailId(String email);
}
