package SystemDesign.Creational.AbstractFactory;

//Client Code
public class Application {
    private final Button button;
    private final CheckBox checkBox;


    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkBox = factory.createCheckBox();
    }

    public void renderUi(){
        button.paint();
        checkBox.paint();
    }
}
