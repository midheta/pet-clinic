package com.petclinicdemo.bootstrap;

import com.petclinicdemo.model.*;
import com.petclinicdemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class LoadData implements CommandLineRunner {
    private OwnerService ownerService;
    private VetService vetService;
    private VisitService visitService;
    private PetService petService;
    private SpecialtyService specialtyService;
    private PetTypeService petTypeService;

    @Autowired
    public LoadData(OwnerService ownerService, VetService vetService, VisitService visitService, PetService petService,
                    SpecialtyService specialtyService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.visitService = visitService;
        this.petService = petService;
        this.specialtyService = specialtyService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading owners ...");

        Owner sam = new Owner();
        sam.setFirstName("Sam");
        sam.setLastName("Smith");
        sam.setAddress("Street Blue");
        sam.setCity("London");
        sam.setPhone("02302840328");
        sam = ownerService.save(sam);

        Owner ema = new Owner();
        ema.setFirstName("Ema");
        ema.setLastName("Doe");
        ema.setAddress("Sesame street");
        ema.setCity("New York");
        ema.setPhone("29842904");
        ema = ownerService.save(ema);

        System.out.println("Loading vets ...");
        Vet vet1 = new Vet();
        vet1.setFirstName("James");
        vet1.setLastName("Carter");
        vet1 = vetService.save(vet1);

        PetType cat = new PetType();
        cat.setDescription("cat");
        cat = petTypeService.save(cat);

        PetType dog = new PetType();
        dog.setDescription("dog");
        dog = petTypeService.save(dog);

        Pet samsCat = new Pet();
        samsCat.setName("kitty");
        samsCat.setBirthDate(LocalDate.now());
        samsCat.setOwner(sam);
        samsCat.setPetType(cat);
        samsCat = petService.save(samsCat);

        Pet clunkers = new Pet();
        clunkers.setName("Clunkers");
        clunkers.setBirthDate(LocalDate.now());
        clunkers.setOwner(ema);
        clunkers.setPetType(dog);
        clunkers = petService.save(clunkers);

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");
        surgery = specialtyService.save(surgery);

        vet1.getSpecialityList().add(surgery);
        vetService.save(vet1);

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.of(2000, 11, 21));
        catVisit.setDescription("Regular control");
        catVisit.setPet(samsCat);
        visitService.save(catVisit);

    }
}
