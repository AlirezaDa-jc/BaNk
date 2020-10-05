package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Branch;
import ir.maktab.entities.Employee;
import ir.maktab.repository.BranchRepository;
import ir.maktab.repository.Impl.BranchRepositoryImpl;

import java.util.List;

public class BranchService {
    private static Branch branch = new Branch();
    private static BranchRepository repository = new BranchRepositoryImpl();
    private static Scan sc = MainApp.getSc();


    public static Branch getBranch() {
        return branch;
    }

    public static void setBranch(Branch branch) {
        BranchService.branch = branch;
    }

    public static BranchRepository getRepository() {
        return repository;
    }

    public static void setRepository(BranchRepository repository) {
        BranchService.repository = repository;
    }


    public static Branch use() {
        System.out.println("\nBranch Section!");
        List<Branch> all = repository.findAll();
        all.forEach((t) -> System.out.println("id: " + t.getId() + "\nTitle: " + t.getTitle()));
        int id = Integer.parseInt(sc.getString("Enter id Of Branch: "));
        return repository.findById(id);
    }

    public static boolean add() {
        String title = sc.getString("Enter Title: ");
        if (repository.findByTitle(title) != null) {
            System.out.println("Invalid Title");
            return false;
        }
        String description = sc.getString("Enter Description: ");
        EmployeeService.insertBoss();
        int id = Integer.parseInt(sc.getString());
        Employee boss = EmployeeService.findById(id);
        branch.setTitle(title);
        branch.setDescription(description);
        if (boss == null) return false;
        branch.setBoss(boss);
        repository.insert(branch);
        boss.setBranch(branch);
        EmployeeService.updateBranch(boss);
        return true;
    }

    public static void displayAll() {
    }
}
