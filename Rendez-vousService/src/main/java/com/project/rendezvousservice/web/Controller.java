package com.project.rendezvousservice.web;

import com.project.rendezvousservice.entities.RendezVous;
import com.project.rendezvousservice.model.Patient;
import com.project.rendezvousservice.repositories.RepositoryRendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/rendezVous")
public class Controller {
    private final RepositoryRendezVous repositoryRendezVous;

    private final RestTemplate restTemplate;
    @Value("${doctor.service.url}")
    private String docteururl;

    @Value("${patient.service.url}")
    private String patienturl;

    public Controller(RepositoryRendezVous repositoryRendezVous, RestTemplate restTemplate) {
        this.repositoryRendezVous = repositoryRendezVous;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all")
    public List<RendezVous> all() {
        List<RendezVous> rendezVous = repositoryRendezVous.findAll();
        for (RendezVous render : rendezVous ) {
            Patient patient = restTemplate.getForObject(patienturl+"/"+render.getPatientId(), Patient.class);
             render.setPatient(patient);

        }
        return rendezVous;
    }




}
