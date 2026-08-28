package SystemDesign.Creational.AbstractFactory;

//Create Concrete Products
public class WindowsCheckBox implements CheckBox {

    @Override
    public void onSelect() {
        System.out.println("Selected Windows CheckBox.");
    }

    @Override
    public void paint(){
        System.out.println("Painted Windows CheckBox.");
    }
}
