package Jobsheet;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GcashApp {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/goashapp_db";
    private static final String DB_USER = "gcashapp_user";
    private static final String DB_PASSWORD = "MyS3cure!Pass";
    
    private Connection connection;
    private int currentUserId = -1; // Tracks logged-in user

    public void UserAuthentication() {
        try {
            // Initialize database connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            createUsersTable();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    // Create users table if it doesn't exist
    private void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                     "Name VARCHAR(100) NOT NULL, " +
                     "Email VARCHAR(100) UNIQUE NOT NULL, " +
                     "Number VARCHAR(20) UNIQUE NOT NULL, " +
                     "PIN VARCHAR(64) NOT NULL)"; // Store hashed PINs
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    // 4. Registration method with validation
    public boolean registerUser(String name, String email, String number, String pin) {
        // Validate all fields
        if (!isValidName(name)) {
            System.out.println("Invalid name. Must be 3-100 characters.");
            return false;
        }
        
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        
        if (!isValidPhoneNumber(number)) {
            System.out.println("Invalid phone number. Use 10-15 digits.");
            return false;
        }
        
        if (!isValidPIN(pin)) {
            System.out.println("Invalid PIN. Must be 4-6 digits.");
            return false;
        }
        
        // Hash the PIN for security
        String hashedPIN = hashPIN(pin);
        
        // Insert into database
        String sql = "INSERT INTO users (Name, Email, Number, PIN) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, number);
            pstmt.setString(4, hashedPIN);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("Email or phone number already registered.");
            } else {
                System.out.println("Registration error: " + e.getMessage());
            }
            return false;
        }
    }

    // 5. Validation methods
    private boolean isValidName(String name) {
        return name != null && name.length() >= 3 && name.length() <= 100;
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && Pattern.compile(emailRegex).matcher(email).matches();
    }
    
    private boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("\\d{10,15}");
    }
    
    private boolean isValidPIN(String pin) {
        return pin != null && pin.matches("\\d{4,6}");
    }
    
    // Simple PIN hashing (in real app use stronger hashing like BCrypt)
    private String hashPIN(String pin) {
        return Integer.toString(pin.hashCode()); // Simplified for example
    }

    // 6. Login method
    public int login(String emailOrNumber, String pin) {
        if (currentUserId != -1) {
            System.out.println("Already logged in. Please logout first.");
            return -1;
        }
        
        String hashedPIN = hashPIN(pin);
        String sql = "SELECT ID FROM users WHERE (Email = ? OR Number = ?) AND PIN = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, emailOrNumber);
            pstmt.setString(2, emailOrNumber);
            pstmt.setString(3, hashedPIN);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                currentUserId = rs.getInt("ID");
                System.out.println("Login successful!");
                return currentUserId;
            } else {
                // 7. Handle login failure scenarios
                handleFailedLogin(emailOrNumber);
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return -1;
        }
    }
    
    // 7. Handle failed login scenarios
    private void handleFailedLogin(String emailOrNumber) {
        System.out.println("Login failed. Possible reasons:");
        
        // Check if email/number exists
        String checkSql = "SELECT ID FROM users WHERE Email = ? OR Number = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(checkSql)) {
            pstmt.setString(1, emailOrNumber);
            pstmt.setString(2, emailOrNumber);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("- Incorrect PIN");
                System.out.println("Forgot PIN? Use account recovery options.");
            } else {
                System.out.println("- Account not found");
                System.out.println("Please register first.");
            }
        } catch (SQLException e) {
            System.out.println("Error checking account: " + e.getMessage());
        }
    }

    // 8. Change PIN method
    public boolean changePIN(String oldPIN, String newPIN) {
        if (currentUserId == -1) {
            System.out.println("Please login first.");
            return false;
        }
        
        if (!isValidPIN(newPIN)) {
            System.out.println("Invalid new PIN format.");
            return false;
        }
        
        String checkSql = "SELECT ID FROM users WHERE ID = ? AND PIN = ?";
        String updateSql = "UPDATE users SET PIN = ? WHERE ID = ?";
        
        try {
            // Verify old PIN first
            String oldHashedPIN = hashPIN(oldPIN);
            try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                checkStmt.setInt(1, currentUserId);
                checkStmt.setString(2, oldHashedPIN);
                
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("Incorrect old PIN.");
                    return false;
                }
            }
            
            // Update to new PIN
            String newHashedPIN = hashPIN(newPIN);
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setString(1, newHashedPIN);
                updateStmt.setInt(2, currentUserId);
                
                int affectedRows = updateStmt.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error changing PIN: " + e.getMessage());
            return false;
        }
    }

    // 9. Logout method
    public void logout() {
        if (currentUserId != -1) {
            System.out.println("Logging out user ID: " + currentUserId);
            currentUserId = -1;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    // Close database connection
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        GcashApp auth = new GcashApp();
        auth.UserAuthentication();
        Scanner scanner = new Scanner(System.in);
        
        // Test registration
        System.out.println("=== Registration Test ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String number = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        if (auth.registerUser(name, email, number, pin)) {
            System.out.println("Registration successful!");
        }
        
        // Test login
        System.out.println("\n=== Login Test ===");
        System.out.print("Enter email/number: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String loginPin = scanner.nextLine();
        
        int userId = auth.login(loginId, loginPin);
        if (userId != -1) {
            System.out.println("Logged in with user ID: " + userId);
            
            // Test change PIN
            System.out.println("\n=== Change PIN Test ===");
            System.out.print("Enter old PIN: ");
            String oldPin = scanner.nextLine();
            System.out.print("Enter new PIN: ");
            String newPin = scanner.nextLine();
            
            if (auth.changePIN(oldPin, newPin)) {
                System.out.println("PIN changed successfully!");
            }
            
            // Test logout
            System.out.println("\n=== Logout Test ===");
            auth.logout();
        }
        
        auth.close();
        scanner.close();
    }
}