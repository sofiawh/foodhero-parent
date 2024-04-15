package com.foodhero.comment.commentservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentRepository extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findAllByAssociationId(Long associationId);
    List<Commentaire> findAllByUserId(Long userId);
    List<Commentaire> findAllByAnnonceId(Long associationId);
}
