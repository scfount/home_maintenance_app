package com.appliance.homeMaintenanceApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class ApplianceResource {
    @Autowired
    private ApplianceService applianceService;

    @GetMapping("/appliances")
    public List<Appliance> getAllAppliances() {
        return applianceService.getAllAppliances();
    }

    @GetMapping("/appliance/{id}")
    public Appliance getAppliance(@PathVariable int id) {
        return applianceService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAppliance(@PathVariable int id) {

        applianceService.deleteById(id);

        Appliance appliance = applianceService.deleteById(id);

        if (appliance != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Appliance> updateAppliance(@PathVariable int id,
                                               @RequestBody Appliance appliance) {

        Appliance applianceUpdated = applianceService.save(appliance);

        return new ResponseEntity<>(applianceUpdated, HttpStatus.OK);
    }

    @PostMapping("/appliance")
    public ResponseEntity<Void> createAppliance(@RequestBody Appliance appliance) {

        Appliance createdAppliance = applianceService.save(appliance);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAppliance.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
