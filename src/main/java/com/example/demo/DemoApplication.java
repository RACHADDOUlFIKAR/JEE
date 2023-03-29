package com.example.demo;

import com.example.demo.entities.Patient;
import com.example.demo.resporitories.PatientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    private PatientRepositories PatientRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0;i<50;i++){

            PatientRepository.save(new Patient(null,"rachad",new Date(),Math.random()>0.5?true:false, (int) Math.random()*100));
        }

        Page<Patient> patients = PatientRepository.findAll(PageRequest.of(0,10));
                System.out.println();
                System.out.println("Tatal pages :"+patients.getTotalPages());
                System.out.println("Total "+patients.getTotalElements()) ;
                System.out.println("Num Page:"+patients.getNumber());
                System.out.println();
        List<Patient> content=patients.getContent();
        Page<Patient> byma=PatientRepository.findByMalade(true,PageRequest.of(0,7));


        List<Patient> patientList=PatientRepository.chercherPatients(  "%r%",  40);

        byma.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println("--------------------------------------------------");
            Patient patient=PatientRepository.findById(1L) .orElse(  null);
            if (patient!=null){
                System.out.println(patient.getNom());
                System.out.println(patient. isMalade());
            }
            PatientRepository.deleteById(1L);


        });
}}
