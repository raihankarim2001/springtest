package com.example.postgrestestnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MainController
{
    @Autowired
    CredentialRepository credentialRepository;
    @Autowired
    private UserdetailRepository userdetailRepository;
    @Autowired
    private UsertypelinkRepository usertypelinkRepository;

    @GetMapping("/")
    public String getLandingPage()
   {
       return "landingpage";
   }
   @PostMapping("/signup")
   public String signup(@RequestParam("username") String username,
                        @RequestParam("password") String password)
   {
       Credential credential = new Credential();
       credential.setUsername(username);
       credential.setPassword(password);

       credentialRepository.save(credential);

       return "dashboard";
   }

   @PostMapping("/login")
   public String login(@RequestParam("username") String username,
                       @RequestParam("password") String password, HttpSession session, Model model) {

       Optional<Credential> matchedCredential = credentialRepository.findById(username);

       if (matchedCredential.isPresent()) {
           if (matchedCredential.get().getPassword().equals(password)) {
               session.setAttribute("username", username);
               Optional<Userdetail> userdetail = userdetailRepository.findById(username);
               List<Usertypelink> usertypelinks = usertypelinkRepository.findAll();
               Optional<Usertypelink> usertypelink = usertypelinks.stream().fiter(usertypelink1 ->usertypelink1.getUsername().equals(username).findAny());


               if (userdetail.isPresent()) {
                   model.addAttribute("userdetail", userdetail.get());
               }
               return "dashboard";
           } else {
               return "landingpage";
           }
       } else {
           return "landingpage";
       }
   }
       @PostMapping("/signing")
       public String signing(@RequestParam("username") String username,@RequestParam("fName") String fname,@RequestParam("lName") String lname,@RequestParam("email") String email,@RequestParam("phone") String phone){
       Userdetail userdetail = new Userdetail();
       userdetail.setUsername(username);
       userdetail.setFname(fname);
       userdetail.setLname(lname);
       userdetail.setEmail(email);
       userdetail.setPhone(phone);
       userdetailRepository.save(userdetail);
       return"last";
}



}
