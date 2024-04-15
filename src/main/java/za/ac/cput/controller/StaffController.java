package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.Staff;
import za.ac.cput.service.PatientService;
import za.ac.cput.service.StaffService;

@Controller

public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private PatientService patientService;

    @GetMapping("/login")
    public String showForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff";
    }

    @GetMapping("/loginCredentials")
    public String showLogin(Model model) {
        model.addAttribute("loginCredentials", new Staff());
        return "loginCredentials";
    }

    @GetMapping("/navigateToLogin")
    public String showLoginPage() {
        return "loginCredentials";
    }


    @PostMapping("/save")
    public String saveStaff(Staff staff) {
        staffService.create((staff));
        return "redirect:/loginCredentials";

    }

    @PostMapping("/login")
    public String validateLogin(@RequestParam String id, @RequestParam String role, @RequestParam String name, @RequestParam String password, Model model) {
        boolean isValidLogin = staffService.validateLogin(name, role, id, password);
        if (isValidLogin) {
            if (role.equals("Doctor")) {
                return "doctorView";
            } else if (role.equals("Pharmacist")) {
                return "pharmacistView";
            } else if (role.equals("Receptionist")) {
                model.addAttribute("patient", new Patient());
                return "receptionistView";
            } else {
                return "incorrect";
            }
        } else {
            return "incorrect";
        }
    }




    @PostMapping("/savePatient")
    public String savePatient(Patient patient){
        patientService.create(patient);
        return "receptionistView";
    }
}



