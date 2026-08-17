
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
    
    public static void returnLoan() {

        System.out.println("Return Loan");
        System.out.print("Enter Loan ID: ");
        String loanId = sc.nextLine();

        for (Loan l : loans) {
            if (l.getLoanId().equals(loanId)) {
                if (l.getStatus().equals("RETURNED")) {
                    System.out.println("This loan was already returned.");
                    return;
                }
                l.setStatus("RETURNED");
                l.getBook().setAvailable(true);
                System.out.println("Loan returned successfully.");
                return;
            }
        }
        System.out.println("Loan not found.");
    }
    
    public static void listActiveLoans() {

        System.out.println("Active Loans");
        boolean found = false;
        for (Loan l : loans) {
            if (l.getStatus().equals("ACTIVE")) {
                System.out.println(l);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active loans.");
        }
    }
    
    
    public static void main(String[] args) {
        int option;

        do {
            System.out.println("");
            System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Register Client");
            System.out.println("2. List Clients");
            System.out.println("3. Search Client");
            System.out.println("4. Update Client");
            System.out.println("5. Delete Client");
            System.out.println("6. Register Book");
            System.out.println("7. List Books");
            System.out.println("8. Search Book");
            System.out.println("9. Update Book");
            System.out.println("10. Delete Book");
            System.out.println("11. Register Loan");
            System.out.println("12. Return Loan");
            System.out.println("13. List Active Loans");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                
                case 1:
                    createClient();
                    break;
                case 2:
                    listClients();
                    break;
                case 3:
                    searchClient();
                    break;
                case 4:
                    updateClient();
                    break;
                case 5:
                    deleteClient();
                    break;
                case 6:
                    createBook();
                    break;
                case 7:
                    listBooks();
                    break;
                case 8:
                    searchBook();
                    break;
                case 9:
                    updateBook();
                    break;
                case 10:
                    deleteBook();
                    break;
                case 11:
                    createLoan();
                    break;
                case 12:
                    returnLoan();
                    break;
                case 13:
                    listActiveLoans();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        } while (option != 0);
    }
    
    
}
