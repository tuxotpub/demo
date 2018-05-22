package org.tuxotpub.booksmanager.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tuxotpub.booksmanager.api.v1.dtos.MagazineDTO;
import org.tuxotpub.booksmanager.api.v1.dtos.MagazinesDTO;
import org.tuxotpub.booksmanager.services.parchments.MagazineService;


/**
 * Created by tuxsamo.
 */

@RestController @RequestMapping(MagazineController.BASE_PATH)
@Api(tags = {"magazineDescription"}) @PropertySource(ignoreResourceNotFound=true,value="classpath:swagger.properties")
public class MagazineController {

    public static final String BASE_PATH = "/api/v1/magazines/";
    public static final String FINDBYID_PATH = BASE_PATH + "findbyid/";
    private final MagazineService magazineService;
    //private final ParchmentService<MagazineDTO, Magazine> magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @ApiOperation(value = "Create or Update magazine", notes="Hier you can create or update magazine")
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public MagazineDTO createMagazine(@RequestBody MagazineDTO magazineDTO){
        return magazineService.create(magazineDTO);
    }

    @ApiOperation(value = "View List of Authors", notes="Hier is the list of all Authors")
    @GetMapping("all")
    public MagazinesDTO getAllMagazines(){
        return new MagazinesDTO( magazineService.getAll() );
    }

    @ApiOperation(value = "Find magazine by id", notes="Hier you can find magazine by id")
    @GetMapping("findbyid/{id}")
    public MagazineDTO getMagazineById(@PathVariable Long id){
        return magazineService.getById(id);
    }

    @ApiOperation(value = "Find magazine by isbnTest", notes="Hier you can find magazine by isbnTest")
    @GetMapping("findbyisbn/{isbn}")
    public MagazineDTO getMagazineByIsbn(@PathVariable String isbn){
        return magazineService.getByIsbn(isbn);
    }

    @ApiOperation(value = "Update magazine by id", notes="Hier you can update magazine by id")
    @PutMapping("updatebyid/{id}")
    public MagazineDTO updateMagazine(@PathVariable Long id, @RequestBody MagazineDTO magazineDTO){
        return magazineService.updateById(id, magazineDTO);
    }

    @ApiOperation(value = "Delete magazine by id", notes="Hier you can delete magazine by id")
    @DeleteMapping("deletebyid/{id}")
    public void deleteMagazine(@PathVariable Long id){
        magazineService.deleteById(id);
    }
}
