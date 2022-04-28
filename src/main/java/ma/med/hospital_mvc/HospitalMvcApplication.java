package ma.med.hospital_mvc;

import ma.med.hospital_mvc.entities.Patient;
import ma.med.hospital_mvc.repositories.PatientRepository;
import ma.med.hospital_mvc.security.services.ISecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalMvcApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository, ISecurityService securityService){
        return args -> {

            for (int i = 0; i < 200 ; i++){
                patientRepository.save(
                        new Patient(null, "Patient"+i,new Date(), Math.random()>0.5?true:false, (12+i)*3));
            }

            securityService.saveNewUser("user", "1234","1234");
            securityService.saveNewUser("admin", "1234","1234");

            securityService.saveNewRole("USER", "it's a user role");
            securityService.saveNewRole("ADMIN", "it's and admin role");

            securityService.addRoleToUser("user", "USER");
            securityService.addRoleToUser("admin", "USER");
            securityService.addRoleToUser("admin", "ADMIN");


            /* patientRepository.findAll().forEach(p -> {
               System.out.println(p.getName());
           });*/
        };
    }
}
