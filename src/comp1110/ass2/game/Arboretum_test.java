//package comp1110.ass2.game;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class comp1110.ass2.Arboretum {
//    HashMap<Position, Card> arboretum = new HashMap<>();
//
//    public comp1110.ass2.Arboretum(int size){
//        for (int i = 0; i < size; i++){
//            for (int j = 0; j < size; j++){
//                this.arboretum.put(new Position(i,j), new Card());
//            }
//        }
//    }
//    public void addCard(Card card, Position position){//change to bool
//        this.arboretum.replace(position,card);
//
//    }
//    public Card getCard(Position position){
//        return this.arboretum.get(position);
//    }
//
//    public boolean isPosCanPlace(Position position){
//        return this.arboretum.get(position).isEmptyCard();
//    }
//
//    public List<Card> showCurrentCards(){
//        List<Card> cards = new ArrayList<>();
//
//        for (Position position:this.arboretum.keySet()) {
//            if (!isPosCanPlace(position)){
//                cards.add(getCard(position));
//            }
//        }
//        return cards;
//    }
//
//    public List<Position> showAvailablePlaces(){
//        List<Position> positions = new ArrayList<>();
//
//        for (Position position:this.arboretum.keySet()) {
//            if (isPosCanPlace(position)){
//                positions.add(position);
//            }
//        }
//
//        return positions;
//    }
//
//    public void scoreRoutes(Species species){
//
//    }
//
//    public int calculateScore(Species species){
//        return 0;
//    }
//
//}
