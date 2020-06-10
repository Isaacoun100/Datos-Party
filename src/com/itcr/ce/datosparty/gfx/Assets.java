package com.itcr.ce.datosparty.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage blueBox;
    public static BufferedImage greenBox;
    public static BufferedImage yellowBox;
    public static BufferedImage redBox;
    public static BufferedImage player1Static;
    public static BufferedImage player2Static;
    public static BufferedImage player3Static;
    public static BufferedImage player4Static;
    public static BufferedImage starSeller;
    public static BufferedImage noCoinsMsg;
    public static BufferedImage enoughCoins;
    public static BufferedImage mapGuide;
    public static BufferedImage eventBackDrop;
    public static BufferedImage eventBackDropBad;
    public static BufferedImage getEventBackDropSpecial;
    public static BufferedImage secondPLaceImg;
    public static BufferedImage thirdPlaceImg;
    public static BufferedImage endGamePodium2;
    public static BufferedImage endGamePodium3;
    public static BufferedImage playerSelectionTitle;

    public static BufferedImage[] settingsButton;
    public static BufferedImage[] playButton;
    public static BufferedImage[] creditsButton;
    public static BufferedImage[] backButton;
    public static BufferedImage[] titleImage;
    public static BufferedImage[] pressEnterImage;
    public static BufferedImage[] notOKComputer;
    public static BufferedImage[] notOKComputerIcon;
    public static BufferedImage[] nothingHereMessage;
    public static BufferedImage[] boton1;
    public static BufferedImage[] diceButton;
    public static BufferedImage[] upArrow;
    public static BufferedImage[] downArrow;
    public static BufferedImage[] leftArrow;
    public static BufferedImage[] rightArrow;
    public static BufferedImage[] player1Button;
    public static BufferedImage[] player2Button;
    public static BufferedImage[] duel;
    public static BufferedImage[] stealCoins;
    public static BufferedImage[] giveCoins;
    public static BufferedImage[] loseStar;
    public static BufferedImage[] win2Stars;
    public static BufferedImage[] win5Stars;
    public static BufferedImage[] stealStar;
    public static BufferedImage[] teleport;
    public static BufferedImage[] swapPlace;
    public static BufferedImage[] player3Button;
    public static BufferedImage[] player4Button;
    public static BufferedImage[] creditsButtonMenu;
    public static BufferedImage[] volumeUp;
    public static BufferedImage[] volumeDown;
    public static BufferedImage[] gameMusic;
    public static BufferedImage[] soundEffects;
    public static BufferedImage[] starPurchaseBackDrop;
    public static BufferedImage[] yesBtn;
    public static BufferedImage[] noBtn;
    public static BufferedImage[] purchaseMsg;
    public static BufferedImage[] buyMsg;
    public static BufferedImage[] endTurnBtn;
    public static BufferedImage[] coin;
    public static BufferedImage[] star;
    public static BufferedImage[] noviceButton;
    public static BufferedImage[] proButton;
    public static BufferedImage[] eliteButton;
    public static BufferedImage[] roundTitle;
    public static BufferedImage[] okBtn;
    public static BufferedImage[] stealCoins1;
    public static BufferedImage[] stealCoins2;
    public static BufferedImage[] stealCoins3;
    public static BufferedImage[] stealCoins4;
    public static BufferedImage[] twoPlayerAnim;
    public static BufferedImage[] threePlayerAnim;
    public static BufferedImage[] fourPlayerAnim;
    public static BufferedImage[] firstPlaceAnim;
    public static BufferedImage[] lastPlaceAnim;
    public static BufferedImage[] congratulationsTitle;

    public static Font bitArtFont;
    public static Font bitArtFont2;
    public static Font bitArtFont3;


    /**
     * This Method initializes all assets that are used in the game, for them to load properly
     * it uses the crop method to initialize sections of an image as separate objects
     */
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet-Sheet.png"));
        SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ButtonSprite-Sheet.png"));
        SpriteSheet mapSheet = new SpriteSheet(ImageLoader.loadImage("/textures/MapLayout.png"));

        titleImage = new BufferedImage[2];
        titleImage[0] = sheet.crop(0,0,20,3);
        titleImage[1] = sheet.crop(20,0,20,3);

        pressEnterImage = new BufferedImage[2];
        pressEnterImage[0] = sheet.crop(0,3,7,1);
        pressEnterImage[1] = sheet.crop(0,3,7,1);

        playButton = new BufferedImage[2];
        playButton[0] = sheet.crop(7,3,3,1);
        playButton[1] = sheet.crop(27,3,3,1);

        settingsButton = new BufferedImage[2];
        settingsButton[0] = sheet.crop(10,3,5,1);
        settingsButton[1] = sheet.crop(30,3,5,1);

        creditsButtonMenu = new BufferedImage[2];
        creditsButtonMenu[0] = sheet.crop(15,3,5,1);
        creditsButtonMenu[1] = sheet.crop(35,3,5,1);

        backButton = new BufferedImage[2];
        backButton[0] = sheet.crop(0,4,3,1);
        backButton[1] = sheet.crop(20,4,3,1);

        notOKComputer = new BufferedImage[2];
        notOKComputer[0] = sheet.crop(5,5,9,1);
        notOKComputer[1] = sheet.crop(5,5,9,1);

        notOKComputerIcon = new BufferedImage[2];
        notOKComputerIcon[0] = sheet.crop(3,4,2,2);
        notOKComputerIcon[1] = sheet.crop(23,4,2,2);

        nothingHereMessage = new BufferedImage[2];
        nothingHereMessage[0] = sheet.crop(5,4,9,1);
        nothingHereMessage[1] = sheet.crop(5,4,9,1);

        diceButton = new BufferedImage[2];
        diceButton[0] = buttonSheet.crop(0,0,4,4);
        diceButton[1] = buttonSheet.crop(19,0,4,4);

        upArrow = new BufferedImage[2];
        upArrow[0] = sheet.crop(14,4,1,1);
        upArrow[1] = sheet.crop(15,4,1,1);

        downArrow = new BufferedImage[2];
        downArrow[0] = sheet.crop(16,4,1,1);
        downArrow[1] = sheet.crop(17,4,1,1);

        rightArrow = new BufferedImage[2];
        rightArrow[0] = sheet.crop(14,5,1,1);
        rightArrow[1] = sheet.crop(15,5,1,1);

        leftArrow = new BufferedImage[2];
        leftArrow[0] = sheet.crop(16,5,1,1);
        leftArrow[1] = sheet.crop(17,5,1,1);


        creditsButton= new BufferedImage[2];
        creditsButton[0] = buttonSheet.crop(0,17,7,2);
        creditsButton[1] = buttonSheet.crop(19,17,7,2);

        player4Button= new BufferedImage[2];
        player4Button[0] = buttonSheet.crop(0,14,7,2);
        player4Button[1] = buttonSheet.crop(19,14,7,2);

        player3Button= new BufferedImage[2];
        player3Button[0] = buttonSheet.crop(0,11,7,2);
        player3Button[1] = buttonSheet.crop(19,11,7,2);

        player2Button= new BufferedImage[2];
        player2Button[0] = buttonSheet.crop(0,8,7,2);
        player2Button[1] = buttonSheet.crop(19,8,7,2);

        player1Button= new BufferedImage[2];
        player1Button[0] = buttonSheet.crop(0,5,7,2);
        player1Button[1] = buttonSheet.crop(19,5,7,2);

        volumeUp = new BufferedImage[2];
        volumeUp[0] = buttonSheet.crop(5,0,3,3);
        volumeUp[1] = buttonSheet.crop(24,0,3,3);

        volumeDown = new BufferedImage[2];
        volumeDown[0] = buttonSheet.crop(9,0,3,3);
        volumeDown[1] = buttonSheet.crop(28,0,3,3);

        noviceButton = new BufferedImage[2];
        noviceButton[0] = buttonSheet.crop(7,17,7,2);
        noviceButton[1] = buttonSheet.crop(26,17,7,2);

        proButton = new BufferedImage[2];
        proButton[0] = buttonSheet.crop(7,19,7,2);
        proButton[1] = buttonSheet.crop(26,19,7,2);

        eliteButton = new BufferedImage[2];
        eliteButton[0] = buttonSheet.crop(0,19,7,2);
        eliteButton[1] = buttonSheet.crop(19,19,7,2);

        roundTitle = new BufferedImage[1];
        roundTitle[0]= buttonSheet.crop(7,10,12,6);

        boton1 = new BufferedImage[2];
        boton1[0] = sheet.crop(14,4,5,1);
        boton1[1] = sheet.crop(34,4,5,1);

        blueBox = sheet.crop(3,6,1,1);
        greenBox = sheet.crop(3,7,1,1);
        yellowBox = sheet.crop(4,6,1,1);
        redBox = sheet.crop(4,7,1,1);

        player1Static = sheet.crop(0,6,1,2);
        player2Static = sheet.crop(7,6,1,2);
        player3Static = sheet.crop(10,6,1,2);
        player4Static = sheet.crop(13,6,1,2);
        starSeller = sheet.crop(16,6,2,2);

        gameMusic= new BufferedImage[1];
        gameMusic[0]= buttonSheet.crop(8,5,8,2);

        soundEffects= new BufferedImage[1];
        soundEffects[0] = buttonSheet.crop(7,8,9,2);

        starPurchaseBackDrop = new BufferedImage[2];
        starPurchaseBackDrop[0] = sheet.crop(18,5,4,2);
        starPurchaseBackDrop[1] = sheet.crop(18,5,4,2);

        yesBtn = new BufferedImage[2];
        yesBtn[0] = sheet.crop(18,7,1,1);
        yesBtn[1] = sheet.crop(18,4,1,1);

        noBtn = new BufferedImage[2];
        noBtn[0] = sheet.crop(19,7,1,1);
        noBtn[1] = sheet.crop(19,4,1,1);

        purchaseMsg = new BufferedImage[2];
        purchaseMsg[0] = sheet.crop(20,7,3,1);
        purchaseMsg[1] = sheet.crop(20,7,3,1);

        noCoinsMsg = sheet.crop(20,8,7,2);
        enoughCoins = sheet.crop(20,7,4,1);

        buyMsg = new BufferedImage[2];
        buyMsg[0] = sheet.crop(25,5,4,1);
        buyMsg[1] = sheet.crop(25,5,4,1);

        endTurnBtn = new BufferedImage[2];
        endTurnBtn[1] = sheet.crop(29,4,2,2);
        endTurnBtn[0] = sheet.crop(31,4,2,2);

        okBtn = new BufferedImage[2];
        okBtn[0] = sheet.crop(19,8,1,1);
        okBtn[1] = sheet.crop(18,8,1,1);

        mapGuide = mapSheet.crop(0,0,100,76);
        eventBackDrop = sheet.crop(0,8,2,3);
        eventBackDropBad = sheet.crop(0,11,2,3);
        getEventBackDropSpecial = sheet.crop(6,11,2,3);


        coin = new BufferedImage[7];
        coin[0] = sheet.crop(27,7,1,1);
        coin[1] = sheet.crop(28,7,1,1);
        coin[2] = sheet.crop(29,7,1,1);
        coin[3] = sheet.crop(30,7,1,1);
        coin[4] = sheet.crop(31,7,1,1);
        coin[5] = sheet.crop(32,7,1,1);
        coin[6] = sheet.crop(27,7,1,1);

        star = new BufferedImage[10];
        star[0] = sheet.crop(29,6,1,1);
        star[1] = sheet.crop(30,6,1,1);
        star[2] = sheet.crop(31,6,1,1);
        star[3] = sheet.crop(32,6,1,1);
        star[4] = sheet.crop(33,6,1,1);
        star[5] = sheet.crop(34,6,1,1);
        star[6] = sheet.crop(35,6,1,1);
        star[7] = sheet.crop(36,6,1,1);
        star[8] = sheet.crop(37,6,1,1);
        star[9] = sheet.crop(29,6,1,1);

        duel = new BufferedImage[8];
        duel[0] = sheet.crop(2,8,2,1);
        duel[1] = sheet.crop(4,8,2,1);
        duel[2] = sheet.crop(6,8,2,1);
        duel[3] = sheet.crop(8,8,2,1);
        duel[4] = sheet.crop(10,8,2,1);
        duel[5] = sheet.crop(12,8,2,1);
        duel[6] = sheet.crop(14,8,2,1);
        duel[7] = sheet.crop(16,8,2,1);

        stealCoins = new BufferedImage[7];

        stealCoins[0] = sheet.crop(2,9,1,1);
        stealCoins[1] = sheet.crop(3,9,1,1);
        stealCoins[2] = sheet.crop(4,9,1,1);
        stealCoins[3] = sheet.crop(5,9,1,1);
        stealCoins[4] = sheet.crop(6,9,1,1);
        stealCoins[5] = sheet.crop(7,9,1,1);
        stealCoins[6] = sheet.crop(2,9,1,1);

        giveCoins = new BufferedImage[7];

        giveCoins[0] = sheet.crop(2,10,1,1);
        giveCoins[1] = sheet.crop(3,10,1,1);
        giveCoins[2] = sheet.crop(4,10,1,1);
        giveCoins[3] = sheet.crop(5,10,1,1);
        giveCoins[4] = sheet.crop(6,10,1,1);
        giveCoins[5] = sheet.crop(7,10,1,1);
        giveCoins[6] = sheet.crop(2,9,1,1);

        loseStar = new BufferedImage[10];
        loseStar[0] = sheet.crop(8,10,1,1);
        loseStar[1] = sheet.crop(9,10,1,1);
        loseStar[2] = sheet.crop(10,10,1,1);
        loseStar[3] = sheet.crop(11,10,1,1);
        loseStar[4] = sheet.crop(12,10,1,1);
        loseStar[5] = sheet.crop(13,10,1,1);
        loseStar[6] = sheet.crop(14,10,1,1);
        loseStar[7] = sheet.crop(15,10,1,1);
        loseStar[8] = sheet.crop(16,10,1,1);
        loseStar[9] = sheet.crop(8,10,1,1);

        win2Stars = new BufferedImage[11];
        win2Stars[0] = sheet.crop(8,11,1,1);
        win2Stars[1] = sheet.crop(9,11,1,1);
        win2Stars[2] = sheet.crop(10,11,1,1);
        win2Stars[3] = sheet.crop(11,11,1,1);
        win2Stars[4] = sheet.crop(12,11,1,1);
        win2Stars[5] = sheet.crop(13,11,1,1);
        win2Stars[6] = sheet.crop(14,11,1,1);
        win2Stars[7] = sheet.crop(15,11,1,1);
        win2Stars[8] = sheet.crop(16,11,1,1);
        win2Stars[9] = sheet.crop(8,11,1,1);

        win5Stars = new BufferedImage[11];
        win5Stars[0] = sheet.crop(8,12,1,1);
        win5Stars[1] = sheet.crop(9,12,1,1);
        win5Stars[2] = sheet.crop(10,12,1,1);
        win5Stars[3] = sheet.crop(11,12,1,1);
        win5Stars[4] = sheet.crop(12,12,1,1);
        win5Stars[5] = sheet.crop(13,12,1,1);
        win5Stars[6] = sheet.crop(14,12,1,1);
        win5Stars[7] = sheet.crop(15,12,1,1);
        win5Stars[8] = sheet.crop(16,12,1,1);
        win5Stars[9] = sheet.crop(8,12,1,1);

        stealStar = new BufferedImage[10];
        stealStar[0] = sheet.crop(8,9,1,1);
        stealStar[1] = sheet.crop(9,9,1,1);
        stealStar[2] = sheet.crop(10,9,1,1);
        stealStar[3] = sheet.crop(11,9,1,1);
        stealStar[4] = sheet.crop(12,9,1,1);
        stealStar[5] = sheet.crop(13,9,1,1);
        stealStar[6] = sheet.crop(14,9,1,1);
        stealStar[7] = sheet.crop(15,9,1,1);
        stealStar[8] = sheet.crop(16,9,1,1);
        stealStar[9] = sheet.crop(8,9,1,1);

        teleport = new BufferedImage[12];

        teleport[0] = sheet.crop(17,12,1,2);
        teleport[1] = sheet.crop(18,12,1,2);
        teleport[2] = sheet.crop(19,12,1,2);
        teleport[3] = sheet.crop(20,12,1,2);
        teleport[4] = sheet.crop(21,12,1,2);
        teleport[5] = sheet.crop(22,12,1,2);
        teleport[6] = sheet.crop(23,12,1,2);
        teleport[7] = sheet.crop(24,12,1,2);
        teleport[8] = sheet.crop(25,12,1,2);
        teleport[9] = sheet.crop(26,12,1,2);
        teleport[10] = sheet.crop(27,12,1,2);
        teleport[11] = sheet.crop(28,12,1,2);

        swapPlace = new BufferedImage[6];

        swapPlace[0] = sheet.crop(17,10,2,2);
        swapPlace[1] = sheet.crop(19,10,2,2);
        swapPlace[2] = sheet.crop(21,10,2,2);
        swapPlace[3] = sheet.crop(23,10,2,2);
        swapPlace[4] = sheet.crop(25,10,2,2);
        swapPlace[5] = sheet.crop(27,10,2,2);

        stealCoins1 = new BufferedImage[2];
        stealCoins1[0] = sheet.crop(2,11,1,2);
        stealCoins1[1] = sheet.crop(4,11,1,2);

        stealCoins2 = new BufferedImage[2];
        stealCoins2[0] = sheet.crop(3,11,1,2);
        stealCoins2[1] = sheet.crop(5,11,1,2);

        stealCoins3 = new BufferedImage[2];
        stealCoins3[0] = sheet.crop(2,13,1,2);
        stealCoins3[1] = sheet.crop(4,13,1,2);

        stealCoins4 = new BufferedImage[2];
        stealCoins4[0] = sheet.crop(3,13,1,2);
        stealCoins4[1] = sheet.crop(5,13,1,2);

        firstPlaceAnim = new BufferedImage[3];
        firstPlaceAnim[0] = sheet.crop(29,8,2,2);
        firstPlaceAnim[1] = sheet.crop(31,8,2,2);
        firstPlaceAnim[2] = sheet.crop(33,8,2,2);

        lastPlaceAnim = new BufferedImage[3];
        lastPlaceAnim[0] = sheet.crop(29,10,2,2);
        lastPlaceAnim[1] = sheet.crop(31,10,2,2);
        lastPlaceAnim[2] = sheet.crop(33,10,2,2);

        secondPLaceImg = sheet.crop(35,8,1,2);
        thirdPlaceImg = sheet.crop(36,8,1,2);

        twoPlayerAnim = new BufferedImage[2];
        twoPlayerAnim[0] = sheet.crop(36,10,2,2);
        twoPlayerAnim[1] = sheet.crop(38,10,2,2);

        threePlayerAnim = new BufferedImage[3];
        threePlayerAnim[0] = sheet.crop(31,12,3,2);
        threePlayerAnim[1] = sheet.crop(34,12,3,2);
        threePlayerAnim[2] = sheet.crop(37,12,3,2);

        fourPlayerAnim = new BufferedImage[4];
        fourPlayerAnim[0] = sheet.crop(24,14,4,2);
        fourPlayerAnim[1] = sheet.crop(28,14,4,2);
        fourPlayerAnim[2] = sheet.crop(32,14,4,2);
        fourPlayerAnim[3] = sheet.crop(36,14,4,2);

        endGamePodium2 = sheet.crop(34,16,4,3);
        endGamePodium3 = sheet.crop(34,16,6,3);

        congratulationsTitle = new BufferedImage[4];
        congratulationsTitle[0] = sheet.crop(0,17,17,2);
        congratulationsTitle[1] = sheet.crop(0,19,17,2);
        congratulationsTitle[2] = sheet.crop(0,21,17,2);
        congratulationsTitle[3] = sheet.crop(0,19,17,2);


        playerSelectionTitle = sheet.crop(6,13,10,2);

        bitArtFont = FontLoader.loadFont("res/fonts/windows_command_prompt.ttf");
        bitArtFont2 = FontLoader.loadFont("res/fonts/retro_computer_personal_use.ttf");
        bitArtFont3 = FontLoader.loadFont("res/fonts/3Dventure.ttf");

    }
}
