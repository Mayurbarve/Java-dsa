import SystemDesign.Creational.AbstractFactory.WindowsFactory;

void main() {
    String os = System.getProperty("os.name");

    GuiFactory factory = new MacOsFactory();

    Application style = new Application(factory);
    style.renderGui();
}

//Abstract Product
interface Button{
    void onClick();
    void paint();
}

interface Checkbox{
    void onSelect();
    void paint();
}

//Concrete Product
static class MacOsButton implements Button{
    @Override
    public void onClick(){
        System.out.println("MacOs button clicked");
    }

    @Override
    public void paint(){
        System.out.println("MacOs Style Button");
    }
}
static class MacOsCheckbox implements Checkbox{
    @Override
    public void onSelect(){
        System.out.println("MacOs Checkbox selected");
    }

    @Override
    public void paint(){
        System.out.println("MacOs Style Checkbox");
    }
}

//Abstract Factory
interface GuiFactory {
    Button createButton();
    Checkbox createCheckbox();
}

//Concrete factory
static class MacOsFactory implements GuiFactory {
    @Override
    public Button createButton() {
        return new MacOsButton();
    }
    public Checkbox createCheckbox() {
        return new MacOsCheckbox();
    }
}


public static class Application{
    private final Button button;
    private final Checkbox checkbox;

    Application(GuiFactory factory){
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void renderGui(){
        button.paint();
        checkbox.paint();
    }
}

