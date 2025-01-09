package gevorgyan.pkmn.services;

import gevorgyan.pkmn.entities.CardEntities;
import gevorgyan.pkmn.models.Card;

import java.util.List;
import java.util.UUID;

public interface CardService {
    List<CardEntities> getAllCards();
    CardEntities getCardById(UUID id);
    CardEntities saveCard(Card card);
    CardEntities updateCard(UUID id, CardEntities card);
    void deleteCard(UUID id);
    List<CardEntities> getCardsByOwner(String firstName, String surName, String familyName);
    List<CardEntities> getCardsByName(String name);
    String getCardImageByName(String cardName); // Новый метод
}