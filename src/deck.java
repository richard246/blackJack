import java.util.ArrayList;

public class deck {
    private int value=0;
    private  ArrayList<Card> deckContainer = new ArrayList<>();
    public deck(){

    }


    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }
    public void addToDeck(Card newCard) {
        this.deckContainer.add(newCard);
        if(newCard.getClass().getName() != "ace") {
            this.value += newCard.getValue();
        }
        else{
            if((this.value+10)>21){
                this.value+= ((ace)newCard).getOptionalValue();
            }
            else{
                this.value += newCard.getValue();
            }
        }

    }
    public ArrayList<Card> getDeckContainer() {
        return deckContainer;
    }





}


