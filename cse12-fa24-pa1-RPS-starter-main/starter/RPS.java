import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";
    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        while(true){
            System.out.println(PROMPT_MOVE);
            String playerMove = in.nextLine();

            if (playerMove.equals(QUIT)){
                game.displayStats();
                break;
            }

            String cpuMove = game.genCPUMove(); // generates a cpuMove

            if(!game.isValidMove(playerMove)){
                System.out.println(INVALID_INPUT);
                continue;
            }
            System.out.printf(CPU_MOVE, cpuMove);

            game.playRPS(playerMove, cpuMove); // plays game
        }
        in.close();
    }

    @Override
    public int determineWinner(String playerMove, String cpuMove) {
        // determine who wins in rock, paper, scissors
        int playerNum = -1;
        int cpuNum = -1;
        // sets the player and cpu numbers to -1 as default

        for(int i=0; i<possibleMoves.length; i++){
            if(possibleMoves[i].equals(playerMove)){
                playerNum = i;
            }
            if(possibleMoves[i].equals(cpuMove)){
                cpuNum = i;
            }
        } // sets the player and cpu moves to a index of the possibleMoves array
        int lastElement = possibleMoves.length - 1;
        if(playerNum == -1 || cpuNum == -1){
            return INVALID_INPUT_OUTCOME;
        } // checks for valid outcomes.
        // -1 is not a index in the array, therefore it is a invalid outcome
        else if(cpuNum > playerNum){
            if(playerNum == 0 && cpuNum == lastElement){
                return PLAYER_WIN_OUTCOME;
            }
            else if (cpuNum - playerNum > 1){
                return TIE_OUTCOME;
            }
            else{
                return CPU_WIN_OUTCOME;
            }
        }
        else if(playerNum > cpuNum){
            if (playerNum == lastElement && cpuNum == 0){
                return CPU_WIN_OUTCOME;
            }
            else if(playerNum - cpuNum > 1){
                return TIE_OUTCOME;
            }
            else{
                return PLAYER_WIN_OUTCOME;
            }
        }
        else{
            return TIE_OUTCOME; // all else is a tie
        }   
    }
}