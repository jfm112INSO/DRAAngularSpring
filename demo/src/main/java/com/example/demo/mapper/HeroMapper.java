package com.example.demo.mapper;

import com.example.demo.dto.HeroDTO;
import com.example.demo.entity.Hero;
import com.example.demo.entity.Power;
import com.example.demo.repository.PowerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // Muy importante para que Spring detecte este bean
public class HeroMapper {

    private final PowerRepository powerRepository;

    public HeroMapper(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    public Hero toEntity(HeroDTO heroDTO) {
        Hero hero = new Hero();
        hero.setName(heroDTO.getName());

        List<Power> powers = heroDTO.getPowers().stream()
            .map(powerName -> powerRepository.findByName(powerName)
                .orElseGet(() -> powerRepository.save(new Power(powerName))))
            .collect(Collectors.toList());

        hero.setPowers(powers);
        return hero;
    }

    public HeroDTO toDto(Hero hero) {
        List<String> powers = hero.getPowers().stream()
            .map(Power::getName)
            .collect(Collectors.toList());

        return new HeroDTO(hero.getId(), hero.getName(), powers);
    }
}
