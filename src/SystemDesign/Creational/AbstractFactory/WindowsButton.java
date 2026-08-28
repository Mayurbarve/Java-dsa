package SystemDesign.Creational.AbstractFactory;

//Create Concrete Products
public class WindowsButton implements Button {

    @Override
    public void paint(){
        System.out.println("Painted Windows Button");
    }

    @Override
    public void click(){
        System.out.println("Clicked Windows Button");
    }
}
