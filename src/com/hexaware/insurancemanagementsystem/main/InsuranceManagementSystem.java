package com.hexaware.insurancemanagementsystem.main;

import com.hexaware.insurancemanagementsystem.entity.Policy;
import com.hexaware.insurancemanagementsystem.exception.PolicyNotFoundException;
import com.hexaware.insurancemanagementsystem.service.IPolicyService;
import com.hexaware.insurancemanagementsystem.service.PolicyServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InsuranceManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IPolicyService policyService = new PolicyServiceImpl();
        int choice = 0;

        do {
            System.out.println("\n===== Insurance Management System =====");
            System.out.println("1. Create Policy");
            System.out.println("2. View Policy by ID");
            System.out.println("3. View All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // clear newline

                switch (choice) {

                    case 1:
                        // Create Policy
                        try {
                            System.out.print("Enter Policy ID (number): ");
                            int policyId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Policy Name: ");
                            String name = scanner.nextLine();
                            if (name.matches("\\d+")) {
                                System.out.println("Invalid name! Name cannot be only numbers.");
                                break;
                            }

                            System.out.print("Enter Policy Type: ");
                            String type = scanner.nextLine();
                            if (type.matches("\\d+")) {
                                System.out.println("Invalid type! Type cannot be only numbers.");
                                break;
                            }

                            System.out.print("Enter Coverage Amount: ");
                            double coverage = scanner.nextDouble();

                            System.out.print("Enter Premium: ");
                            double premium = scanner.nextDouble();

                            Policy newPolicy = new Policy(policyId, name, type, coverage, premium);
                            boolean created = policyService.createPolicy(newPolicy);
                            System.out.println(created ? "Policy created successfully." : "Failed to create policy.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter proper data types.");
                            scanner.nextLine(); // clear invalid input
                        }
                        break;

                    case 2:
                        // View Policy by ID
                        try {
                            System.out.print("Enter Policy ID to view: ");
                            int viewId = scanner.nextInt();
                            Policy policy = policyService.getPolicy(viewId);
                            System.out.println("Policy Details:\n" + policy);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Policy ID must be a number.");
                            scanner.nextLine();
                        } catch (PolicyNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // View All
                        List<Policy> policies = policyService.getAllPolicies();
                        if (policies.isEmpty()) {
                            System.out.println("No policies found.");
                        } else {
                            for (Policy p : policies) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 4:
                        // Update Policy
                        try {
                            System.out.print("Enter Policy ID to update: ");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter New Policy Name: ");
                            String newName = scanner.nextLine();
                            if (newName.matches("\\d+")) {
                                System.out.println("Invalid name! Name cannot be only numbers.");
                                break;
                            }

                            System.out.print("Enter New Policy Type: ");
                            String newType = scanner.nextLine();
                            if (newType.matches("\\d+")) {
                                System.out.println("Invalid type! Type cannot be only numbers.");
                                break;
                            }

                            System.out.print("Enter New Coverage Amount: ");
                            double newCoverage = scanner.nextDouble();

                            System.out.print("Enter New Premium: ");
                            double newPremium = scanner.nextDouble();

                            Policy updatedPolicy = new Policy(updateId, newName, newType, newCoverage, newPremium);
                            boolean updated = policyService.updatePolicy(updatedPolicy);
                            System.out.println(updated ? "Policy updated successfully." : "Failed to update policy.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter correct types.");
                            scanner.nextLine();
                        } catch (PolicyNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        // Delete Policy
                        try {
                            System.out.print("Enter Policy ID to delete: ");
                            int deleteId = scanner.nextInt();
                            boolean deleted = policyService.deletePolicy(deleteId);
                            System.out.println(deleted ? "Policy deleted successfully." : "Failed to delete policy.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Policy ID must be a number.");
                            scanner.nextLine();
                        } catch (PolicyNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("Exiting the system. Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 6.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid menu choice! Please enter a number from 1 to 6.");
                scanner.nextLine(); // clear invalid input
            }

        } while (choice != 6);

        scanner.close();
    }
}
