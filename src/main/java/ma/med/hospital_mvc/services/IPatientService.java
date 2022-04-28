package ma.med.hospital_mvc.services;

import ma.med.hospital_mvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPatientService {
    Page<Patient> getPatients(String name, int page, int size);
    void deletePatient(Long id);
    void createUpdatePatient(Patient patient);
    Patient getPatientById(Long id);
}
