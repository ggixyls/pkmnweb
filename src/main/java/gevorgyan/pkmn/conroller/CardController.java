package gevorgyan.pkmn.conroller;

import gevorgyan.pkmn.entities.CardEntities;
import gevorgyan.pkmn.entities.StudentEntities;
import gevorgyan.pkmn.models.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import gevorgyan.pkmn.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    @Autowired
    private final CardService cardService;

    @GetMapping("")
    public List<CardEntities> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CardEntities> getCardById(@PathVariable UUID id) {
        CardEntities card = cardService.getCardById(id);
        return card != null ? ResponseEntity.ok(card) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public CardEntities saveCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @PutMapping("/id/{id}")
    public CardEntities updateCard(@PathVariable UUID id, @RequestBody CardEntities card) {
        return cardService.updateCard(id, card);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner")
    public List<CardEntities> getCardsByOwner(@RequestBody StudentEntities ownerRequest) {
        return cardService.getCardsByOwner(ownerRequest.getFirstName(), ownerRequest.getSurName(), ownerRequest.getFamilyName());
    }

    @GetMapping("/{name}")
    public List<CardEntities> getCardsByName(@PathVariable String name) {
        return cardService.getCardsByName(name);
    }

    @PostMapping("/card/image")
    public ResponseEntity<String> getCardImageByName(@RequestBody Map<String, String> body) {
        String cardName = body.get("name");
        System.out.println("Получено имя карты: " + cardName);
        try {
            String imageUrl = cardService.getCardImageByName(cardName);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка: " + e.getMessage());
        }
    }
}