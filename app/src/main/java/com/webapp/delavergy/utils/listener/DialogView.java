package com.webapp.delavergy.utils.listener;

public interface DialogView<T> {

    void setData(T t);

    void showDialog(String s);

    void hideDialog();
}
