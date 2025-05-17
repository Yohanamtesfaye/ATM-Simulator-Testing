package com.abeto.javafx.ui;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TextFieldWrapper implements UIComponent {
    private final TextField textField;

    public TextFieldWrapper(TextField textField) {
        this.textField = textField;
    }

    @Override
    public String getText() {
        return textField.getText();
    }

    @Override
    public void setText(String text) {
        textField.setText(text);
    }

    @Override
    public void setTextFill(Color color) {
        textField.setStyle("-fx-text-fill: " + color.toString().replace("0x", "#"));
    }
} 