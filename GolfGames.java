/*
 *Golf Games
 *
 *This program analyzes golf scores from different players.
 *Heather Truong
 *3/28/23
 *CMSC 255 Section C90
 */
package Project05;


public class GolfGames {

    //Define and initialize getNames() method
    //Takes in a string of names and returns a one dimensional String array containing the list of names
    public static String[] getNames(String inputNameString) {
        String[] listOfNames = inputNameString.split(",");
        return listOfNames;
    }

    //Define and initialize getScores() method
    //Takes in a String of players' scores and returns a two-dimensional int array containing the scores for each of the players for each of the nine golf holes
    public static int[][] getScores(String inputScoresString) {
        //Split string into rows by "<>"
        String[] rows = inputScoresString.split("<>");

        //Create a 2D array
        String[][] scores = new String[rows.length][];
        for(int i = 0; i < rows.length; i++) {
            //Split each row into columns by ","
            String[] columns = rows[i].split(",");
            //Set the current row of the scores double array to the columns array
            scores[i] = columns;
        }
        //Convert the String double array to an int double array
        int[][] intScores = new int[rows.length][];
        for(int i = 0; i < rows.length; i++) {
            int[] row = new int[scores[i].length];
            for(int j = 0; j < scores[i].length; j++) {
                row[j] = Integer.parseInt(scores[i][j]);
            }
            intScores[i] = row;
        }
        return intScores;
    }

    //Define and initialize findWinner() method
    //Takes in a String array of player names and a 2D int array of player scores. The method will find and return the player associated with lowest score
    public static String findWinner(String[] names, int[][] scores) {
        //Create a new int array named total with the same length as String array names
        int[] totals = new int[names.length];
        //Go through each player's scores down the column and add them up then store each score into the total array
        for (int j = 0; j < scores[0].length; j++) { 
            for (int i = 0; i < scores.length; i++) {
                totals[j] += scores[i][j];
            }
        }

        //Find the index of the lowest total
        int winnerIndex = 0;
        for (int i = 0; i < totals.length; i++) {
            if(totals[i] < totals[winnerIndex]) {
                winnerIndex = i;
            }
        }
        return names[winnerIndex];
    }

    //Define and intialize findAvgForHole() method
    //Takes in a 2D int array of player scores and an int hole number. It will search the scores for that hole and will return a double value representing the average score on that hole.
    public static double findAvgForHole(int[][] scores, int holeNum) {
        int sum = 0;
        int count = 0;

        //Go through each player's score for the hole number and add the sum
        for (int j = 0; j < scores[0].length; j++) {
            if (holeNum >= 0 && holeNum < scores.length) {
                sum += scores[holeNum - 1][j];
                count++;
            }
        }

        //Calculate the average score for the hole num
        if (count > 0) {
            return (double) sum / count;
        } else {
            return 0.0;
        }
    }

    //Define and initialize searchPlayersBelowAvg()
    //Takes in a String array of player names, a 2D int array of player scores, an int hole number, and a double average for a given hole. It will search for the players who scored below the average on the given hole. The names of all players under the hole average will be returned in a String array
    public static String[] searchPlayersBelowAvg(String[] names, int[][] scores, int holeNum, double avgForHole) {
        //Create an Array to hold the names of players below the average
        String[] belowAveragePlayers = new String[names.length];
        int count = 0;

        //Go through each player's score for the hole number and add their name to the belowAveragePlayers array if their score is below the average
        for (int j = 0; j < names.length; j++) {
            if (holeNum >= 0 && holeNum <= scores.length && scores[holeNum - 1][j] < avgForHole) {
                belowAveragePlayers[count] = names[j];
                count++;
            }
        }
        //Create a new String array with the length of count and copy in values of belowAveragePlayers
        String[] resizeBelowAveragePlayers = new String[count];
        for (int i = 0; i < count; i++) {
            resizeBelowAveragePlayers[i] = belowAveragePlayers[i];
        }
        return resizeBelowAveragePlayers;
    }

    //Main method
    public static void main(String[] args) {
        //Pass the first command line argument to the getNames() method and store the result
        String[] playerNames = getNames(args[0]);
        for(String names: playerNames) {
        }

        //Pass the second command line argument to the getScores() method and store the result
        int[][] scoresArray = getScores(args[1]);
        for(int[] scores: scoresArray) {
        }

        //Call findWinner(), passing in the result of getScores() and getNames() and print the result
        String winner = findWinner(playerNames, scoresArray);
        System.out.println("The winner is: " + winner);

        //Call findAvgForHole() passing in the results of getScores() and print the result
        double average = findAvgForHole(scoresArray, 6);
        System.out.println("The average for Hole 6 is: " + average);

        //Call searchPlayersBelowAvg() passing in the results of getScores() and getNames() and print the result
        String[] playersBelowAvg = searchPlayersBelowAvg(playerNames, scoresArray, 6, 6.4);
            System.out.println("The players below the average for Hole 6 are: " + playersBelowAvg[0] + " " + playersBelowAvg[1]);
    }
}
