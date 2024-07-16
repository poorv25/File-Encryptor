package views;

import Model.Data;
import dao.DataDAO;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {

    private String email;
    UserView (String email){
        this.email=email;
    }
    public void home(){
        do {
            System.out.println("Welcome " + this.email );
            System.out.println("Press 1 to show hidden files");
            System.out.println("Press 2 to hide a file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("Press 0 to exit");
            Scanner ip=new Scanner(System.in);
            int ch=Integer.parseInt(ip.nextLine());
            switch (ch){

                case 1 -> {
                    try {
                        List< Data> files= DataDAO.getAllFiles(this.email);
                        System.out.println("ID- File Name");
                        for(Data file: files){
                            System.out.println(file.getId() + " - " + file.getFilename());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 ->{
                    System.out.println("Enter the file path");
                    String path =ip.nextLine();
                    File f= new File(path);
                    Data file = new Data (0,f.getName(),path,this.email);
                    try {
                        DataDAO.hideFile(file);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3->{
                    List< Data> files= null;
                    try {
                        files = DataDAO.getAllFiles(this.email);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("ID- File Name");
                    for(Data file: files){
                        System.out.println(file.getId() + " - " + file.getFilename());
                    }
                    System.out.println("Enter id of file to unhide it");
                    int id= Integer.parseInt(ip.nextLine());
                    boolean isValid=false;
                    for (Data file: files){
                        if(file.getId()==id){
                            isValid=true;
                            break;
                        }
                    }
                    if (isValid){
                        try {
                            DataDAO.unhide(id);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        System.out.println("Wrong id");
                    }
                }
                case 0->{
                    System.exit(0);
                }
                default -> System.out.println("Wrong input");
            }

        }while(true);

  }
}
