package com.example.RBAZadatak.Repository;

import com.example.RBAZadatak.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByOib(String oib);

    void deleteByOib(String oib);
}
