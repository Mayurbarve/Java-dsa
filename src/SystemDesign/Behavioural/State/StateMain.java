package SystemDesign.Behavioural.State;

public class StateMain {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();

        vm.insertCoin(1.0);   // Rejected: no item selected
        vm.selectItem("A1");  // Transitions to ItemSelectedState
        vm.insertCoin(1.5);   // Transitions to HasMoneyState
        vm.dispenseItem();    // Dispenses, resets to IdleState

        System.out.println("\n--- Second Transaction ---");
        vm.selectItem("B2");
        vm.insertCoin(2.0);
        vm.dispenseItem();
    }
}
