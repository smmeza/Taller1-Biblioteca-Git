
package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    static ArrayList<Client> clients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    
    public static void createClient() {
        
        System.out.println("Register Client");
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Client client = new Client(id, name, phone, email);
        clients.add(client);
        System.out.println("Client registered successfully.");
    }
    
    public static void listClients() {
        
        System.out.println("--- Client List ---");
        if (clients.isEmpty()) {
            System.out.println("No clients registered.");
            return;
        }
        for (Client c : clients) {
            System.out.println(c);
        }
    }
    
    public static void searchClient() {
        
        System.out.println("--- Search Client ---");
        System.out.print("Enter Client ID: ");
        String id = sc.nextLine();

        for (Client c : clients) {
            if (c.getId().equals(id)) {
                System.out.println("Client found: " + c);
                return;
            }
        }
        System.out.println("Client not found.");
    }
    
    

    public static void main(String[] args) {
        
    }
}
