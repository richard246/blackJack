import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Collections;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    public ImageView playerCard1;
    @FXML
    public ImageView playerCard2;
    @FXML
    public ImageView playerCard3;
    @FXML
    public ImageView playerCard4;
    @FXML
    public ImageView playerCard5;
    @FXML
    public ImageView dealerCard1;
    @FXML
    public ImageView dealerCard2;
    @FXML
    public ImageView dealerCard3;
    @FXML
    public ImageView dealerCard4;
    @FXML
    public ImageView dealerCard5;

    @FXML
    public deck bj = new deck();
    @FXML
    public hand playerHand = new hand();
    @FXML
    public hand dealerHand = new hand();
    @FXML
    Label playerPoints;

    @FXML
    Label dealerPoints;
    @FXML
    AnchorPane gameAnchor;
    @FXML
    AnchorPane gameAnchorBase;
    @FXML
    public Label winText;
    @FXML
    public Label lostText;
    @FXML
    public Rectangle gameOverbox;
    @FXML
    public Button stayButton;
    @FXML
    public Button hitButton;
    int i = 0;
    public boolean playerLose = false;
    public boolean dealerLose = false;
    int acetrackerP = 0;
    int acetrackerD = 0;
    public Effect BoxBlur = new BoxBlur();
    public Bloom Bloom = new Bloom();
    private Object Lighting = new Lighting();
    public DropShadow ds = new DropShadow();

    public PerspectiveTransform pt = new PerspectiveTransform();
    public TranslateTransition translate = new TranslateTransition();
    public PauseTransition p = new PauseTransition();
    public int image3 = 0;
    public int image4 = 0;
    public int image5 = 0;

    public void Hit(ActionEvent e) throws IOException {
        stayButton.setDisable(false);

        if (i != 5 && !playerLose) {
            ImageView[] deltCardPositions = {playerCard1, playerCard2, playerCard3, playerCard4, playerCard5};
            if (bj.getDeckContainer().get(0).getClass().getName() == "ace") {
                deltCardPositions[i].setImage(new Image("./images/ace_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
                acetrackerP++;
            } else {
                deltCardPositions[i].setImage(new Image("./images/" + bj.getDeckContainer().get(0).getValue() + "_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
            }
            playerHand.addToDeck(bj.getDeckContainer().get(0));
            bj.getDeckContainer().remove(0);
            if (playerHand.getValue() > 21 && acetrackerP > 0) {
                playerHand.setValue(playerHand.getValue() - 9);
                acetrackerP--;
            }
            i++;
            playerPoints.setText("Player: " + playerHand.getValue());
        }
        if (playerHand.getValue() > 21) {
            playerLose = true;
            int i = 0;
            lossScreen();

        }
    }

    public void Stay(ActionEvent event) throws InterruptedException, IOException {


        i = 0;
        if (bj.getDeckContainer().get(0).getClass().getName() == "ace") {
            dealerCard2.setImage(new Image("./images/ace_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
            acetrackerD++;
        } else {
            dealerCard2.setImage(new Image("./images/" + bj.getDeckContainer().get(0).getValue() + "_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
        }
        dealerHand.addToDeck(bj.getDeckContainer().get(0));
        dealerPoints.setText("Dealer: " + dealerHand.getValue());

        if (dealerHand.getValue() > 21 && acetrackerD > 0) {
            dealerHand.setValue(dealerHand.getValue() - 9);
            acetrackerD--;
        }
        if (dealerHand.getValue() > 21) {
            dealerLose = true;
            winScreen();
        }

        while ((!dealerLose) && dealerHand.getValue() < 17) {


            if (i < 3) {
                ImageView[] deltCardPositions = {dealerCard3, dealerCard4, dealerCard5};
                if (bj.getDeckContainer().get(0).getClass().getName() == "ace") {
                    deltCardPositions[i].setImage(new Image("./images/ace_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
                    acetrackerD++;
                } else {
                    deltCardPositions[i].setImage(new Image("./images/" + bj.getDeckContainer().get(0).getValue() + "_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
                }

                dealerHand.addToDeck(bj.getDeckContainer().get(0));
                bj.getDeckContainer().remove(0);
                if (dealerHand.getValue() > 21 && acetrackerD > 0) {
                    dealerHand.setValue(dealerHand.getValue() - 9);
                    acetrackerD--;
                }
                dealerPoints.setText("Dealer: " + dealerHand.getValue());
                i++;
            }
            if (dealerHand.getValue() > 21) {
                dealerLose = true;
                int i = 0;
                winScreen();
            }
        }
        if (!dealerLose) {
            if (dealerHand.getValue() < playerHand.getValue()) {
                dealerLose = true;
                int i = 0;
                winScreen();
            } else if (dealerHand.getValue() > playerHand.getValue()) {
                playerLose = true;
                int i = 0;
                lossScreen();

            }
        }


    }

    public void Reset(ActionEvent e) {
        clear();
        acetrackerP = 0;
        acetrackerD = 0;
        playerHand.getDeckContainer().clear();
        dealerHand.getDeckContainer().clear();
        dealerPoints.setText("Dealer: ");
        playerPoints.setText("Player: ");
        playerHand.setValue(0);
        dealerHand.setValue(0);
        i = 0;
        playerCard1.setImage(null);
        playerCard2.setImage(null);
        playerCard3.setImage(null);
        playerCard4.setImage(null);
        playerCard5.setImage(null);
        dealerCard1.setImage(null);
        dealerCard2.setImage(new Image("./images/Back.png"));
        dealerCard3.setImage(null);
        dealerCard4.setImage(null);
        dealerCard5.setImage(null);
        playerLose = false;
        dealerLose = false;
        gameAnchor.setEffect(null);
        buildDeck(bj);
        Collections.shuffle(bj.getDeckContainer());
        if (bj.getDeckContainer().get(0).getClass().getName() == "ace") {
            dealerCard1.setImage(new Image("./images/ace_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
            acetrackerD++;
        } else {
            dealerCard1.setImage(new Image("./images/" + bj.getDeckContainer().get(0).getValue() + "_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
        }
        dealerHand.addToDeck(bj.getDeckContainer().get(0));
        bj.getDeckContainer().remove(0);
        dealerPoints.setText("Dealer: " + dealerHand.getValue());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildDeck(bj);
        Collections.shuffle(bj.getDeckContainer());
        if (bj.getDeckContainer().get(0).getClass().getName() == "ace") {
            dealerCard1.setImage(new Image("./images/ace_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
            acetrackerD++;
        } else {
            dealerCard1.setImage(new Image("./images/" + bj.getDeckContainer().get(0).getValue() + "_of_" + bj.getDeckContainer().get(0).getSuit() + ".png"));
        }
        dealerHand.addToDeck(bj.getDeckContainer().get(0));
        bj.getDeckContainer().remove(0);
        dealerPoints.setText("Dealer: " + dealerHand.getValue());
        stayButton.setDisable(true);


    }

    public static void buildDeck(deck deckofcards) {
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] cardType = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for (String suit : suits) {
            for (String faceOfCard : cardType) {
                if (!faceOfCard.equals("jack") && !faceOfCard.equals("queen") && !faceOfCard.equals("king") && !faceOfCard.equals("ace")) {
                    int value = Integer.parseInt(faceOfCard);
                    deckofcards.addToDeck(new Card(value, suit));

                } else if (faceOfCard.equals("jack") || faceOfCard.equals("queen") || faceOfCard.equals("king")) {
                    deckofcards.addToDeck(new Card(10, suit));
                } else {
                    deckofcards.addToDeck(new ace(10, suit));
                }
            }
        }
    }

    public void winScreen() {

        //gameAnchorBase.setEffect(BoxBlur);
        Bloom.setThreshold(.1);
        winText.setEffect(Bloom);
        gameOverbox.setFill(Color.DARKGREEN);
        gameOverbox.setEffect((Effect) Lighting);
        stayButton.setDisable(true);
        hitButton.setDisable(true);
    }

    public void lossScreen() {
        //gameAnchorBase.setEffect(BoxBlur);
        Bloom.setThreshold(.1);
        lostText.setEffect(Bloom);
        gameOverbox.setFill(Color.DARKGREEN);
        gameOverbox.setEffect((Effect) Lighting);
        stayButton.setDisable(true);
        hitButton.setDisable(true);
    }

    public void clear() {
        lostText.setEffect(pt);
        winText.setEffect(pt);
        gameOverbox.setEffect(pt);
        gameAnchorBase.setEffect(null);
        stayButton.setDisable(false);
        hitButton.setDisable(false);
    }
}










