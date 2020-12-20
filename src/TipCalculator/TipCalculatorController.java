package TipCalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController { 
   // formatters for currency and percentages
   private static final NumberFormat currency = 
      NumberFormat.getCurrencyInstance();
   private static final NumberFormat percent = 
      NumberFormat.getPercentInstance();
   
   private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
   private BigDecimal totalAmount = new BigDecimal(0);
   
   // GUI controls defined in FXML and used by the controller's code
   @FXML
   private Label amountLabel;

   @FXML
   private Label tipPercentageLabel;

   @FXML
   private Label tipLabel;

   @FXML
   private Label totalLabel;

   @FXML
   private Slider tipPercentageSlider;

   @FXML
   private TextField amountTextField;

   @FXML
   private TextField tipTextField;

   @FXML
   private TextField totalTextField;

   // calculates and displays the tip and total amounts
//   private void calculateButtonPressed(ActionEvent event) {
//      try {
//         BigDecimal amount = new BigDecimal(amountTextField.getText());
//         BigDecimal totalUsers = new BigDecimal(userTextField.getText());
//         BigDecimal tip = amount.multiply(tipPercentage);
//         BigDecimal total = amount.add(tip);
//         BigDecimal totalPerHead = total.divide(totalUsers);
//
//         tipTextField.setText(currency.format(tip));
//         totalTextField.setText(currency.format(total));
//         perHeadTextField.setText(currency.format(totalPerHead));
//      }
//      catch (NumberFormatException ex) {
//         amountTextField.setText("Enter amount");
//         amountTextField.selectAll();
//         amountTextField.requestFocus();
//      }
//   }

   // called by FXMLLoader to initialize the controller
   public void initialize() {
      // 0-4 rounds down, 5-9 rounds up 
      currency.setRoundingMode(RoundingMode.HALF_UP);


      
      // listener for changes to tipPercentageSlider's value
      tipPercentageSlider.valueProperty().addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, 
               Number oldValue, Number newValue) {
               tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
               tipPercentageLabel.setText(percent.format(tipPercentage));
               calculateAmount();
            }
         }
      );

      amountTextField.textProperty().addListener((args, oldValue, newValue) -> {
         try {
            totalAmount = new BigDecimal(newValue);}
         catch (Exception e) {
            amountTextField.setText("Enter proper value");
         }
         calculateAmount();
      });
   }

   public void calculateAmount(){
      if (totalAmount.doubleValue() >=0 ){
         BigDecimal tip = totalAmount.multiply(tipPercentage);
         BigDecimal total = totalAmount.add(tip);
         tipTextField.setText(currency.format(tip));
         totalTextField.setText(currency.format(total));
      }else {
         tipTextField.setText("0");
         tipTextField.setText("0");
      }
   }
}
