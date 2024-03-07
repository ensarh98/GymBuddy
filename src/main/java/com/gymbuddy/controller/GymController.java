package com.gymbuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.gymbuddy.db.model.GymRecord;
import com.gymbuddy.gym.Gym;
import com.gymbuddy.gym.GymService;

/**
 * Controller class for managing gyms.
 * Handles requests for retrieving, creating, updating and deleting items.
 *
 * @author Ensar Horozović
 */
@RestController
@CrossOrigin
@RequestMapping("/gyms")
public class GymController extends BaseController {

    @Autowired
    private GymService gymService;

    @GetMapping()
    public List<Gym> getGyms() {
        return gymService.getGyms();
    }

    @PostMapping()
    public Integer createGym(@RequestBody GymRecord gym) {
        return gymService.createGym(gym);
    }

    @PutMapping("/{id}")
    public void updateGym(@PathVariable Integer id, @RequestBody Gym gym) {
        gymService.updateGym(id, gym);
    }

    @DeleteMapping("/{id}")
    public void deleteGym(Integer id) {
        gymService.deleteGym(id);
    }
}
