package gevorgyan.pkmn.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import gevorgyan.pkmn.rest.ETConverter;
import gevorgyan.pkmn.models.*;
import jakarta.persistence.*;

import static org.hibernate.type.SqlTypes.JSON;

@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntities implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name="name")
    private String name;
    @Column(columnDefinition = "smallint")
    private short hp;

    @Column(name="cardNumber")
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    @Column(name="stage")
    private PokemonStage pokemonStage;
    @Column(name="retreat_cost")
    private String retreatCost;

    @Convert(converter = ETConverter.class)
    @Column(name="pokemon_type", nullable = true)
    private EnergyType pokemonType;
    @Convert(converter = ETConverter.class)
    @Column(name="weakness_type", nullable = true)
    private EnergyType weaknessType;
    @Convert(converter = ETConverter.class)
    @Column(name="resistance_type", nullable = true)
    private EnergyType resistanceType;

    @Column(name="game_set")
    private String gameSet;
    @Column(name="regulation_mark")
    private char regulationMark;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "pokemon_owner_id")
    private StudentEntities pokemonOwner;

    @JdbcTypeCode(JSON)
    @Column(name="attack_skills", columnDefinition = "JSON")
    private List<AttackSkill> skills;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "evolves_from_id")
    private CardEntities evolvesFrom;

    @Override
    public String toString() {
        return "Card{" +
                "pokemonStage=" + pokemonStage +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", pokemonType=" + pokemonType +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", owner=" + ((pokemonOwner != null) ? pokemonOwner.toString() : pokemonOwner)+
                '}';
    }

    public static CardEntities toEntity(Card card) {
        if (card != null) {
            return CardEntities.builder()
                    .id(UUID.randomUUID())
                    .pokemonStage(card.getPokemonStage())
                    .name(card.getName())
                    .hp((short) card.getHp())
                    .pokemonType(card.getPokemonType())
                    .evolvesFrom(toEntity(card.getEvolvesFrom()))
                    .skills(card.getSkills())
                    .weaknessType(card.getWeaknessType())
                    .resistanceType(card.getResistanceType())
                    .retreatCost(card.getRetreatCost())
                    .gameSet(card.getGameSet())
                    .regulationMark(card.getRegulationMark())
                    .pokemonOwner(StudentEntities.toEntity(card.getPokemonOwner()))
                    .cardNumber(card.getNumber())
                    .build();
        }
        return null;
    }
}