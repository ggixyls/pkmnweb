package gevorgyan.pkmn.dao;

import gevorgyan.pkmn.entities.CardEntity;
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

    public List<CardEntity> findAll() {
        return cardRepository.findAll();
    }

    public CardEntity save(CardEntity card) {
        return cardRepository.save(card);
    }

    public void deleteById(UUID id) {
        cardRepository.deleteById(id);
    }

    public List<CardEntity> findCardsByOwner(String firstName, String surName, String familyName) {
        return cardRepository.findByPokemonOwner(firstName, surName, familyName);
    }

    public List<CardEntity> findCardsByName(String name) {
        return cardRepository.findByName(name);
    }

    public Optional<CardEntity> findById(UUID id) {
        return cardRepository.findById(id);
    }
    public boolean cardExists(Card card) {
        List<CardEntity> cards = cardRepository.findByName(card.getName());
        return !cards.isEmpty(); // Если список не пуст, значит карта существует
    }
}