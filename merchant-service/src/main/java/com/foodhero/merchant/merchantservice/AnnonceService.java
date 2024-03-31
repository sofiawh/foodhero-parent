package com.foodhero.merchant.merchantservice;

import com.foodhero.merchant.merchantservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    public Annonce createAnnonce(Annonce annonce) {
        Annonce savedAnnonce = annonceRepository.save(annonce);

        return savedAnnonce;
    }

    public Annonce getAnnonceById(Long idAnnonce) {
        Annonce annonce = annonceRepository.findById(idAnnonce)
                .orElseThrow(() -> new NotFoundException("Annonce non trouvé avec l'ID : " + idAnnonce));

        return annonce;
    }
    public List<Annonce> getAnnonces(){

        return annonceRepository.findAll();
    }

    public Annonce updateAnnonce(Long idAnnonce, Annonce updatedAnnonce) {
        Annonce existingAnnonce = annonceRepository.findById(idAnnonce)
                .orElseThrow(() -> new NotFoundException("Annonce non trouvée avec l'ID : " + idAnnonce));

        // Mettez à jour les attributs de l'annonce existante avec les données de l'annonce mise à jour
        existingAnnonce.setTitre(updatedAnnonce.getTitre());
        existingAnnonce.setDescription(updatedAnnonce.getDescription());

        return annonceRepository.save(existingAnnonce);
    }

    public void deleteAnnonce(Long idAnnonce) {
        if (!annonceRepository.existsById(idAnnonce)) {
            throw new NotFoundException("Annonce non trouvée avec l'ID : " + idAnnonce);
        }
        annonceRepository.deleteById(idAnnonce);
    }

}

