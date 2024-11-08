import java.util.Stack;

//Protokollierbar
public interface Recordable {
    Stack<GameMove> record = new Stack<GameMove>();
    public void recordMove(GameMove move);
    public void deleteMove();
}
