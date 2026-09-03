package SystemDesign.Behavioural.State;

public class hasMoneyState implements MachineState{
    @Override
    public void selectItem(VendingMachine context, String itemCode){
        System.out.println("Cannot Change item after inserting Money");
    }

    @Override
    public void insertCoin(VendingMachine context, double amount){
        System.out.println("Money already Inserted");
    }

    @Override
    public void dispenseItem(VendingMachine context){
        System.out.println("Dispensing Item: " + context.getSelectedItem());
        context.setState(new despensingState());
        System.out.println("Item Dispensing Successful");
        context.reset();
    }

}
