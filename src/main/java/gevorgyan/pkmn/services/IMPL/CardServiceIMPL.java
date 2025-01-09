package gevorgyan.pkmn.services.IMPL;

import gevorgyan.pkmn.models.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import gevorgyan.pkmn.rest.CardResponse;
import gevorgyan.pkmn.rest.RestClient;
import gevorgyan.pkmn.dao.CardDao;
import gevorgyan.pkmn.entities.CardEntity;
import gevorgyan.pkmn.services.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor

public class CardServiceIMPL implements CardService {
    private final CardDao cardDao;

    @Autowired
    private RestClient restClient; // Внедрение RestClient

    @Override
    public List<CardEntity> getAllCards() {
        return cardDao.findAll();
    }

    @Override
    public CardEntity getCardById(UUID id) {
        return cardDao.findById(id).orElse(null);
    }

    @Override
    public CardEntity saveCard(Card card) {
        if (cardDao.cardExists(card)) {
            throw new IllegalArgumentException("Карта уже есть в базе данных");
        }
        if (card.getPokemonOwner() == null) {
            throw new IllegalArgumentException("У карты нет владельца");
        }
        return cardDao.save(CardEntity.toEntity(card));
    }

    @Override
    public CardEntity updateCard(UUID id, CardEntity card) {
        if (cardDao.findById(id).isEmpty()) {
            throw new RuntimeException("Card not found.");
        }
        card.setId(id);
        return cardDao.save(card);
    }

    @Override
    public void deleteCard(UUID id) {
        cardDao.deleteById(id);
    }

    @Override
    public List<CardEntity> getCardsByOwner(String firstName, String surName, String familyName) {
        return cardDao.findCardsByOwner(firstName, surName, familyName);
    }

    @Override
    public List<CardEntity> getCardsByName(String name) {
        return cardDao.findCardsByName(name);
    }


    public String getCardImageByName(String cardName) {
        CardResponse response = restClient.getCardByName(cardName); // Вызов RestClient
        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
            // Достаем URL большого изображения
            return response.getData().get(0).getImages().getLarge();
        }
        throw new IllegalArgumentException("Карта с именем " + cardName + " не найдена.");
    }
}
