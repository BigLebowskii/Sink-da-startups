import java.util.ArrayList;

public class StartUpBust{


    private GameHelper helper = new GameHelper();
    private ArrayList<StartUp> startups = new ArrayList<StartUp>();
    private int numGuesses = 0;

    private void setUpGame(){
        // first make a few start ups and give them locations
        StartUp one = new StartUp();
        one.setName("Elgato");
        StartUp two = new  StartUp();
        two.setName("OpenAI");
        StartUp three = new StartUp();
        three.setName("Cabista");
        startups.add(one);
        startups.add(two);
        startups.add(three);

        System.out.println("Your goal is to sink three Startups.");
        System.out.println("Elgato, OpenAI, Cabista");
        System.out.println("Try to sink them all in the fewest number of guesses.");

        for (StartUp startUp : startups){
            ArrayList<String> newLocation = helper.placeStartUp(3);
            startUp.setLocationCells(newLocation);
        }

    }

    private void startPlaying(){
        while (!startups.isEmpty()){
            String userGuess = helper.getUserInput("Enter a guess: ");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess){
        numGuesses++;
        String result = "miss";

        for (StartUp startUpToTest : startups){
            result = startUpToTest.checkYourself(userGuess);

            if (result.equals("hit")){
                break;
            }
            if (result.equals("kill")){
                startups.remove(startUpToTest);
                break;
            }
        }
    System.out.println(result);
    }

    private void finishGame(){
        System.out.println("All Startups are dead! Your stock is now worthless.");
        if(numGuesses <= 18){
            System.out.println("It only took you " + numGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        }
        else{
            System.out.println("Took you long enough. " + numGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }


    public static void main(String[] args){
        StartUpBust game = new StartUpBust();
        game.setUpGame();
        game.startPlaying();
    }
    
}