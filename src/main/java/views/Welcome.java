package views;

import Model.User;
import dao.UserDAO;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen(){
        Scanner ip=new Scanner(System.in);
        System.out.println("Welcome to the app");
        System.out.println("Press 1 for login");
        System.out.println("Press 2 for signup");
        System.out.println("Press 0 for exit");
        int choice = ip.nextInt();
        switch (choice){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
            default -> System.out.println("Wrong input");
        }
    }



    private void login() {
        Scanner ip=new Scanner(System.in);
        String email=ip.nextLine();
        try {
            if(UserDAO.isExists(email)){
                String genOTP= GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the otp");
                String otp=ip.nextLine();
                if(otp.equals(genOTP)){
                    new UserView(email).home();
                }
                else{
                    System.out.println("Wrong Otp");
                }
            }else{
                System.out.println("User not found");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void signUp() {
    Scanner ip=new Scanner(System.in);
        System.out.println("Enter Name");
        String name=ip.nextLine();
        System.out.println("Enter email");
        String email=ip.nextLine();
        String genOTP= GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("Enter the otp");
        String otp=ip.nextLine();

        if(otp.equals(genOTP)){
            User user=new User(name,email);
            int response = UserService.saveUser(user);
            switch (response){
                case 0 -> System.out.println("User registered");
                case 1 -> System.out.println("User already exists");
            }
        }
        else{
            System.out.println("Wrong Otp");
        }

    }

}
