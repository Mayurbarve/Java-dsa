package SystemDesign.Creational.AbstractFactory;

//Concrete Factories
public class MacOsFactory implements GUIFactory{

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacOSCheckBox();
    }
}
