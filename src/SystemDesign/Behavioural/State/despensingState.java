package SystemDesign.Behavioural.State;

public class despensingState implements MachineState{
    @Override
    public void selectItem(VendingMachine context, String itemCode){
        System.out.println("Please wait dispensing in process");
    }

    @Override
    public void insertCoin(VendingMachine context, double amount){
        System.out.println("Please wait dispensing in process");
    }

    @Override
    public void dispenseItem(VendingMachine context){
        System.out.println("already dispensing Please wait.");
    }
}
