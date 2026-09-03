package SystemDesign.Behavioural.State;

public class IdleState implements MachineState{

    @Override
    public void selectItem(VendingMachine context, String itemCode){
        System.out.println("Selecting Item "+itemCode);
        context.setSelectedItem(itemCode);
        context.setState(new ItemSelectedState());
    }

    @Override
    public void insertCoin(VendingMachine context, double amount){
        System.out.println("Please Select item before inserting coins");
    }

    @Override
    public void dispenseItem(VendingMachine context){
        System.out.println("Please Select item before dispensing");
    }
}
