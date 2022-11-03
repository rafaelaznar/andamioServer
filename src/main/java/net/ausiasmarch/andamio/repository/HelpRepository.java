package net.ausiasmarch.andamio.repository;

import net.ausiasmarch.andamio.entity.HelpEntity;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRepository extends JpaRepository<HelpEntity, Long> {

    Page<HelpEntity> findAll(Pageable oPageable);

    Page<HelpEntity> findByResolutionIdAndDeveloperId(Long id_resolution, Long id_developer, Pageable oPageable);

    Page<HelpEntity> findByResolutionId(Long id_resolution, Pageable oPageable);

    Page<HelpEntity> findByDeveloperId(Long id_developer, Pageable oPageable);

}