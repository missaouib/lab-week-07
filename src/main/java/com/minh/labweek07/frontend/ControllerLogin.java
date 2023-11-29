package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.models.User;
import com.minh.labweek07.backend.models.Role;
import com.minh.labweek07.backend.repository.UserRepository;
import com.minh.labweek07.backend.repository.RoleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerLogin {
    @Autowired
    private UserRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = {"/login"})
    public String showLoginPage(Model model) {
        User user=new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value = {"/handleLogin"})
    public String handleLogin(@RequestParam("email") String email, @RequestParam("pass") String pass, HttpSession session, Model model) {
        User acc = accountRepository.findAccountByEmailAndPassword(email, pass);
//        Role roleAdmin = roleRepository.findById("admin").get();
//        Role roleUser = roleRepository.findById("user").get();
        if (acc != null) {
            session.setAttribute("acc", acc);
//            if (grantRepository.findGrantByRoleAndAccount_AccountID(roleAdmin, acc.getAccountID()) != null) {
//                return "redirect:/admin";
//            } else if (grantRepository.findGrantByRoleAndAccount_AccountID(roleUser, acc.getAccountID()) != null) {
//                return "redirect:/home";
//            } else {
//                model.addAttribute("error", "Tai khoan khong co quyen truy cap");
//                return "login";
//            }
        } else {
            model.addAttribute("error", "Email or password is incorrect");
            return "login";
        }
        return "login";
    }

}
