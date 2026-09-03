package SystemDesign.Behavioural.State;

public class ItemSelectedState implements MachineState{
    @Override
    public void selectItem(VendingMachine context, String itemCode){
        System.out.println("Item already Selected");
    }

    @Override
    public void insertCoin(VendingMachine context, double amount){
        System.out.println("Inserted amount " + amount + " for " + context.getSelectedItem());
        context.setInsertedAmount(amount);
        context.setState(new hasMoneyState());
    }

    @Override
    public void dispenseItem(VendingMachine context){
        System.out.println("Insert Coin before dispensing");
    }


}
