package controller;

public interface Controller {
    // Attributes for the controller
    // boolean methods para malaman anong key napindot
    boolean isRequestingUp();
    boolean isRequestingDown();
    boolean isRequestingLeft();
    boolean isRequestingRight();
    boolean isSprinting();
    boolean isSneaking();
    boolean isSprintKeyReleased();

    boolean isPaused();
}
