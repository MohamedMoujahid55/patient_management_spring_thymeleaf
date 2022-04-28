package ma.med.hospital_mvc.web;

import lombok.AllArgsConstructor;
import ma.med.hospital_mvc.entities.Patient;
import ma.med.hospital_mvc.repositories.PatientRepository;
import ma.med.hospital_mvc.services.IPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
        PatientRepository patientRepository;
        IPatientService patientService;

        @GetMapping(path = "/")
        public String home(){
            return "home";
        }

        @GetMapping(path = "/user/index")
        public String patients(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "10") int size,
                               @RequestParam(name = "name", defaultValue = "") String name
                               ){

            Page<Patient> pagePatients = patientService.getPatients(name, page, size);
            model.addAttribute("listPatients", pagePatients.getContent());
            model.addAttribute("pages", new int [pagePatients.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("name", name);
            return "patients";
        }

        @GetMapping(path = "/admin/delete")
        public String delete(Long id, String name, int page){
            patientService.deletePatient(id);
            return "redirect:/user/index?page="+page+"&name="+name;
        }

        @GetMapping(path = "/admin/patientForm")
        public String patientForm(Model model){
            model.addAttribute("patient", new Patient());
            return "patientForm";
        }

        @PostMapping(path = "/admin/save")
        public String save(Model model,
                           @Valid Patient p,
                           BindingResult bindingResult,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "") String name){

            if (bindingResult.hasErrors()) return "patientForm";

            patientService.createUpdatePatient(p);
            return "redirect:/user/index?page="+page+"&name="+name;
        }

        @GetMapping(path = "/admin/editPatient")
        public String editpatient(Model model, Long id, String name, int page){
            Patient patient = patientService.getPatientById(id);
            if (patient == null) throw new RuntimeException("Patient does not exist");
            model.addAttribute("patient", patient);
            model.addAttribute("name", name);
            model.addAttribute("page", page);
            return "editPatient";
        }
}
