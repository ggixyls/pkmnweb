package gevorgyan.pkmn.dao;

import gevorgyan.pkmn.entities.CardEntities;
import gevorgyan.pkmn.models.Card;
import lombok.RequiredArgsConstructor;
import gevorgyan.pkmn.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CardDao {
    @Autowired
    private final CardRepository cardRepository;

    public List<CardEntities> findAll() {
        return cardRepository.findAll();
    }

    public CardEntities save(CardEntities card) {
        return cardRepository.save(card);
    }

    public void deleteById(UUID id) {
        cardRepository.deleteById(id);
    }

    public List<CardEntities> findCardsByOwner(String firstName, String surName, String familyName) {
        return cardRepository.findByPokemonOwner(firstName, surName, familyName);
    }

    public List<CardEntities> findCardsByName(String name) {
        return cardRepository.findByName(name);
    }

    public Optional<CardEntities> findById(UUID id) {
        return cardRepository.findById(id);
    }
    public boolean cardExists(Card card) {
        List<CardEntities> cards = cardRepository.findByName(card.getName());
        return !cards.isEmpty(); // Если список не пуст, значит карта существует
    }
}