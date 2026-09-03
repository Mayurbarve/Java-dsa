package SystemDesign.Creational.AbstractFactory.Examples;

import java.util.Scanner;

interface Button{
    void onClick();
    void Style();
}

interface CheckBox{
    void onSelect();
    void Style();
}

class DarkButton implements Button{
    @Override
    public void onClick() {
        System.out.println("Dark Button clicked");
    }
    @Override
    public void Style() {
        System.out.println("Dark button styled");
    }
}

class DarkCheckbox implements CheckBox{
    @Override
    public void onSelect() {
        System.out.println("Dark Checkbox Selected");
    }

    @Override
    public void Style() {
        System.out.println("Dark Checkbox styled");
    }
}

class LightButton implements Button{
    @Override
    public void onClick() {
        System.out.println("Light Button clicked");
    }
    @Override
    public void Style() {
        System.out.println("Light button styled");
    }
}

class LightCheckbox implements CheckBox{
    @Override
    public void onSelect() {
        System.out.println("Light Checkbox Selected");
    }

    @Override
    public void Style() {
        System.out.println("Light Checkbox styled");
    }
}

interface GUIComponent{
     Button createButton();
     CheckBox createCheckBox();
}

class DarkThemeGUI implements GUIComponent{
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new DarkCheckbox();
    }
}

class LightThemeGUI implements GUIComponent{
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new LightCheckbox();
    }
}


class Application{
    private final Button button;
    private final CheckBox checkBox;

    Application(GUIComponent component){
        this.button = component.createButton();
        this.checkBox = component.createCheckBox();
    }

    public void renderTheme(){
        button.onClick();
        checkBox.onSelect();
    }
}

public class UIThemeFactory{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Theme: ");
        String theme = sc.nextLine();

        GUIComponent component;

        if(theme.equalsIgnoreCase("Dark")){
            component = new DarkThemeGUI();
        }
        else{
            component = new LightThemeGUI();
        }

        Application app = new Application(component);
        app.renderTheme();
    }
}