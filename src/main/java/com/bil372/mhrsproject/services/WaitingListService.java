package com.bil372.mhrsproject.services;

import com.bil372.mhrsproject.DTOs.WaitingListDTO;
import com.bil372.mhrsproject.entities.Doctor;
import com.bil372.mhrsproject.entities.Hospital;
import com.bil372.mhrsproject.entities.HospitalDepartment;
import com.bil372.mhrsproject.entities.Patient;
import com.bil372.mhrsproject.entities.WaitingList;
import com.bil372.mhrsproject.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WaitingListService {

    private final HospitalRepository hospitalRepository;

    private final WaitingListRepository waitingListRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final HospitalDepartmentRepository hospitalDepartmentRepository;

    public WaitingListService(WaitingListRepository waitingListRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, HospitalDepartmentRepository hospitalDepartmentRepository, HospitalRepository hospitalRepository){
        this.waitingListRepository = waitingListRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.hospitalDepartmentRepository = hospitalDepartmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public List<WaitingListDTO> getDoctorWaitingList(long doctorNationalId){
        Doctor doctor = doctorRepository.findByDoctorNationalId(doctorNationalId).orElseThrow(() -> new RuntimeException("Doctor not found"));
        List<WaitingList> result = waitingListRepository.findByDoctor(doctor);
        return result.stream().map(this::waitinglistDTOMapper).toList();
    }

    public List<WaitingListDTO> getPatientWaitingList(long patientNationalId){
        Patient patient = patientRepository.findByPatientNationalId(patientNationalId).orElseThrow(() -> new RuntimeException("Patient not found"));
        List<WaitingList> result = waitingListRepository.findByPatient(patient);
        return result.stream().map(this::waitinglistDTOMapper).toList();
    }

    public void cancelWaitingListByPatient(int waitingId) {
        if (!waitingListRepository.existsById(waitingId)) {
            throw new RuntimeException("couldnt find wlist");
        }
        waitingListRepository.deleteById(waitingId);
    }

    public void cancelWaitingListByDoctor(int waitingId) {
        if (!waitingListRepository.existsById(waitingId)) {
            throw new RuntimeException("couldnt find wlist");
        }
        waitingListRepository.deleteById(waitingId);
    }

    public void cancelWaitingListByAdmin(int waitingId){
        if (!waitingListRepository.existsById(waitingId)) {
            throw new RuntimeException("couldnt find wlist");
        }
        waitingListRepository.deleteById(waitingId);
    }

    public List<WaitingListDTO> getAdminWaitingList() {
        List<WaitingList> result = waitingListRepository.findAll();
        return result.stream().map(this::waitinglistDTOMapper).toList();
    }

    private WaitingListDTO waitinglistDTOMapper(WaitingList w){

        String doctorName = null;
        if (w.getDoctor() != null) {
            doctorName = w.getDoctor().getFirstName() + " " + w.getDoctor().getLastName();
        }

        String hospitalName = w.getHospital() != null ? w.getHospital().getName() : null;
        String departmentName = w.getDepartment() != null ? w.getDepartment().getBranchName() : null;

        String patientName = null;
        if (w.getPatient() != null) {
            patientName = w.getPatient().getFirstName() + " " + w.getPatient().getLastName();
        }

        return new WaitingListDTO(
                w.getWaitingId(),
                w.getLevel(),
                doctorName,
                hospitalName,
                departmentName,
                patientName,
                w.getRequestDateTime()
        );
    }

     public void joinDoctorWaitingList(long patientNationalId, long doctorId) {

        if (waitingListRepository
            .existsByPatient_PatientNationalIdAndDoctor_DoctorNationalIdAndLevel(
                patientNationalId, doctorId, "doctor")) {
        throw new RuntimeException("Bu doktor için zaten bekleme listesinde kaydınız var.");
        }

        WaitingList entry = new WaitingList();
        entry.setPatient((patientRepository.findByPatientNationalId(patientNationalId)).orElseThrow(() -> new RuntimeException("Patient not found")));
        Doctor doctor = (doctorRepository.findByDoctorNationalId(doctorId)).orElseThrow(() -> new RuntimeException("Doctor not found"));
        entry.setDoctor(doctor);
        entry.setRequestDateTime(LocalDateTime.now());
        entry.setLevel("doctor");
        Hospital hospital = hospitalRepository.findFirstByHospitalDoctors_DoctorNationalId(doctor.getDoctorNationalId());
        HospitalDepartment department = hospitalDepartmentRepository.findFirstByDepartmentDoctors_DoctorNationalId(doctor.getDoctorNationalId());
        entry.setHospital(hospital);
        entry.setDepartment(department);
        waitingListRepository.save(entry);
    }

    public void joinDepartmentWaitingList(long patientNationalId, int departmentId) {

        Hospital hospital = hospitalRepository.findFirstByHospitalDepartments_DepartmentId(departmentId);
        if (waitingListRepository
            .existsByPatient_PatientNationalIdAndHospital_HospitalIdAndDepartment_DepartmentIdAndLevel(
                patientNationalId, hospital.getHospitalId(), departmentId, "department")) {
        throw new RuntimeException("Bu departman için zaten bekleme listesinde kaydınız var.");
        }

        WaitingList entry = new WaitingList();
        entry.setPatient((patientRepository.findByPatientNationalId(patientNationalId)).orElseThrow(() -> new RuntimeException("Patient not found")));
        entry.setDepartment(hospitalDepartmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Deparment not found")));
        entry.setRequestDateTime(LocalDateTime.now());
        entry.setLevel("department");
        entry.setHospital(hospital);

        

        waitingListRepository.save(entry);
    }

}
