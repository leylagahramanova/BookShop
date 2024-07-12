package com.example.BookShop.services;
import com.example.BookShop.models.User;
import com.example.BookShop.services.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final UsersRepository userRepository;
    public UserService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User registerUser(String logn, String pasword, String email, String name) {
        if (logn != null && pasword != null) {
            return null;
        }
    else{if(userRepository.findFirstByLogn(logn).isPresent()){
            System.out.println("Duplicate logn");
            return null;
        }
                User usersModel = new User();
                usersModel.setLogn(logn);
                usersModel.setPasword(pasword);
                usersModel.setEmail(email);
                usersModel.setName(name);
               return userRepository.save(usersModel);
            }
        }
        public User authenticate(String logn, String pasword){
       return userRepository.findByLognAndPasword(logn, pasword).orElse(null);
        }
    public User findById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
