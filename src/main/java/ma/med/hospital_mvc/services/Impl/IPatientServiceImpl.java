package ma.med.hospital_mvc.services.Impl;

import lombok.AllArgsConstructor;
import ma.med.hospital_mvc.entities.Patient;
import ma.med.hospital_mvc.repositories.PatientRepository;
import ma.med.hospital_mvc.services.IPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class IPatientServiceImpl implements IPatientService {
    private PatientRepository patientRepository;

    @Override
    public Page<Patient> getPatients(String name, int page, int size) {

        return patientRepository.findByNameContains(name, PageRequest.of(page, size));
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public void createUpdatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }


}
