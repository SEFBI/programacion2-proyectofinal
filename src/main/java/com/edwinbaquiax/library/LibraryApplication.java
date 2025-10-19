package com.edwinbaquiax.library;

import com.edwinbaquiax.library.services.IBookManagementService;
import com.edwinbaquiax.library.services.IClientManagementService;
import com.edwinbaquiax.library.services.IRentManagementService;
import com.edwinbaquiax.library.services.LoginService;
import com.edwinbaquiax.library.ui.App;
import com.edwinbaquiax.library.ui.Login;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext context = SpringApplication.run(LibraryApplication.class, args);

        SwingUtilities.invokeLater(() -> {
          
             //JFrame login = new Login();
             
             //login.setVisible(true);
             IClientManagementService clientService = context.getBean(IClientManagementService.class);
             IBookManagementService bookService = context.getBean(IBookManagementService.class);
             IRentManagementService rentService = context.getBean(IRentManagementService.class);
             LoginService loginService = context.getBean(LoginService.class);
             App app = new App(clientService,bookService,rentService);
             
             
             JFrame login = new Login(loginService,app);
             
             login.setVisible(true);

        });

    }

}
