package controller;

public interface Controller {
    boolean isDefault();
    boolean isRequestingUp();
    boolean isRequestingDown();
    boolean isRequestingLeft();
    boolean isRequestingRight();
}
