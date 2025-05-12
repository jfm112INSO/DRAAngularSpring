package com.example.demo.controller;

import com.example.demo.dto.HeroDTO;
import com.example.demo.entity.Hero;
import com.example.demo.mapper.HeroMapper;
import com.example.demo.repository.HeroRepository;
import com.example.demo.service.MarvelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;
    private final MarvelService marvelService;

    public HeroController(HeroRepository heroRepository, HeroMapper heroMapper, MarvelService marvelService) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
        this.marvelService = marvelService;
    }

    @GetMapping
    public List<HeroDTO> getAllHeroes() {
        return heroRepository.findAll().stream()
                .map(heroMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HeroDTO getHero(@PathVariable Long id) {
        Hero hero = heroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hero not found"));
        return heroMapper.toDto(hero);
    }

    @PostMapping
    public HeroDTO createHero(@RequestBody HeroDTO heroDTO) {
        String imageUrl = marvelService.getHeroImage(heroDTO.getName());
        heroDTO.setImage(imageUrl);
        Hero hero = heroMapper.toEntity(heroDTO);
        Hero savedHero = heroRepository.save(hero);
        return heroMapper.toDto(savedHero);
    }

    @PutMapping("/{id}")
    public HeroDTO updateHero(@PathVariable Long id, @RequestBody HeroDTO heroDTO) {
        Hero existingHero = heroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hero not found"));
        
        existingHero.setName(heroDTO.getName());
        existingHero.setPowers(heroMapper.toEntity(heroDTO).getPowers());
        
        // Get hero image from Marvel API
        String imageUrl = marvelService.getHeroImage(heroDTO.getName());
        existingHero.setImage(imageUrl);
        
        Hero updatedHero = heroRepository.save(existingHero);
        return heroMapper.toDto(updatedHero);
    }

    @DeleteMapping("/{id}")
    public void deleteHero(@PathVariable Long id) {
        Hero hero = heroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hero not found"));
        heroRepository.delete(hero);
    }
}
