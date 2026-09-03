package SystemDesign.Behavioural.State;

public class VendingMachine {
    private MachineState currentState;
    private String selectedItem;
    private double insertedAmount;

    public VendingMachine(){
        this.currentState = new IdleState();
    }

    public void setState(MachineState newState){
        currentState = newState;
    }

    public void setSelectedItem(String selectedItem){
        this.selectedItem = selectedItem;
    }
     public void setInsertedAmount(double insertedAmount){
        this.insertedAmount = insertedAmount;
     }

     public double getInsertedAmount(){
        return insertedAmount;
     }

     public String getSelectedItem(){
        return selectedItem;
     }

     public void selectItem(String itemCode){
        currentState.selectItem(this, itemCode);
     }

     public void insertCoin(double insertedAmount){
        currentState.insertCoin(this, insertedAmount);
     }

     public void dispenseItem(){
        currentState.dispenseItem(this);
     }

     public void reset(){
        currentState = new IdleState();
        insertedAmount = 0;
        selectedItem = "";
     }


}
