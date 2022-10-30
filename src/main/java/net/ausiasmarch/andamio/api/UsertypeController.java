package net.ausiasmarch.andamio.api;

import net.ausiasmarch.andamio.entity.UsertypeEntity;
import net.ausiasmarch.andamio.service.UsertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/usertype")
public class UsertypeController {

    private final UsertypeService oUsertypeService;

    @Autowired
    public UsertypeController(UsertypeService oUsertypeService) {
        this.oUsertypeService = oUsertypeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsertypeEntity> get(@PathVariable Long id) {
        return new ResponseEntity<>(oUsertypeService.get(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody UsertypeEntity oUsertypeEntity) {
        return new ResponseEntity<>(oUsertypeService.update(oUsertypeEntity), HttpStatus.OK);
    }
}
