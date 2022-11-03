package net.ausiasmarch.andamio.api;

import org.springdoc.api.annotations.ParameterObject;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import net.ausiasmarch.andamio.entity.HelpEntity;
import net.ausiasmarch.andamio.service.HelpService;


@RestController
@RequestMapping("/help")
public class HelpController {

    @Autowired
    HelpService oHelpService;

    @GetMapping("/{id}")
    public ResponseEntity<HelpEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<HelpEntity>(oHelpService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable(value = "id") Long id){
        return oHelpService.delete(id);
    }

    @PostMapping("/generate")
    public ResponseEntity<HelpEntity> generate() {
        return new ResponseEntity<>(oHelpService.generate(), HttpStatus.OK);
    }

    @PostMapping("/generate/{amount}")
    public ResponseEntity<Long> generateSome(@PathVariable(value = "amount") Integer amount) {
        return new ResponseEntity<>(oHelpService.generateSome(amount), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody HelpEntity oHelpEntity) {
        return new ResponseEntity<Long>(oHelpService.update(oHelpEntity), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<HelpEntity>> getPage(
    @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
    @RequestParam(name = "resolution", required = false) Long id_resolution,
    @RequestParam(name = "developer", required = false) Long id_developer
    ) {
    return new ResponseEntity<Page<HelpEntity>>(oHelpService.getPage(oPageable, id_resolution, id_developer), HttpStatus.OK);
    }
}
