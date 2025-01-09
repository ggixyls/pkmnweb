package gevorgyan.pkmn.repositories;

import gevorgyan.pkmn.entities.CardEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import gevorgyan.pkmn.entities.CardEntities;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntities, UUID> {

    @Query(
            """
                    SELECT card
                    FROM CardEntity as card
                    WHERE card.pokemonOwner.firstName = :firstName
                      AND card.pokemonOwner.surName = :surName
                      AND card.pokemonOwner.familyName = :familyName
                    """
    )
    List<CardEntities> findByPokemonOwner(String firstName, String surName, String familyName);
    List<CardEntities> findByName(String name);

}