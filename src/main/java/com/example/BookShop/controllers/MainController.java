package com.example.BookShop.controllers;

import com.example.BookShop.models.*;
import com.example.BookShop.services.BooksRepository;
import com.example.BookShop.services.BookDetailsRepository;
import com.example.BookShop.services.MyBookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;
import com.example.BookShop.services.UserService;
@Controller
public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class.getName());
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }
    @Autowired
    private BooksRepository repo;
    @Autowired
    private MyBookRepository myrepo;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String showList(Model model) {
        List<Book> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("books", list);
        List<Book> biographyBooks = repo.findByCategory("Biography");
        List<Book> comedyBooks = repo.findByCategory("Comedy");
        List<Book> tragedyBooks = repo.findByCategory("Tragedy");
        List<Book> fantasyBooks = repo.findByCategory("Fantasy");
        List<Book> mysteryBooks = repo.findByCategory("Mystery");
        model.addAttribute("biographyBooks", biographyBooks);
        model.addAttribute("comedyBooks", comedyBooks);
        model.addAttribute("tragedyBooks", tragedyBooks);
        model.addAttribute("fantasyBooks", fantasyBooks);
        model.addAttribute("mysteryBooks", mysteryBooks);
        model.addAttribute("title", "Main Page");
        return "bookList";
    }
    @GetMapping("/admin/")
    public String showBookList(Model model) {
        List<Book> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("books", list);
        return "bookList";
    }
    @GetMapping("/admin/create")
    public String bookRegister(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "bookRegister";
    }
    @PostMapping("/admin/create")
    public String createBook(@ModelAttribute @Valid BookDto bookDto, BindingResult result) {
        if (bookDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("bookDto", "imageFile", "The image is required"));
        }
        if (result.hasErrors()) {
            return "bookRegister";
        }
        MultipartFile image = bookDto.getImageFile();
        String storageFileName = image.getOriginalFilename();
        String uploadDir = "src/main/resources/static/images/";
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
            logger.info("Image successfully saved: " + uploadPath.resolve(storageFileName).toString());
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setPrice(bookDto.getPrice());
        book.setDescription(bookDto.getDescription());
        book.setImageFileName(storageFileName);
        repo.save(book);
        return "redirect:/";
    }
    @GetMapping("/admin/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        try {
            Book book = repo.findById(id).orElseThrow();
            model.addAttribute("book", book);
            BookDto bookDto = new BookDto();
            bookDto.setName(book.getName());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setCategory(book.getCategory());
            bookDto.setPrice(book.getPrice());
            bookDto.setDescription(book.getDescription());
            model.addAttribute("bookDto", bookDto);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/";
        }
        return "EditBook";
    }
    @PostMapping("/admin/edit")
    public String updateBook(Model model, @RequestParam int id, @Valid @ModelAttribute BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "EditBook";
        }
        try {
            Book book = repo.findById(id).orElseThrow();
            if (!bookDto.getImageFile().isEmpty()) {
                String uploadDir = "src/main/resources/static/images/";
                Path oldImagePath = Paths.get(uploadDir + book.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }

                MultipartFile image = bookDto.getImageFile();
                String storageFileName = image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                    book.setImageFileName(storageFileName);
                } catch (IOException ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
            book.setName(bookDto.getName());
            book.setAuthor(bookDto.getAuthor());
            book.setCategory(bookDto.getCategory());
            book.setPrice(bookDto.getPrice());
            book.setDescription(bookDto.getDescription());
            repo.save(book);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/";
    }
    @GetMapping("/admin/delete")
    public String deleteBook(@RequestParam int id) {
        try {
            Book book = repo.findById(id).get();
            Path imagePath = Paths.get("src/main/resources/static/images/" + book.getImageFileName());
            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
            repo.delete(book);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/";
    }
    @GetMapping("/personal_page")
    public String getMyBooks(Model model) {
        List<MyBookList> list = myrepo.findAll();
        model.addAttribute("myBooks", list);
        return "myBooks";
    }
    @RequestMapping("/myList/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book book = repo.findById(id).orElseThrow();
        MyBookList myBook = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getImageFileName());
        myrepo.save(myBook);
        return "redirect:/personal_page";
    }
    @GetMapping("/personal_page/delete")
    public String deleteMyBook(@RequestParam int id) {
        try {
            MyBookList book = myrepo.findById(id);
            if (book != null) {
                myrepo.delete(book);
            } else {
                throw new Exception("Book not found");
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/personal_page";
    }
    @GetMapping("/checkout")
    public String showCheckoutPage(@RequestParam("userId") int userId, @RequestParam("bookId") int bookId, Model model) {
        // Fetch user and book details from your database or service layer
        User user = userService.findById(userId);
        Optional<Book> book = repo.findById(bookId);
        model.addAttribute("user", user);
        model.addAttribute("book", book);

        return "checkout";
    }
    @PostMapping("/checkout")
    public String handleCheckout(@ModelAttribute @Valid Checkout checkout, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "checkout";
        }
        // Handle the checkout logic here
        // Save order details, process payment, etc.
        return "checkoutSuccess"; // Redirect to a success page or show a success message
}
    @GetMapping("/book/{id}")
    public String showBookDetail(Model model, @PathVariable("id") int id) {
        try {
            Book book = repo.findById(id).orElseThrow();
            model.addAttribute("book", book);
            // Fetch related books based on the category of the current book
            List<Book> relatedBooks = repo.findByCategory(book.getCategory());
            model.addAttribute("relatedBooks", relatedBooks);
            return "book_detail";
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/logn")
    public String showLognForm(Model model) {
        model.addAttribute("lognRequest", new User());
        return "logn"; // Assuming "logn" is your custom login page
    }

    @PostMapping("/logn")
    public String lognUser(@ModelAttribute User user, Model model) {
        User authenticated = userService.authenticate(
                user.getLogn(),
                user.getPasword()
        );
        if (authenticated != null) {
            return "redirect:/"; // Redirect to main page upon successful login
        } else {
            return "error_page"; // Handle login failure
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register"; // Assuming "register" is your custom registration page
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User registeredUser = userService.registerUser(
                user.getLogn(),
                user.getPasword(),
                user.getEmail(),
                user.getName()
        );
        if (registeredUser != null) {
            return "redirect:/logn"; // Redirect to login page after successful registration
        } else {
            return "error_page"; // Handle registration failure
        }
    }
}
