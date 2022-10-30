package net.ausiasmarch.andamio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.ausiasmarch.andamio.entity.DeveloperEntity;
import net.ausiasmarch.andamio.exception.ResourceNotFoundException;
import net.ausiasmarch.andamio.exception.ResourceNotModifiedException;
import net.ausiasmarch.andamio.helper.RandomHelper;
import net.ausiasmarch.andamio.repository.DeveloperRepository;
import net.ausiasmarch.andamio.repository.UsertypeRepository;

@Service
public class DeveloperService {

    @Autowired
    UsertypeService oUsertypeService;

    @Autowired
    TeamService oTeamService;

    private final DeveloperRepository oDeveloperRepository;
    private final AuthService oAuthService;

    @Autowired
    public DeveloperService(DeveloperRepository oDeveloperRepository, AuthService oAuthService) {
        this.oDeveloperRepository = oDeveloperRepository;
        this.oAuthService = oAuthService;
    }

    private final String ANDAMIO_DEFAULT_PASSWORD = "73ec8dee81ea4c9e7515d63c9e5bbb707c7bc4789363c5afa401d3aa780630f6";
    private final String[] NAMES = {"Jose", "Mark", "Elen", "Toni", "Hector", "Jose", "Laura", "Vika", "Sergio",
        "Javi", "Marcos", "Pere", "Daniel", "Jose", "Javi", "Sergio", "Aaron", "Rafa", "Lionel", "Borja"};

    private final String[] SURNAMES = {"Penya", "Tatay", "Coronado", "Cabanes", "Mikayelyan", "Gil", "Martinez",
        "Bargues", "Raga", "Santos", "Sierra", "Arias", "Santos", "Kuvshinnikova", "Cosin", "Frejo", "Marti",
        "Valcarcel", "Sesa", "Lence", "Villanueva", "Peyro", "Navarro", "Navarro", "Primo", "Gil", "Mocholi",
        "Ortega", "Dung", "Vi", "Sanchis", "Merida", "Aznar", "Aparici", "Tarazón", "Alcocer", "Salom", "Santamaría"};

    public void validate(Long id) {
        if (!oDeveloperRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public DeveloperEntity get(Long id) {
        oAuthService.OnlyAdmins();
        return oDeveloperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer with id: " + id + " not found"));
    }

    public Page<DeveloperEntity> getPage(Long id_team, Long id_usertype, int page, int size) {
        oAuthService.OnlyAdmins();
        Pageable oPageable = PageRequest.of(page, size);
        if (id_team == null && id_usertype == null) {
            return oDeveloperRepository.findAll(oPageable);
        } else if (id_team == null) {
            return oDeveloperRepository.findByUsertypeId(id_usertype, oPageable);
        } else if (id_usertype == null) {
            return oDeveloperRepository.findByTeamId(id_team, oPageable);
        } else {
            return oDeveloperRepository.findByTeamIdAndUsertypeId(id_team, id_usertype, oPageable);
        }
    }

    public Long count() {
        oAuthService.OnlyAdmins();
        return oDeveloperRepository.count();
    }

    public Long update(DeveloperEntity oDeveloperEntity) {
        validate(oDeveloperEntity.getId());
        oAuthService.OnlyAdmins();
        return oDeveloperRepository.save(oDeveloperEntity).getId();
    }

    public Long create(DeveloperEntity oNewDeveloperEntity) {
        oAuthService.OnlyAdmins();
        oNewDeveloperEntity.setId(0L);
        return oDeveloperRepository.save(oNewDeveloperEntity).getId();
    }

    public Long delete(Long id) {
        oAuthService.OnlyAdmins();
        validate(id);
        oDeveloperRepository.deleteById(id);
        if (oDeveloperRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public DeveloperEntity generate() {
        oAuthService.OnlyAdmins();
        return oDeveloperRepository.save(generateRandomUser());
    }

    public Long generateSome(Integer amount) {
        oAuthService.OnlyAdmins();
        List<DeveloperEntity> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            DeveloperEntity oUsuarioEntity = generateRandomUser();
            oDeveloperRepository.save(oUsuarioEntity);
            userList.add(oUsuarioEntity);
        }
        return oDeveloperRepository.count();
    }

    private DeveloperEntity generateRandomUser() {
        DeveloperEntity oDeveloperEntity = new DeveloperEntity();
        oDeveloperEntity.setName(generateName());
        oDeveloperEntity.setSurname(generateSurname());
        oDeveloperEntity.setLast_name(generateSurname());
        oDeveloperEntity.setEmail(generateEmail(oDeveloperEntity.getName(), oDeveloperEntity.getSurname()));
        oDeveloperEntity.setUsertype(oUsertypeService.getOneRandom());
        oDeveloperEntity.setTeam(oTeamService.getOneRandom());
        oDeveloperEntity.setUsername(oDeveloperEntity.getName() + "_" + oDeveloperEntity.getSurname());
        oDeveloperEntity.setPassword(ANDAMIO_DEFAULT_PASSWORD); // andamio
        return oDeveloperEntity;
    }

    private String generateName() {
        return NAMES[RandomHelper.getRandomInt(0, NAMES.length - 1)].toLowerCase();
    }

    private String generateSurname() {
        return SURNAMES[RandomHelper.getRandomInt(0, SURNAMES.length - 1)].toLowerCase();
    }

    private String generateEmail(String name, String surname) {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(surname);
        return getFromList(list) + "_" + getFromList(list) + "@daw.tk";
    }

    private String getFromList(List<String> list) {
        int randomNumber = RandomHelper.getRandomInt(0, list.size() - 1);
        String value = list.get(randomNumber);
        list.remove(randomNumber);
        return value;
    }

}
