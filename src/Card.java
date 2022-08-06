import javafx.scene.image.Image;

public class Card {
    private int value;
    private String suit;
    public Card(){}
    public Card(int value, String suit){
        this.value = value;
        this.suit = suit;

    }

    public int getValue() {
        return value;
    }
    public String getSuit(){
        return suit;
    }
}
class ace extends Card{
    private int optionalValue;

    public ace(int value,String suit){
        super(value,suit);
        optionalValue = 1;


    }

    public int getOptionalValue() {
        return optionalValue;
    }
}

