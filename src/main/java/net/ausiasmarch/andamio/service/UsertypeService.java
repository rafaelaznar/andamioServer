package net.ausiasmarch.andamio.service;

import net.ausiasmarch.andamio.entity.UsertypeEntity;
import net.ausiasmarch.andamio.exception.ResourceNotFoundException;
import net.ausiasmarch.andamio.helper.RandomHelper;
import net.ausiasmarch.andamio.repository.UsertypeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Long count() {
        oAuthService.OnlyAdmins();
        return oUsertypeRepository.count();
    }

    public UsertypeEntity getOneRandom() {
        UsertypeEntity oTipoProductoEntity = null;
        int iPosicion = RandomHelper.getRandomInt(0, (int) oUsertypeRepository.count() - 1);
        Pageable oPageable = PageRequest.of(iPosicion, 1);
        Page<UsertypeEntity> tipoProductoPage = oUsertypeRepository.findAll(oPageable);
        List<UsertypeEntity> tipoProductoList = tipoProductoPage.getContent();
        oTipoProductoEntity = oUsertypeRepository.getById(tipoProductoList.get(0).getId());
        return oTipoProductoEntity;
    }
}