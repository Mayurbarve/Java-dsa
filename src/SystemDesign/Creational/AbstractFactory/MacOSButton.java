package SystemDesign.Creational.AbstractFactory;

public class MacOSButton implements Button {

    @Override
    public void click() {
        System.out.println("MacOS Button clicked");
    }

    @Override
    public void paint() {
        System.out.println("MacOS Button painted");
    }
}
