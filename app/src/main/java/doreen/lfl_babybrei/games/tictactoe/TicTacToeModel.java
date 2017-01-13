package doreen.lfl_babybrei.games.tictactoe;

/**
 * Logik des TicTacToe-Spieles.
 * Created by Doreen on 24.11.2016.
 */
import java.util.Random;

/**
 * The type Tic tac toe model.
 */
public class TicTacToeModel {
     /**
      * Spielbrett
      */
     private char Spielbrett[ ];
     /**
      * Spielbrettgröße
      */
     private static final int spielbrettGroesse = 9;

     /**
      * menschlicher Benutzer
      */
     public static final char user = 'X';
     /**
      * Computer
      */
     public static final char computer = 'O';
     /**
      * leeres Feld
      */
     public static final char leer = ' ';
     /**
      * Zufallszahl
      */
     private Random zufall;

     /**
      * Auslesen der Spielbrettgröße.
      *
      * @return Spielbrettgröße
      */
     public static int getspielbrettGroesse() {
                 return spielbrettGroesse;
            }

     /**
      * Logik wird initialisiert
      */
     TicTacToeModel() {

            Spielbrett = new char[spielbrettGroesse];
             for (int i = 0; i < spielbrettGroesse; i++) {
                 Spielbrett[i] = leer;
             }
                 zufall = new Random();
              }

     /**
      * Spielbrett zurücksetzen.
      */
     public void clearBoard() {
                   for (int i = 0; i < spielbrettGroesse; i++) {
                          Spielbrett[i] = leer;
                     }
             }

     /**
      * Spielzüge setzen.
      *
      * @param player   Spieler
      * @param location Position
      */
     public void setMove(final char player, final int location) {
                   Spielbrett[location] = player;
               }

     /**
      * berechnet die zufällige Position des Computers
      * @return Computerposition
      */
     public int getComputerMove() {
                  int move;

                   for (int i = 0; i < getspielbrettGroesse(); i++) {
                           if (Spielbrett[i] != user && Spielbrett[i] != computer) {
                                     char curr = Spielbrett[i];
                                     Spielbrett[i] = computer;
                                     if (checkForWinner() == 3) {
                                               setMove(computer, i);
                                              return i;
                                     } else {
                                         Spielbrett[i] = curr;
                                     }
                                }
                       }

                  for (int i = 0; i < getspielbrettGroesse(); i++) {
                             if (Spielbrett[i] != user && Spielbrett[i] != computer) {
                                    char curr = Spielbrett[i];
                                      Spielbrett[i] = user;
                                     if (checkForWinner() == 2) {
                                             setMove(computer, i);
                                              return i;
                                     } else {
                                         Spielbrett[i] = curr;
                                     }

                               }
                        }

                  do {
                             move = zufall.nextInt(getspielbrettGroesse());
                        } while (Spielbrett[move] == user || Spielbrett[move] == computer);

                         setMove(computer, move);
                   return move;
               }

     /**
      * Kontrolle, ob Gewinner vorhanden
      * @return int
      */
     public int checkForWinner() {
                    for (int i = 0; i <= 6; i += 3) {
                            if (Spielbrett[i] == user
                                    && Spielbrett[i + 1] == user
                                    && Spielbrett[i + 2] == user) {
                                return 2;
                            }

                             if (Spielbrett[i] == computer
                                     && Spielbrett[i + 1] == computer
                                     && Spielbrett[i + 2] == computer) {
                                 return 3;
                             }

                        }

                    for (int i = 0; i <= 2; i++) {
                             if (Spielbrett[i] == user
                                     && Spielbrett[i + 3] == user
                                     && Spielbrett[i + 6] == user) {
                                 return 2;
                             }

                            if (Spielbrett[i] == computer
                                    && Spielbrett[i + 3] == computer
                                    && Spielbrett[i + 6] == computer) {
                                return 3;
                            }
                       }

                  if ((Spielbrett[0] == user
                          && Spielbrett[4] == user
                          && Spielbrett[8] == user)
                          || Spielbrett[2] == user
                          && Spielbrett[4] == user
                          && Spielbrett[6] == user) {
                        return 2;
                  }

                    if ((Spielbrett[0] == computer
                                && Spielbrett[4] == computer
                                && Spielbrett[8] == computer)
                                ||
                        Spielbrett[2] == computer
                                && Spielbrett[4] == computer
                                && Spielbrett[6] == computer) {
                        return 3;
                    }

                    for (int i = 0; i < getspielbrettGroesse(); i++) {
                            if (Spielbrett[i] != user && Spielbrett[i] != computer) {
                                return 0;
                            }
                        }

                  return 1;
               }
      }
