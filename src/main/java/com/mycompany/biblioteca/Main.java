
package com.mycompany.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Loan> loans = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    
    //CLIENT
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
        
        System.out.println("Client List");
        if (clients.isEmpty()) {
            System.out.println("No clients registered.");
            return;
        }
        for (Client c : clients) {
            System.out.println(c);
        }
    }
    
    public static void searchClient() {
        
        System.out.println("Search Client");
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
    
    public static void updateClient() {
        
        System.out.println("Update Client");
        System.out.print("Enter Client ID to update: ");
        String id = sc.nextLine();

        for (Client c : clients) {
            if (c.getId().equals(id)) {
                System.out.print("New Name: ");
                String name = sc.nextLine();
                System.out.print("New Phone: ");
                String phone = sc.nextLine();
                System.out.print("New Email: ");
                String email = sc.nextLine();

                c.setName(name);
                c.setPhone(phone);
                c.setEmail(email);

                System.out.println("Client updated successfully.");
                return;
            }
        }
        System.out.println("Client not found.");
    }
    
    public static void deleteClient() {
        
        System.out.println("Delete Client");
        System.out.print("Enter Client ID to delete: ");
        String id = sc.nextLine();

        for (Client c : clients) {
            if (c.getId().equals(id)) {
                clients.remove(c);
                System.out.println("Client deleted successfully.");
                return;
            }
        }
        System.out.println("Client not found.");
    }
    
    //BOOK
    public static void createBook() {
        
        System.out.println("Register Book");
        System.out.print("Code: ");
        String code = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Publication Year: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.print("Author: ");
        String author = sc.nextLine();

        Book book = new Book(code, title, year, author);
        books.add(book);
        System.out.println("Book registered successfully.");
    }
    
    public static void listBooks() {

        System.out.println("Book List");
        if (books.isEmpty()) {
            System.out.println("No books registered.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }
    
    public static void searchBook() {

        System.out.println("Search Book");
        System.out.print("Enter Book Code: ");
        String code = sc.nextLine();

        for (Book b : books) {
            if (b.getCode().equals(code)) {
                System.out.println("Book found: " + b);
                return;
            }
        }
        System.out.println("Book not found.");
    }
    
    public static void updateBook() {

        System.out.println("Update Book");
        System.out.print("Enter Book Code to update: ");
        String code = sc.nextLine();

        for (Book b : books) {
            if (b.getCode().equals(code)) {
                System.out.print("New Title: ");
                String title = sc.nextLine();
                System.out.print("New Publication Year: ");
                int year = Integer.parseInt(sc.nextLine());
                System.out.print("New Author: ");
                String author = sc.nextLine();

                b.setTitle(title);
                b.setPublicationYear(year);
                b.setAuthor(author);

                System.out.println("Book updated successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }
    
    public static void deleteBook() {

        System.out.println("Delete Book");
        System.out.print("Enter Book Code to delete: ");
        String code = sc.nextLine();

        for (Book b : books) {
            if (b.getCode().equals(code)) {
                books.remove(b);
                System.out.println("Book deleted successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }
    
    
    //LOAN
    public static void createLoan() {

        System.out.println("Register Loan");
        System.out.print("Loan ID: ");
        String loanId = sc.nextLine();
        System.out.print("Client ID: ");
        String clientId = sc.nextLine();
        System.out.print("Book Code: ");
        String bookCode = sc.nextLine();

        Client foundClient = null;
        for (Client c : clients) {
            if (c.getId().equals(clientId)) {
                foundClient = c;
                break;
            }
        }

        Book foundBook = null;
        for (Book b : books) {
            if (b.getCode().equals(bookCode)) {
                foundBook = b;
                break;
            }
        }

        if (foundClient == null) {
            System.out.println("Client not found.");
            return;
        }
        if (foundBook == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!foundBook.isAvailable()) {
            System.out.println("Book is not available.");
            return;
        }

        Loan loan = new Loan(loanId, foundClient, foundBook, LocalDate.now(), "ACTIVE");
        loans.add(loan);
        foundBook.setAvailable(false);
        System.out.println("Loan registered successfully.");
    }
    
    

    public static void main(String[] args) {
        
    }
}
