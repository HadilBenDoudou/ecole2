package com.ecole.ecole.controllers;


import com.ecole.ecole.models.Eleve;
import com.ecole.ecole.services.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eleves")
public class EleveController {

    @Autowired
    private EleveService eleveService;

    // Récupérer tous les élèves
    @GetMapping
    public List<Eleve> getAll() {
        return eleveService.getAllEleves(); // Utiliser le nom correct de la méthode
    }

    // Récupérer un élève par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Eleve> getById(@PathVariable Long id) {
        Eleve eleve = eleveService.getEleveById(id);
        return (eleve != null) ? ResponseEntity.ok(eleve) : ResponseEntity.notFound().build();
    }

    // Créer un nouvel élève
    @PostMapping
    public Eleve create(@RequestBody Eleve eleve) {
        return eleveService.saveEleve(eleve); // Utiliser le nom correct de la méthode
    }

    // Mettre à jour un élève existant
    @PutMapping("/{id}")
    public ResponseEntity<Eleve> update(@PathVariable Long id, @RequestBody Eleve eleve) {
        if (eleveService.getEleveById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        eleve.setId_eleve(id); // Utiliser le bon nom de la méthode pour définir l'ID
        return ResponseEntity.ok(eleveService.saveEleve(eleve)); // Utiliser le nom correct de la méthode
    }

    // Supprimer un élève par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (eleveService.getEleveById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        eleveService.deleteEleve(id); // Utiliser le nom correct de la méthode
        return ResponseEntity.noContent().build();
    }
}
