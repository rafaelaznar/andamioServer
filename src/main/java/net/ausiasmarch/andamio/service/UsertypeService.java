package net.ausiasmarch.andamio.service;

import net.ausiasmarch.andamio.entity.UsertypeEntity;
import net.ausiasmarch.andamio.exception.ResourceNotFoundException;
import net.ausiasmarch.andamio.repository.UsertypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsertypeService {

    private final UsertypeRepository oUsertypeRepository;
    private final AuthService oAuthService;

    @Autowired
    public UsertypeService(UsertypeRepository oUsertypeRepository, AuthService oAuthService) {
        this.oUsertypeRepository = oUsertypeRepository;
        this.oAuthService = oAuthService;
    }

    public UsertypeEntity get(Long id) {
        oAuthService.OnlyAdmins();
        return oUsertypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserType with id: " + id + " not found"));
    }

    public void validate(Long id) {
        if (!oUsertypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long update(UsertypeEntity oUsertypeEntity) {
        validate(oUsertypeEntity.getId());
        oAuthService.OnlyAdmins();
        oUsertypeRepository.save(oUsertypeEntity);
        return oUsertypeEntity.getId();
    }
}
