package main.com.rqueztech.ui.events;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
* A DocumentListener implementation that listens for changes in a JTextField's document.
*/
public class TextFieldListener implements DocumentListener {

  private JTextField textField;

  public TextFieldListener(JTextField textField) {
    this.textField = textField;

  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    this.textField.getText();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    this.textField.getText();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub

  }
}
