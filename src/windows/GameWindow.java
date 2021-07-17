package windows;

public class GameWindow extends Window{
    private FrontWindow frontWindow;

    public GameWindow(FrontWindow frontWindow){
        this.frontWindow = frontWindow;
        setTitle("Новая игра");
    }

}
