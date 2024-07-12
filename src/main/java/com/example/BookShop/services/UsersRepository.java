package com.example.BookShop.services;
import com.example.BookShop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLognAndPasword(String logn, String pasword);
    Optional<User> findFirstByLogn(String logn);
}