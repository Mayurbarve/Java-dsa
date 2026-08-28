package SystemDesign.Creational.AbstractFactory;

public class MacOSCheckBox implements CheckBox {
    @Override
    public void paint() {
        System.out.println("MacOS CheckBox painted");
    }

    @Override
    public void onSelect() {
        System.out.println("MacOS CheckBox onSelect");
    }
}
